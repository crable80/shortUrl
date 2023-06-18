package studio.thinkground.aroundhub.data.dao;

import studio.thinkground.aroundhub.data.entity.ShortUrlEntity;

public interface ShortUrlDAO {

	ShortUrlEntity saveShortUrl(ShortUrlEntity shortUrlEntity); //단축url 저장
	
	ShortUrlEntity getShortUrl(String originalUrl); //단축url 조회 by originalUrl
	 
	ShortUrlEntity getOriginalUrl(String shortUrl); //단축url 조회 by shortUrl
	
	ShortUrlEntity updateShortUrl(ShortUrlEntity newShortUrlEntity);
	
	void deleteByShortUrl(String shortUrl); //단축url 삭제 by shortUrl
	
	void deleteByOriginalUrl(String originalUrl); //단축url 삭제 by originalUrl
}
