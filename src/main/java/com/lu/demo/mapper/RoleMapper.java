package com.lu.demo.mapper;

import com.lu.demo.model.Role;
import com.lu.demo.vo.AdminVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface RoleMapper {

    @Select(
            "SELECT `id`,`user_id`,`role_rank` FROM `role`"
    )
    @Results(
            id = "Role",
            value = {
                    @Result(property = "id",column = "id",javaType = Long.class,jdbcType = JdbcType.BIGINT),
                    @Result(property = "userId",column = "user_id",javaType = Long.class,jdbcType = JdbcType.BIGINT),
                    @Result(property = "roleRank",column = "role_rank",javaType = Integer.class,jdbcType = JdbcType.INTEGER)
            }
    )
    List<Role> selectRoleAll();



    @Select(
            "SELECT `id`,`user_id`,`role_rank` FROM `role` WHERE `user_id` = #{userId}"
    )
    @ResultMap("Role")
    Role selectRoleByDUserId(Long userId);

    @Select(
            "SELECT `id`,`user_id`,`role_rank` FROM `role` WHERE `role_rank` = #{roleRank}"
    )
    @ResultMap("Role")
    Role selectRoleByRoleRank(Integer roleRank);

    @Select("SELECT COUNT(*) FROM `role` WHERE `role_rank` = #{roleRank}")
    Integer selectRoleLengthByRoleRank(Integer roleRank);

    @Insert("INSERT INTO `role`(`user_id`,`role_rank`) VALUES(#{userId},#{roleRank})")
    @ResultMap("role")
    Integer insertRole(AdminVO adminVO);

    @Delete("DELETE FROM `role` WHERE `user_id` = #{userId}")
    Integer deleteRole(Long userId);

    @Update("UPDATE role SET `role_rank` = #{roleRank} WHERE `user_id` = #{userId}")
    @ResultMap("Role")
    Integer updateRole(AdminVO adminVO);
}
