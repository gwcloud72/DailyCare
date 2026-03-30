package com.gwcloud.dailycare.conroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwcloud.dailycare.dto.CowCreateRequest;
import com.gwcloud.dailycare.dto.CowResponse;
import com.gwcloud.dailycare.service.CowService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//
// 등록 목록조회 기능 구성
//
@Tag(name="Cow",description="개체 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cows")
public class CowController {
	private final CowService cowService;
	 
	//등록 API
	@Operation(summary=" 등록", description=" 개체 정보를 등록합니다")
	@PostMapping
	public CowResponse create(@Valid @RequestBody CowCreateRequest request) {
		return cowService.create(request);
	}
	@Operation(summary=" 조회", description=" 등록된 개체를 조회합니다")
	@GetMapping
	public List<CowResponse> findAll(){
		return cowService.findAll();
	}
	}
 

