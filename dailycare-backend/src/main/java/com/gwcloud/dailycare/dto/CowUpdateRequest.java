package com.gwcloud.dailycare.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 젖소 수정 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class CowUpdateRequest {

    @NotBlank(message = "개체번호는 필수입니다.")
    private String earTag;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    private LocalDate birthDate;
    private String breed;
    private String status;
    private String memo;
}