package com.muskmelon.modules.zookeeper.service;

import java.util.List;

/**
 * @author muskmelon
 * @description zk服务接口
 * @date 2020-2-5 22:13
 * @since 1.0
 */
public interface ZKService {

    /**
     * 连接zk服务
     * @param connectString 连接信息 ip:port
     */
    boolean connect(String connectString);

    /**
     * 关闭zk连接
     */
    void close();

    /**
     * 查询节点下的子节点
     * @param path 节点路径
     * @return 节点下的子节点名称
     * @throws Exception 抛出异常
     */
    List<String> listNodeChildren(String path) throws Exception;

    /**
     * 查询节点对应的值
     * @param path 节点路径
     * @return 节点值
     */
    String getNodeValueByPath(String path);

    /**
     * 创建zk节点
     * @param path 节点所在路径
     * @param nodeValue 节点value
     * @return 是否创建成功
     */
    boolean createNode(String path,String nodeValue) throws Exception;

    /**
     * 更新zk节点
     * @param path 节点所在路径
     * @param nodeValue 节点value
     * @return 是否更新成功
     */
    boolean updateNode(String path,String nodeValue) throws Exception;

    /**
     * 删除zk节点
     * @param path 节点路径
     * @return 是否删除成功
     */
    boolean deleteNode(String path) throws Exception;
}
