package com.gwcloud.dailycare.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.gwcloud.dailycare.entity.Cow;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
// 개체 정보를 응답으로 내려줄때 사용하는 Dto
// 엔티티를 그대로 노출하지않고 필요한값만 반환

@Getter
@Builder
public class CowResponse {
	private Long id;
	private String earTag;
	private String name;
	private LocalDate birthDate;
	private String breed;
	private String status;
	private String memo;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	// 엔티티를 응답 DTO로 사용
	// 서비스나 컨트롤러에서 반복되는 변환코드를 줄이기위해사용

	public static CowResponse from(Cow cow) {
		return CowResponse.builder()
				.id(cow.getId())
				.earTag(cow.getEarTag())
				.name(cow.getName())
				.birthDate(cow.getBirthDate())
				.breed(cow.getBreed())
				.status(cow.getStatus())
				.memo(cow.getMemo())
				.createdAt(cow.getCreatedAt())
				.updatedAt(cow.getUpdatedAt())
				.build();
	}
}
