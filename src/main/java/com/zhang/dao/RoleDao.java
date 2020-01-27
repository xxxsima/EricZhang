package com.zhang.dao;

import com.zhang.model.Function;
import com.zhang.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-11-29 13:21
 * @Desc:
 **/
public interface RoleDao {
    void  insert(Role entity);

    void delete (Long id);

    void update (Role entity);

    Role getById(Long id);

    List<Role> fuzzyQuery(Map map);

    List<Role> findAll();

    Integer findCount(@Param(value = "fuzzy") String fuzzy);

    List<Role> exitContactBy(@Param(value = "name")String  name);
}
