package com.lu.demo.mapper;

import com.lu.demo.model.RoleList;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


public interface RoleListMapper {


    @Select("SELECT `id`,`list_name`,`list_url`,`role_rank_list` FROM `role_list`")
    @Results(
            id = "RoleList",
            value = {
                    @Result(property = "id",column = "id",javaType = Long.class,jdbcType = JdbcType.BIGINT),
                    @Result(property = "listName",column = "list_name",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(property = "listUrl",column = "list_url",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(property = "roleRankList",column = "role_rank_list",javaType = String.class,jdbcType = JdbcType.VARCHAR)
            }
    )
    List<RoleList> selectRoleListAll();



}
