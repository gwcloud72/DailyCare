package com.gwcloud.dailycare.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="cow")
public class Cow {
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY)
  private Long id;
 // 소 기본키 
  @Column(name ="ear_tag", nullable = false, unique= true , length=50 )
  private String earTag;
  // 개체 식별번호
  @Column(nullable =false, length= 100)
  private String name;
  // 이름및 별칭
  @Column(name = "birth_date")
  private LocalDate birthDate;
  // 출생일
  @Column(length = 50)
  private String breed;
  //품종
  @Column(nullable =false , length =30)
  private String status;
  //현재상태
  @Column(columnDefinition ="TEXT")
  private String memo;
  // 메모
  @Column(name="created_at", nullable=false)
  private LocalDateTime createdAt;
  //생성일시
  
  @Column(name="updated_at", nullable=false)
  private LocalDateTime updatedAt;
  //수정일시
  
  // 처음등록할때 사용할 생성자
  @Builder
  public Cow(String earTag, String name, LocalDate birthDate, String breed , String status, String memo)
  {
	  this.earTag= earTag;
	  this.name= name;
	  this.birthDate= birthDate;
	  this.breed= breed;
	  this.status=status;
	  this.memo=memo;
  }
  
  //
 // 생성일 수정일 기본상태값세팅
  //
   
  @PrePersist
  public void prePersist() {
	  LocalDateTime now = LocalDateTime.now();
	  this.createdAt=now;
	  this.updatedAt=now;
	  if(this.status==null|| this.status.isBlank()) {
		  this.status="NORMAL";
	  }
  }
  
  
  
  
}
