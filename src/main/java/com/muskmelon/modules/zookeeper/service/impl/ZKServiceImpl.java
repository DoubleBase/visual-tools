package com.muskmelon.modules.zookeeper.service.impl;

import com.muskmelon.modules.zookeeper.service.ZKService;
import com.muskmelon.modules.zookeeper.util.ZKUtil;
import org.springframework.stereotype.Service;

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
    public List<String> listNodeChildren(String path) throws Exception {
        return ZKUtil.getNodeChildren(path);
    }

    @Override
    public String getNodeValueByPath(String path) {
        return ZKUtil.getNodeValueByPath(path);
    }

    @Override
    public boolean createNode(String path, String createNode) throws Exception {
        return ZKUtil.createNode(path, createNode);
    }

    @Override
    public boolean updateNode(String path, String nodeValue) throws Exception {
        return ZKUtil.updateNode(path,nodeValue);
    }

    @Override
    public boolean deleteNode(String path) throws Exception {
        return ZKUtil.deleteNode(path);
    }
}
