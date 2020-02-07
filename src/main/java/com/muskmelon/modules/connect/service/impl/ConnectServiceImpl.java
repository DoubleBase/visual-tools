package com.muskmelon.modules.connect.service.impl;

import com.muskmelon.modules.connect.dao.ConnectRepository;
import com.muskmelon.modules.connect.domain.ConnectInfo;
import com.muskmelon.modules.connect.service.ConnectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-6 23:29
 * @since 1.0
 */
@Service("connectService")
public class ConnectServiceImpl implements ConnectService {

    @Resource
    private ConnectRepository connectRepository;

    @Override
    public List<ConnectInfo> listConnectInfo() {
        return connectRepository.findAll();
    }

    @Override
    public void saveOrUpdateConnectInfo(ConnectInfo connectInfo) {
        connectRepository.saveAndFlush(connectInfo);
    }

    @Override
    public void deleteConnectInfo(Integer id) {
        connectRepository.deleteById(id);
    }
}
