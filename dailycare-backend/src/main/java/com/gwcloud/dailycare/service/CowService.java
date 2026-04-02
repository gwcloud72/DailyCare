package com.gwcloud.dailycare.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwcloud.dailycare.dto.CowCreateRequest;
import com.gwcloud.dailycare.dto.CowResponse;
import com.gwcloud.dailycare.dto.CowUpdateRequest;
import com.gwcloud.dailycare.entity.Cow;
import com.gwcloud.dailycare.exception.CowNotFoundException;
import com.gwcloud.dailycare.exception.DuplicateEarTagException;
import com.gwcloud.dailycare.repository.CowRepository;

import lombok.RequiredArgsConstructor;

/*
 * CRUD로직
 */
@Service
@RequiredArgsConstructor
public class CowService {

    private final CowRepository cowRepository;

    /*
     * 젖소 등록
     */
    @Transactional
    public CowResponse create(CowCreateRequest request) {

        String earTag = request.getEarTag().trim();

        if (cowRepository.existsByEarTag(earTag)) {
            throw new DuplicateEarTagException();
        }

        Cow cow = Cow.builder()
                .earTag(earTag)
                .name(request.getName().trim())
                .birthDate(request.getBirthDate())
                .breed(request.getBreed())
                .status(normalizeStatus(request.getStatus()))
                .memo(request.getMemo())
                .build();

        Cow savedCow = cowRepository.save(cow);
        return CowResponse.from(savedCow);
    }

    /*
     * 전체 목록 조회
     */
    @Transactional(readOnly = true)
    public List<CowResponse> findAll() {
        List<Cow> cows = cowRepository.findAll();
        List<CowResponse> result = new ArrayList<>();

        for (Cow cow : cows) {
            result.add(CowResponse.from(cow));
        }

        return result;
    }

    /*
     * 상세 조회
     */
    @Transactional(readOnly = true)
    public CowResponse findById(Long id) {
        Cow cow = findCowEntity(id);
        return CowResponse.from(cow);
    }

    /*
     * 수정
     */
    @Transactional
    public CowResponse update(Long id, CowUpdateRequest request) {

        Cow cow = findCowEntity(id);

        String earTag = request.getEarTag().trim();

        // 자기 자신(id)은 제외하고 같은 개체번호가 있는지 확인
        if (cowRepository.existsByEarTagAndIdNot(earTag, id)) {
            throw new DuplicateEarTagException();
        }

        cow.updateCow(
                earTag,
                request.getName().trim(),
                request.getBirthDate(),
                request.getBreed(),
                normalizeStatus(request.getStatus()),
                request.getMemo()
        );

        return CowResponse.from(cow);
    }

    /*
     * 삭제
     */
    @Transactional
    public void delete(Long id) {
        Cow cow = findCowEntity(id);
        cowRepository.delete(cow);
    }

    /*
     * 공통 조회 메서드
     * 없으면 커스텀 예외 발생
     */
    private Cow findCowEntity(Long id) {
        Cow cow = cowRepository.findById(id).orElse(null);

        if (cow == null) {
            throw new CowNotFoundException();
        }

        return cow;
    }

    /*
     * 상태값이 비어 있으면 기본값 NORMAL 사용
     */
    private String normalizeStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            return "NORMAL";
        }
        return status.trim();
    }
}