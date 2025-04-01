package com.canoacaicara.groups.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupUserRelationRepository extends JpaRepository<GroupUserRelationEntity, Integer> {
}
