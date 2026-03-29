package com.gwcloud.dailycare.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gwcloud.dailycare.entity.Cow;

// 개체 엔티티 DB접근을 담당하는 Repository
// JpaRepository를 상속받아 기존 CRUD기능을 사용할수있다.
public interface CowRepository extends JpaRepository<Cow, Long> {
   boolean existsByEarTag(String earTag);
}
