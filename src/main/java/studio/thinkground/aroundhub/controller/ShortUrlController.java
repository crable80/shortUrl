package studio.thinkground.aroundhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import studio.thinkground.aroundhub.data.dto.ShortUrlResponseDto;
import studio.thinkground.aroundhub.service.ShortUrlService;

/**
 * @author frodo
 * 단축URL 컨트롤러 (네이버 API 연동)
 */
@RestController
@RequestMapping("/short-url")
public class ShortUrlController {

	private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlController.class);
	
	@Value("${around.hub.short.url.id}")
	private String CLIENT_ID; //네이버 client id
	
	@Value("${around.hub.short.url.scret}")
	private String CLIENT_SCRET; //네이버 client scret
	
	private ShortUrlService shortUrlService;
	
	public ShortUrlController(ShortUrlService shortUrlService) {
		this.shortUrlService = shortUrlService;
	}
	
	// 네이버 API 단축url 조회 -> DB 저장
	@PostMapping()
	public ShortUrlResponseDto generateShortUrl(String originalUrl) {
		
		LOGGER.info("[generateShortUrl] perform API. CLIENT_ID : {}, CLIENT_SECRET : {]", CLIENT_ID, CLIENT_SCRET);
		
		return shortUrlService.generateShortUrl(CLIENT_ID, CLIENT_SCRET, originalUrl);
		
	}
	
	//단축 url 조회 (DB조회후 없으면, 네이버 API 조회)
	@GetMapping()
	public ShortUrlResponseDto getShortUrl(String originalUrl) {
		//ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto("ss", "ss");
		
		return shortUrlService.getShortUrl(CLIENT_ID, CLIENT_SCRET, originalUrl);
	}
	
	@PutMapping("/")
	public ShortUrlResponseDto updateShortUrl(String originalUrl) { return null; }
	
	//단축 url 삭제 by (originalUrl or shortUrl) 
	@DeleteMapping("/")
	public ResponseEntity<String> deleteShortUrl(String url) { 
		try {
			
			shortUrlService.deleteShortUrl(url);
			
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
		
	}
	
	
}
