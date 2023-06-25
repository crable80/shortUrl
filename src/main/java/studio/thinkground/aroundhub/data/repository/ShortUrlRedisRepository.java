package studio.thinkground.aroundhub.data.repository;

import org.springframework.data.repository.CrudRepository;

import studio.thinkground.aroundhub.data.dto.ShortUrlResponseDto;

public interface ShortUrlRedisRepository extends CrudRepository<ShortUrlResponseDto, String>{

}
