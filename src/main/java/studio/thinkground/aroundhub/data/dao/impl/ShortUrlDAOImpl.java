package studio.thinkground.aroundhub.data.dao.impl;

import org.springframework.stereotype.Service;

import studio.thinkground.aroundhub.data.dao.ShortUrlDAO;
import studio.thinkground.aroundhub.data.entity.ShortUrlEntity;
import studio.thinkground.aroundhub.data.repository.ShortUrlRepository;

@Service
public class ShortUrlDAOImpl implements ShortUrlDAO{
	
	ShortUrlRepository shortUrlRepository;

	public ShortUrlDAOImpl(ShortUrlRepository shortUrlRepository) {
		this.shortUrlRepository = shortUrlRepository;
	}
	
	//단축 url 저장
	@Override
	public ShortUrlEntity saveShortUrl(ShortUrlEntity shortUrlEntity) {
		ShortUrlEntity foundShortUrlEntity = shortUrlRepository.save(shortUrlEntity);
		return foundShortUrlEntity;
	}
	
	//단축 url 조회 by originalUrl
	@Override
	public ShortUrlEntity getShortUrl(String originalUrl) {
		ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByOrgUrl(originalUrl);
		return foundShortUrlEntity;
	}

	//단축 url 조회 by shortUrl
	@Override
	public ShortUrlEntity getOriginalUrl(String shortUrl) {
		ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByUrl(shortUrl);
		return foundShortUrlEntity;
	}

	@Override
	public ShortUrlEntity updateShortUrl(ShortUrlEntity newShortUrlEntity) {
		ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByOrgUrl(newShortUrlEntity.getOrgUrl());
		
		foundShortUrlEntity.setUrl(newShortUrlEntity.getUrl());
		
		ShortUrlEntity savedShortUrlEntity = shortUrlRepository.save(foundShortUrlEntity);
		
		return savedShortUrlEntity;
	}

	//단축 url 삭제 by shortUrl
	@Override
	public void deleteByShortUrl(String shortUrl) {
		ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByUrl(shortUrl);
		shortUrlRepository.delete(foundShortUrlEntity);
	}

	//단축 url 삭제 by originalUrl
	@Override
	public void deleteByOriginalUrl(String originalUrl) {
		ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByOrgUrl(originalUrl);
		shortUrlRepository.delete(foundShortUrlEntity);
	}
	

	
}
