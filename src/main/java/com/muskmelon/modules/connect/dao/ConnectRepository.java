package com.muskmelon.modules.connect.dao;

import com.muskmelon.modules.connect.domain.ConnectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-6 23:56
 * @since 1.0
 */
@Transactional(rollbackFor = Exception.class)
public interface ConnectRepository extends JpaRepository<ConnectInfo, Integer> {
}
