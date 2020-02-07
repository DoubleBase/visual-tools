package com.muskmelon.modules.connect.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-6 23:30
 * @since 1.0
 */
@Entity
@Table(name = "connect_info")
@Data
public class ConnectInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "info", nullable = false)
    private String info;

    @Column(name = "type")
    private Integer type;

}
