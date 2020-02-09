package com.muskmelon.modules.system.domain;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @author muskmelon
 * @description 操作日志domain
 * @date 2020-2-9 16:12
 * @since 1.0
 */
@Entity
@Table(name = "operator_log")
@Data
public class OperatorLog implements Serializable {

    /**
     * 日志id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * 日志类型 (info,error)
     */
    @Column(name = "type")
    private String type;

    /**
     * 日志标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 日志内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 方法名称
     */
    @Column(name = "method")
    private String method;

    /**
     * 请求地址
     */
    @Column(name = "remote_addr")
    private String remoteAddr;

    /**
     * URI
     */
    @Column(name = "request_uri")
    private String requestUri;

    /**
     * 提交参数
     */
    @Column(name = "params")
    private String params;

    /**
     * 异常信息
     */
    @Column(name = "exception")
    private String exception;

    /**
     * 执行结果
     */
    @Column(name = "result")
    private String result;

    /**
     * 操作时间
     */
    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 执行耗时
     */
    @Column(name = "cost_time")
    private Long costTime;

    /**
     * 操作用户
     */
    @Column(name = "userName")
    private String userName;

    public void setParamFromMap(Map<String, String[]> paramMap) {
        if (null == paramMap) {
            return;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
            params.append((StringUtils.isEmpty(params.toString()) ? "" : "&")).append(param.getKey()).append("=");
            String paramValue = (param.getValue().length > 0) ? param.getValue()[0] : "";
            params.append(paramValue);
        }
        this.params = params.toString();
    }

}
