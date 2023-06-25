package studio.thinkground.aroundhub.service.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import studio.thinkground.aroundhub.data.dao.ShortUrlDAO;
import studio.thinkground.aroundhub.data.dto.NaverUriDto;
import studio.thinkground.aroundhub.data.dto.ShortUrlResponseDto;
import studio.thinkground.aroundhub.data.entity.ShortUrl;
import studio.thinkground.aroundhub.data.repository.ShortUrlRedisRepository;
import studio.thinkground.aroundhub.service.ShortUrlService;

@Service
public class ShortUrlServiceImpl implements ShortUrlService{
	
	private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlServiceImpl.class);
	private final ShortUrlDAO shortUrlDAO;
	private final ShortUrlRedisRepository shortUrlRedisRepository; // (Redis)
	
	@Autowired
	public ShortUrlServiceImpl(ShortUrlDAO shortUrlDAO, ShortUrlRedisRepository shortUrlRedisRepository) {
		this.shortUrlDAO = shortUrlDAO;
		this.shortUrlRedisRepository = shortUrlRedisRepository;
	}

	//단축 url 조회 (DB조회후 없으면, 네이버 API 조회)
	@Override
	public ShortUrlResponseDto getShortUrl(String clientId, String clientSecret, String originalUrl) {
		LOGGER.info("[getShortUrl] request data : {}", originalUrl);
		
		//Cache Logic (Redis) - 사용하려면 docker 서버 생성할것!
//		Optional<ShortUrlResponseDto> foundResponseDto = shortUrlRedisRepository.findById(originalUrl);
//		if(foundResponseDto.isPresent()) {
//			LOGGER.info("[getShortUrl] Cache Data is existed.");
//			return foundResponseDto.get();
//		}else {
//			LOGGER.info("[getShortUrl] Cache Data is not existed.");
//		}
		
		//단축 url DB 조회
		ShortUrl getShortUrlEntity = shortUrlDAO.getShortUrl(originalUrl);
		
		String orgUrl;
		String shortUrl;
		
		if(getShortUrlEntity == null) { //단축 url DB 조회 결과 없을시 -> 네이버 API 조회
			LOGGER.info("[getShortUrl] No Entity in Database.");
			ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);
			
			orgUrl	= responseEntity.getBody().getResult().getOrgUrl();
			shortUrl= responseEntity.getBody().getResult().getUrl();
			
		} else {
			orgUrl	= getShortUrlEntity.getOrgUrl();
			shortUrl= getShortUrlEntity.getUrl();
		}
		
		//결과 반환 DTO
		ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);
		
		LOGGER.info("[getShortUrl] Response DTO : {}", shortUrlResponseDto.toString());
		
		return shortUrlResponseDto;
		
	}

	// 네이버 API 단축url 조회 -> DB 저장
	@Override
	public ShortUrlResponseDto generateShortUrl(String clientId, String clientSecret, String originalUrl) {
		LOGGER.info("[generateShortUrl] request data : {}", originalUrl);
		
		//네이버 API 단축url 조회
		ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);
		
		String orgUrl	= responseEntity.getBody().getResult().getOrgUrl();
		String shortUrl	= responseEntity.getBody().getResult().getUrl();
		String hash		= responseEntity.getBody().getResult().getHash();
		
		ShortUrl shortUrlEntity = new ShortUrl();
		shortUrlEntity.setOrgUrl(orgUrl);
		shortUrlEntity.setUrl(shortUrl);
		shortUrlEntity.setHash(hash);
		
		// 단축url DB 저장
		shortUrlDAO.saveShortUrl(shortUrlEntity);
		
		//반환 DTO
		ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);
		
		// Cache Logic (Redis) - 사용하려면 docker 서버 생성할것!
		//shortUrlRedisRepository.save(shortUrlResponseDto);
		
		LOGGER.info("[generateShortUrl] Response DTO : {}", shortUrlResponseDto.toString());
		
		return shortUrlResponseDto;
		
	}

	@Override
	public ShortUrlResponseDto updateShortUrl(String clientId, String clientSecret, String originalUrl) {
		return null;
	}
	
	// 단축url DB 삭제
	@Override
	public void deleteShortUrl(String url) {
		if(url.contains("me2.do")) {
			LOGGER.info("[deleteShortUrl] Request Url is 'ShortUrl'.");
			deleteByShortUrl(url);
		}else {
			LOGGER.info("[deleteShortUrl] Request Url is 'OriginalUrl'.");
			deleteByOriginalUrl(url);
		}
	}

	//단축 url 삭제 by shortUrl
	public void deleteByShortUrl(String url) {
		LOGGER.info("[deleteByShortUrl] delete record");
		shortUrlDAO.deleteByShortUrl(url);
	}

	//단축 url 삭제 by originalUrl
	public void deleteByOriginalUrl(String url) {
		LOGGER.info("[deleteByOriginalUrl] delete record");
		shortUrlDAO.deleteByOriginalUrl(url);
	}
	
	// 네이버 API - 단축 url 조회
	private ResponseEntity<NaverUriDto> requestShortUrl(String clientId, String clientSecret, String originalUrl){
		LOGGER.info("[requestShortUrl] client ID : ***, client Secret : ***, original URL : {}", originalUrl);
		
		URI uri = UriComponentsBuilder
				.fromUriString("https://openapi.naver.com")
				.path("/v1/util/shorturl")
				.queryParam("url", originalUrl) //url 파라마터
				.encode()
				.build()
				.toUri();
		
		LOGGER.info("[requestShortUrl] set HTTP Request Header");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("X-Naver-Client-Id", clientId);			//네이버 인증 id
		headers.set("X-Naver-Client-Secret", clientSecret);	//네이버 인증 secret
		
		HttpEntity<String> entity = new HttpEntity<>("", headers);
		
		//RestTemplate 사용
		RestTemplate restTemplate = new RestTemplate();
		
		LOGGER.info("[requestShortUrl] request by restTemplate");
		ResponseEntity<NaverUriDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, NaverUriDto.class); //단축 url 요청
		
		LOGGER.info("[requestShortUrl] request has been successfully complete.");
		
		return responseEntity;
		
		
	}

}
