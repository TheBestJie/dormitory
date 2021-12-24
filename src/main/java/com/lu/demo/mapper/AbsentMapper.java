package com.lu.demo.mapper;

import com.lu.demo.service.dynamicSql.Dynamic;
import com.lu.demo.vo.AbsentVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

public interface AbsentMapper {


//    @Select("select `absent`.`user_id`,`d_user`.`username`,`absent`.`date`,`absent`.`desc` from `absent` left join `d_user` on `absent`.`user_id` = `d_user`.`user_id` order by `absent`.`id` desc limit #{page},#{limit}")
    @SelectProvider(type = Dynamic.class, method = "selectAbsentAll")
    @Results(
            id = "AbsentVO",
            value = {
                    @Result(property = "id",column = "id",javaType = Long.class,jdbcType = JdbcType.BIGINT),
                    @Result(property = "userId",column = "user_id",javaType = Long.class,jdbcType = JdbcType.BIGINT),
                    @Result(property = "username",column = "username",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(property = "date",column = "date",javaType = String.class,jdbcType = JdbcType.DATE),
                    @Result(property = "desc",column = "desc",javaType = String.class,jdbcType = JdbcType.VARCHAR)
            }
    )
    List<AbsentVO> selectAbsentAll(String username, String date, Integer page,Integer limit);

    @Select("select count(*) from `absent`")
    Integer selectAbsentLength();

    @Insert("insert into `absent`(`user_id`,`date`,`desc`) values(#{userId},#{date},#{desc})")
    @ResultMap("AbsentVO")
    Integer insertAbsent(AbsentVO absentVO);

    @Delete("delete from `absent` where `id` = #{id}")
    Integer deleteAbsent(Long id);
}
