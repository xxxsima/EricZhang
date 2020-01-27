package com.zhang.service;

import com.zhang.model.SystemLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-12-03 21:03
 * @Desc:
 **/

public interface SystemLogService {
    void save(SystemLog entity);

    List<SystemLog> findAll();

    SystemLog getById(Long id);

    List<SystemLog> fuzzyQuery(@Param(value = "fuzzy")String fuzzy,@Param(value = "pageNum")Integer pageNum,@Param(value = "pageSize") Integer pageSize);
    Integer findCount(@Param(value = "fuzzy")String fuzzy);

}
