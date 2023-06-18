package studio.thinkground.aroundhub.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import studio.thinkground.aroundhub.data.entity.ShortUrlEntity;

public interface ShortUrlRepository extends JpaRepository<ShortUrlEntity, Long> {

	//ShortUrlEntity findById(String url);
	
	ShortUrlEntity findByOrgUrl(String originalUrl);
	
	ShortUrlEntity findByUrl(String url);
}
