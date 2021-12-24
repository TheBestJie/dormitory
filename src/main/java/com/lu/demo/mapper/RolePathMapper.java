package com.lu.demo.mapper;

import com.lu.demo.model.RolePath;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface RolePathMapper {

    @Select("select `id`,`role_rank`,`path` from `role_path`")
    @Results(value = {
            @Result(property = "id",column = "id",javaType = Long.class,jdbcType = JdbcType.BIGINT),
            @Result(property = "roleRank",column = "role_rank",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
            @Result(property = "path",column = "path",javaType = String.class,jdbcType = JdbcType.VARCHAR)
    })
    List<RolePath> selectRolePathAll();
}
