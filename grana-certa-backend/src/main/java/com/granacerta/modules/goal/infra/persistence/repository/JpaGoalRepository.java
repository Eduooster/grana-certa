package com.granacerta.modules.goal.infra.persistence.repository;

import com.granacerta.modules.goal.infra.persistence.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGoalRepository extends JpaRepository<GoalEntity, Long> {
}
