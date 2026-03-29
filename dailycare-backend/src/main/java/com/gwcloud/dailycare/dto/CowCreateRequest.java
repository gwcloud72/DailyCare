package com.gwcloud.dailycare.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 소 등록 요청을 받을때 사용하는DTO
@Getter
@Setter
@NoArgsConstructor
public class CowCreateRequest {
	// 개체번호는 필수입력값
	@NotBlank(message=" 개체번호는 필수입니다.")
   private String earTag;
	// 이름도 필수입력값
	@NotBlank(message=" 이름은 필수입니다")  
    private String name;
	
	
	private LocalDate birthDate; //생일
	private String breed;   //품종
	private String status;  //상태
	private String memo; //메모
   
}
