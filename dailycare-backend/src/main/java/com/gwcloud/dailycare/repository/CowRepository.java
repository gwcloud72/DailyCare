package com.gwcloud.dailycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gwcloud.dailycare.entity.Cow;

/*
 * 개체 엔티티 DB 접근 Repository
 */
public interface CowRepository extends JpaRepository<Cow, Long> {

    // 등록할 때 같은 개체번호가 이미 있는지 확인
    boolean existsByEarTag(String earTag);

    // 수정할 때 자기 자신을 제외하고 같은 개체번호가 있는지 확인
    boolean existsByEarTagAndIdNot(String earTag, Long id);
}