package com.muskmelon.modules.system.service;

import com.muskmelon.common.page.PageDto;
import com.muskmelon.common.page.PageInfo;
import com.muskmelon.modules.system.domain.OperatorLog;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-9 20:16
 * @since 1.0
 */
public interface OperatorLogService {

    /**
     * 分页查询操作日志
     * @return
     */
    PageInfo<OperatorLog> listOperatorLog(PageDto pageDto);

    /**
     * 添加log
     * @param log
     * @return 返回主键ID
     */
    Integer addOperatorLog(OperatorLog log);

    /**
     * 更新log
     * @param log
     */
    void updateOperatorLog(OperatorLog log);

}
