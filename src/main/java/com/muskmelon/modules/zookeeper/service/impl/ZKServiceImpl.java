package com.muskmelon.modules.zookeeper.service.impl;

import com.google.common.collect.Lists;
import com.muskmelon.common.tree.TreeNode;
import com.muskmelon.common.tree.TreeUtil;
import com.muskmelon.modules.zookeeper.service.ZKService;
import com.muskmelon.modules.zookeeper.util.ZKUtil;
import com.muskmelon.modules.zookeeper.vo.ZKNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-5 22:24
 * @since 1.0
 */
@Service("zkService")
public class ZKServiceImpl implements ZKService {

    @Override
    public boolean connect(String connectString) {
        return ZKUtil.connect(connectString);
    }

    @Override
    public void close() {
        ZKUtil.close();
    }

    @Override
    public List<TreeNode> listNodeChildren(String pathName) throws Exception {
        List<TreeNode> treeNodes = Lists.newArrayList();
        if (StringUtils.isBlank(pathName)) {
            return Collections.singletonList(TreeUtil.getRootNode());
        }
        List<String> nodes = ZKUtil.getNodeChildren(pathName);
        nodes.forEach(node -> {
            TreeNode treeNode = new TreeNode();
            treeNode.setName(node);
            treeNode.setOpen(false);
            treeNode.setPathName(TreeUtil.getPath(pathName, node));
            treeNodes.add(treeNode);
        });
        return treeNodes;
    }

    @Override
    public String getNodeValueByPath(String path) {
        return ZKUtil.getNodeValueByPath(path);
    }

    @Override
    public ZKNode getNodeInfoByPath(String path) throws Exception {
        ZKNode zkNode = new ZKNode();
        Stat stat = ZKUtil.getNodeStat(path);
        if (null != stat) {
            zkNode.setNodeValue(getNodeValueByPath(path));
            zkNode.setCzxid(stat.getCzxid());
            zkNode.setMzxid(stat.getMzxid());
            zkNode.setCtime(stat.getCtime());
            zkNode.setMtime(stat.getMtime());
            zkNode.setVersion(stat.getVersion());
            zkNode.setCversion(stat.getCversion());
            zkNode.setAversion(stat.getAversion());
            zkNode.setEphemeralOwner(stat.getEphemeralOwner());
            zkNode.setDataLength(stat.getDataLength());
            zkNode.setNumChildren(stat.getNumChildren());
            zkNode.setPzxid(stat.getPzxid());
        }
        return zkNode;
    }

    @Override
    public TreeNode createNode(String path, String nodeValue) throws Exception {
        TreeNode treeNode = new TreeNode();
        boolean flag = ZKUtil.createNode(path, nodeValue);
        if (flag) {
            treeNode.setName(TreeUtil.getNameFromPath(path));
            treeNode.setPathName(path);
            treeNode.setOpen(false);
            return treeNode;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateNode(String path, String nodeValue) throws Exception {
        return ZKUtil.updateNode(path, nodeValue);
    }

    @Override
    public boolean updateNodePath(String oldPath, String newPath) throws Exception {
        if (!ZKUtil.checkExist(newPath)) {
            // 新路径不存在,删除老路径,创建新路径
            String value = getNodeValueByPath(oldPath);
            ZKUtil.deleteNode(oldPath);
            ZKUtil.createNode(newPath, value);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteNode(String path) throws Exception {
        return ZKUtil.deleteNode(path);
    }
}
