package com.muskmelon.modules.system.dao;

import com.muskmelon.modules.system.domain.OperatorLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-9 20:18
 * @since 1.0
 */
public interface OperatorLogRepository extends JpaRepository<OperatorLog, Integer> {

}
