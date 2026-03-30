package com.gwcloud.dailycare.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.gwcloud.dailycare.dto.CowCreateRequest;
import com.gwcloud.dailycare.dto.CowResponse;
import com.gwcloud.dailycare.entity.Cow;
import com.gwcloud.dailycare.repository.CowRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CowService {
  private final CowRepository cowRepository;
  
  // 개체 새로등록 같은 개체 번호  등록시 예외발생
  public CowResponse create(CowCreateRequest request) {
	  if(cowRepository.existsByEarTag(request.getEarTag())) {
		  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"이미 등록된 개체번호입니다");
	  }
		  Cow cow = Cow.builder()
			  .earTag(request.getEarTag())
			  .name(request.getName())
			  .birthDate(request.getBirthDate())
			  .breed(request.getBreed())
			  .status(request.getStatus())
			  .memo(request.getMemo())
			  .build();
			Cow savedCow = cowRepository.save(cow);  
			return CowResponse.from(savedCow);
  }
  
  //조회전용이므로 readOnly사용 성능 최적화
  @Transactional(readOnly = true)
  public List<CowResponse> findAll() {
      return cowRepository.findAll()
              .stream()
              .map(CowResponse::from)
              .toList();
  }
  
  
  }
  

