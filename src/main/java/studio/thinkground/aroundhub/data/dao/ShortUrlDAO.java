package studio.thinkground.aroundhub.data.dao;

import studio.thinkground.aroundhub.data.entity.ShortUrl;

public interface ShortUrlDAO {

	ShortUrl saveShortUrl(ShortUrl shortUrlEntity); //단축url 저장
	
	ShortUrl getShortUrl(String originalUrl); //단축url 조회 by originalUrl
	 
	ShortUrl getOriginalUrl(String shortUrl); //단축url 조회 by shortUrl
	
	ShortUrl updateShortUrl(ShortUrl newShortUrlEntity);
	
	void deleteByShortUrl(String shortUrl); //단축url 삭제 by shortUrl
	
	void deleteByOriginalUrl(String originalUrl); //단축url 삭제 by originalUrl
}
