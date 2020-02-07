package com.muskmelon.modules.connect.service;

import com.muskmelon.modules.connect.domain.ConnectInfo;

import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-6 23:29
 * @since 1.0
 */
public interface ConnectService {

    /**
     * 查询连接列表
     * @return 返回列表信息
     */
    List<ConnectInfo> listConnectInfo();

    /**
     * 新增或更新连接信息
     * @param connectInfo 连接信息
     */
    void saveOrUpdateConnectInfo(ConnectInfo connectInfo);

    /**
     * 删除连接信息
     * @param id 连接信息ID
     */
    void deleteConnectInfo(Integer id);

}
