package studio.thinkground.aroundhub.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import studio.thinkground.aroundhub.data.entity.ShortUrl;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

	//ShortUrlEntity findById(String url);
	
	ShortUrl findByOrgUrl(String originalUrl);
	
	ShortUrl findByUrl(String url);
}
