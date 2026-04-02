package com.gwcloud.dailycare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwcloud.dailycare.dto.CowCreateRequest;
import com.gwcloud.dailycare.dto.CowResponse;
import com.gwcloud.dailycare.dto.CowUpdateRequest;
import com.gwcloud.dailycare.service.CowService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/*
 * 개체 관리 API 컨트롤러
 */
@Tag(name = "Cow", description = "개체 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cows")
public class CowController {

    private final CowService cowService;

    /*
     * 등록
     */
    @Operation(summary = "등록", description = "개체 정보를 등록합니다.")
    @PostMapping
    public CowResponse create(@Valid @RequestBody CowCreateRequest request) {
        return cowService.create(request);
    }

    /*
     * 전체 목록 조회
     */
    @Operation(summary = "목록 조회", description = "등록된 개체 목록을 조회합니다.")
    @GetMapping
    public List<CowResponse> findAll() {
        return cowService.findAll();
    }

    /*
     * 상세 조회
     */
    @Operation(summary = "상세 조회", description = "개체 한 건을 상세 조회합니다.")
    @GetMapping("/{id}")
    public CowResponse findById(@PathVariable Long id) {
        return cowService.findById(id);
    }

    /*
     * 수정
     */
    @Operation(summary = "수정", description = "개체 정보를 수정합니다.")
    @PutMapping("/{id}")
    public CowResponse update(@PathVariable Long id,
                              @Valid @RequestBody CowUpdateRequest request) {
        return cowService.update(id, request);
    }

    /*
     * 삭제
     */
    @Operation(summary = "삭제", description = "개체 정보를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cowService.delete(id);
        return ResponseEntity.noContent().build();
    }
}