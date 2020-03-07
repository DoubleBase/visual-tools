package com.muskmelon.common.tree;

import org.apache.commons.lang3.StringUtils;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-6 22:23
 * @since 1.0
 */
public class TreeUtil {

    /**
     * 获取根节点
     *
     * @return
     */
    public static TreeNode getRootNode() {
        TreeNode treeNode = new TreeNode();
        treeNode.setName(Constants.TREE_ROOT);
        treeNode.setPathName("/");
        treeNode.setOpen(true);
        return treeNode;
    }

    /**
     * 获取树节点路径
     *
     * @param parentPath 父节点路径
     * @return
     */
    public static String getPath(String parentPath, String nodeName) {
        if (Constants.TREE_ROOT.equals(parentPath)) {
            return "/" + nodeName;
        } else {
            return parentPath + "/" + nodeName;
        }
    }

    public static String getNameFromPath(String path) {
        int index = path.lastIndexOf("/");
        if (index >= 0) {
            return path.substring(index + 1);
        }
        return StringUtils.EMPTY;
    }

}
