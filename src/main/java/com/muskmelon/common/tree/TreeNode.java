package com.muskmelon.common.tree;

import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-6 22:06
 * @since 1.0
 */
public class TreeNode {

    private String name;
    private boolean open;
    private String pathName;
    private List<TreeNode> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
