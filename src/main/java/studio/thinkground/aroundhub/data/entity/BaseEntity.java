package studio.thinkground.aroundhub.data.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass //공통 엔티티로 사용 (superclass 라고 생각하면됨)
@EntityListeners(AuditingEntityListener.class) //엔티티 감사 리스너
public class BaseEntity {
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
//	@CreatedBy
//	@Column(updatable = false)
//	private String createdBy;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
//	@LastModifiedBy
//	private String updatedBy;

}
