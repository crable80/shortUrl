package studio.thinkground.aroundhub.data.dao.impl;

import org.springframework.stereotype.Service;

import studio.thinkground.aroundhub.data.dao.ShortUrlDAO;
import studio.thinkground.aroundhub.data.entity.ShortUrl;
import studio.thinkground.aroundhub.data.repository.ShortUrlRepository;

@Service
public class ShortUrlDAOImpl implements ShortUrlDAO{
	
	ShortUrlRepository shortUrlRepository;

	public ShortUrlDAOImpl(ShortUrlRepository shortUrlRepository) {
		this.shortUrlRepository = shortUrlRepository;
	}
	
	//단축 url 저장
	@Override
	public ShortUrl saveShortUrl(ShortUrl shortUrlEntity) {
		ShortUrl foundShortUrlEntity = shortUrlRepository.save(shortUrlEntity);
		return foundShortUrlEntity;
	}
	
	//단축 url 조회 by originalUrl
	@Override
	public ShortUrl getShortUrl(String originalUrl) {
		ShortUrl foundShortUrlEntity = shortUrlRepository.findByOrgUrl(originalUrl);
		return foundShortUrlEntity;
	}

	//단축 url 조회 by shortUrl
	@Override
	public ShortUrl getOriginalUrl(String shortUrl) {
		ShortUrl foundShortUrlEntity = shortUrlRepository.findByUrl(shortUrl);
		return foundShortUrlEntity;
	}

	@Override
	public ShortUrl updateShortUrl(ShortUrl newShortUrlEntity) {
		ShortUrl foundShortUrlEntity = shortUrlRepository.findByOrgUrl(newShortUrlEntity.getOrgUrl());
		
		foundShortUrlEntity.setUrl(newShortUrlEntity.getUrl());
		
		ShortUrl savedShortUrlEntity = shortUrlRepository.save(foundShortUrlEntity);
		
		return savedShortUrlEntity;
	}

	//단축 url 삭제 by shortUrl
	@Override
	public void deleteByShortUrl(String shortUrl) {
		ShortUrl foundShortUrlEntity = shortUrlRepository.findByUrl(shortUrl);
		shortUrlRepository.delete(foundShortUrlEntity);
	}

	//단축 url 삭제 by originalUrl
	@Override
	public void deleteByOriginalUrl(String originalUrl) {
		ShortUrl foundShortUrlEntity = shortUrlRepository.findByOrgUrl(originalUrl);
		shortUrlRepository.delete(foundShortUrlEntity);
	}
	

	
}
