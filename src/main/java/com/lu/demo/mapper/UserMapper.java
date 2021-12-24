package com.lu.demo.mapper;

import com.lu.demo.model.User;
import com.lu.demo.service.dynamicSql.Dynamic;
import com.lu.demo.vo.AdminVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface UserMapper {
    @Select("SELECT `id`,`user_id`,`username`,`password`,`sex`,`phone`,`floor_id` FROM `d_user`")
    @Results(
            id = "User",
            value = {
                    @Result(property = "id",column = "id",javaType = Long.class,jdbcType = JdbcType.BIGINT),
                    @Result(property = "userId",column = "user_id",javaType = Long.class,jdbcType = JdbcType.BIGINT),
                    @Result(property = "username",column = "username",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(property = "password",column = "password",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(property = "sex",column = "sex",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(property = "phone",column = "phone",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(property = "floorId",column = "floor_id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(property = "role",column = "user_id",one = @One(
                            select = "com.lu.demo.mapper.RoleMapper.selectRoleByDUserId"
                        )
                    ),
            }
    )
    List<User> selectUserAll();


    @Select("SELECT `id`,`user_id`,`username`,`password`,`sex`,`phone`,`floor_id` FROM `d_user` WHERE `user_id` = #{userId}")
    @ResultMap("User")
    User selectUserByUserId(Long userId);

    @Update("UPDATE `d_user` SET `password` = #{password} WHERE `user_id` = #{userId}")
    void updatePassword(Long userId, String password);

    @SelectProvider(type = Dynamic.class, method = "selectDUserDynamic")
    @Results(
            id = "adminVO",
            value = {
                    @Result(property = "userId",column = "user_id",javaType = Long.class,jdbcType = JdbcType.BIGINT),
                    @Result(property = "username",column = "username",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(property = "password",column = "password",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(property = "sex",column = "sex",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(property = "phone",column = "phone",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(property = "floorId",column = "floor_id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(property = "roleRank",column = "role_rank",javaType = Integer.class,jdbcType = JdbcType.INTEGER)
            }
    )
    List<AdminVO> selectUserByWhere(Long userId,String username, String phone, Integer floorId, Integer page, Integer limit, Integer roleRank);


    @Insert("INSERT INTO d_user(`user_id`,`username`,`password`,`sex`,`phone`,`floor_id`)\n" +
            "VALUES (#{userId},#{username},#{password},#{sex},#{phone},#{floorId});")
    @ResultMap("adminVO")
    Integer insertUser(AdminVO adminVO);

    @Delete("DELETE FROM `d_user` WHERE `user_id` = #{userId}")
    Integer deleteUser(Long userId);

    @Update(
            "UPDATE `d_user` SET " +
                    "`user_id` = #{userId}," +
                    "`username` = #{username}," +
                    "`password` = #{password}," +
                    "`sex` = #{sex}," +
                    "`phone` = #{phone}," +
                    "`floor_id` = #{floorId} " +
                    "WHERE `user_id` = #{userId}"
    )
    @ResultMap("adminVO")
    Integer updateUser(AdminVO adminVO);
}
