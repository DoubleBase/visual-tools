package com.muskmelon.common.enums;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-6 23:32
 * @since 1.0
 */
public enum ConnectType {

    /**
     * Zookeeper
     */
    ZOOKEEPER(1),

    /**
     * Redis
     */
    REDIS(2);

    private int type;

    ConnectType(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }}
