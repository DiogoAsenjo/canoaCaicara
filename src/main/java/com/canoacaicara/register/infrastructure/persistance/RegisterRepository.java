package com.canoacaicara.register.infrastructure.persistance;

import com.canoacaicara.common.enums.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RegisterRepository extends JpaRepository<RegisterEntity, Integer> {
    List<RegisterEntity> findByUserId(int userId);
    List<RegisterEntity> findByUserIdAndDateAndActivityType(int userId, LocalDate date, ActivityType activityType);
    List<RegisterEntity> findByUserIdAndDateBetween(int userId, LocalDate startOfMonth, LocalDate endOfMonth);
    List<RegisterEntity> findByUserIdAndDateAndActivityTypeAndIdNot(int userId, LocalDate date, ActivityType activityType, int registerId);
}
