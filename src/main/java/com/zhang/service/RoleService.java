package com.zhang.service;

import com.zhang.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: King
 * @Create: 2019-11-29 13:28
 * @Desc:
 **/
public interface RoleService {
    void  insert(Role entity);

    void delete(Long id);

    void update(Role entity);

    Role  getById(Long id);

    List<Role> fuzzyQuery (@Param(value = "fuzzy") String fuzzy,@Param(value = "pageNum") Integer pageNum,@Param(value = "pageSize") Integer pageSize);

    Integer findCount(@Param(value = "fuzzy") String fuzzy);

    List<Role> findAll();
}
