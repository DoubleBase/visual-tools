package com.muskmelon.modules.system.service.impl;

import com.muskmelon.modules.system.dao.OperatorLogRepository;
import com.muskmelon.modules.system.domain.OperatorLog;
import com.muskmelon.modules.system.service.OperatorLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-9 20:17
 * @since 1.0
 */
@Service("operatorService")
public class OperatorLogServiceImpl implements OperatorLogService {

    @Resource
    private OperatorLogRepository operatorLogRepository;

    @Override
    public List<OperatorLog> listOperatorLog() {
        return operatorLogRepository.findAll();
    }

    @Override
    public Integer addOperatorLog(OperatorLog log) {
        OperatorLog operatorLog = operatorLogRepository.saveAndFlush(log);
        return operatorLog.getId();
    }

    @Override
    public void updateOperatorLog(OperatorLog log) {
        operatorLogRepository.saveAndFlush(log);
    }
}
