package com.muskmelon.modules.zookeeper.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.logging.log4j.util.Strings;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-5 22:34
 * @since 1.0
 */
public class ZKUtil {

    private static Logger logger = LoggerFactory.getLogger(ZKUtil.class);

    private static CuratorFramework curatorFramework;

    /**
     * 重试策略：休眠1秒重试，最大重试3次
     */
    private static ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000,
            3, Integer.MAX_VALUE);

    /**
     * 连接zk服务
     *
     * @param connectString 连接信息 ip:port
     */
    public static boolean connect(String connectString) {
        logger.info("******连接zk服务******");
        logger.info("连接信息：{}", connectString);
        try {
            curatorFramework = CuratorFrameworkFactory.newClient(connectString, retry);
            curatorFramework.start();
            if (CuratorFrameworkState.STARTED.equals(curatorFramework.getState())) {
                logger.info("连接服务成功,连接信息：{}", connectString);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("连接失败,检查连接信息是否正确", e);
            return false;
        }
    }

    /**
     * 关闭连接
     */
    public static void close() {
        logger.info("******关闭zk连接******");
        curatorFramework.close();
    }

    /**
     * 校验是否已连接zk服务
     */
    public static void validate() {
        if (curatorFramework == null || CuratorFrameworkState.STOPPED.equals(curatorFramework.getState())) {
            throw new RuntimeException("未连接zk服务");
        }
    }

    /**
     * 查询某个路径下的节点的值
     *
     * @param path 节点路径
     */
    public static String getNodeValueByPath(String path) {
        try {
            byte[] value = curatorFramework.getData().forPath(path);
            if (null == value) {
                return Strings.EMPTY;
            }
            return new String(value);
        } catch (Exception e) {
            logger.error("查找节点的值失败,path={}", path, e);
            return Strings.EMPTY;
        }
    }

    /**
     * 获取路径下所有子节点
     *
     * @param path 节点路径
     */
    public static List<String> getNodeChildren(String path) throws Exception {
        try {
            validate();
            List<String> nodes = curatorFramework.getChildren().forPath(path);
            return nodes;
        } catch (Exception e) {
            logger.error("查找路径下的子节点失败,path={}", path, e);
            throw e;
        }
    }


    /**
     * 创建zk节点
     *
     * @param path      节点路径
     * @param nodeValue 节点值
     */
    public static boolean createNode(String path, String nodeValue) throws Exception {
        try {
            validate();
            String nodePath = curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, nodeValue.getBytes());
            logger.info("节点创建成功,nodePath={},value={}", nodePath, nodeValue);
            return true;
        } catch (Exception e) {
            int index = path.lastIndexOf("/") + 1;
            String nodeName = path.substring(index);
            path = path.substring(0, index);
            logger.error("在path={}下创建节点nodeName={}失败,节点value={}", path, nodeName, nodeValue, e);
            throw e;
        }
    }

    /**
     * 更新zk节点
     *
     * @param path      zk节点路径
     * @param nodeValue 更新的节点值
     */
    public static boolean updateNode(String path, String nodeValue) throws Exception {
        try {
            validate();
            if (StringUtils.isBlank(nodeValue)) {
                curatorFramework.setData().forPath(path);
            } else {
                String oldValue = getNodeValueByPath(path);
                curatorFramework.setData().forPath(path, nodeValue.getBytes());
                logger.info("节点更新成功,nodePath={},oldValue={},value={}", path, oldValue, nodeValue);
            }
            return true;
        } catch (Exception e) {
            int index = path.lastIndexOf("/") + 1;
            String nodeName = path.substring(index);
            path = path.substring(0, index);
            logger.error("在path={}下更新节点nodeName={}失败,节点value={}", path, nodeName, nodeValue, e);
            throw e;
        }
    }

    /**
     * 删除zk节点
     *
     * @param path 节点zk路径
     */
    public static boolean deleteNode(String path) throws Exception {
        try {
            validate();
            curatorFramework.delete().forPath(path);
            logger.info("删除节点path={}成功", path);
            return true;
        } catch (Exception e) {
            logger.error("删除节点path={}失败", path, e);
            throw e;
        }
    }

    /**
     * 校验节点是否存在
     *
     * @param path 路径
     * @return
     * @throws Exception
     */
    public static boolean checkExist(String path) throws Exception {
        try {
            validate();
            Stat stat = getNodeStat(path);
            if (null == stat) {
                logger.info("节点path={}不存在", path);
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.error("删除节点path={}失败", path, e);
            throw e;
        }
    }

    /**
     * 获取节点信息
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static Stat getNodeStat(String path) throws Exception {
        return curatorFramework.checkExists().forPath(path);
    }

}
