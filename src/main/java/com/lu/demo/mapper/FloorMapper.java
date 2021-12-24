package com.lu.demo.mapper;

import com.lu.demo.model.Floor;
import com.lu.demo.service.dynamicSql.Dynamic;
import com.lu.demo.vo.FloorVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface FloorMapper {

//    @Select("select `id`,`floor_id`,`desc` from `floor` order by `id` desc")
    @SelectProvider(type = Dynamic.class, method = "selectFloorAll")
    @Results(
            id = "Floor",
            value = {
                    @Result(property = "id",column = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(property = "floorId",column = "floor_id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(property = "desc",column = "desc",javaType = String.class,jdbcType = JdbcType.VARCHAR)
            }
    )
    List<Floor> selectFloorAll(Integer page,Integer limit);

    @Insert("insert into `floor`(`floor_id`,`desc`) values(#{floorId},#{desc})")
    Integer insertFloor(Floor floor);

    @UpdateProvider(type = Dynamic.class,method = "updateFloor")
    Integer updateFloor(FloorVO floor);

    @Delete("delete from `floor` where `floor_id` = #{floorId}")
    Integer deleteFloor(Integer floorId);

    @Select("select count(*) from floor")
    Integer floorLength();

}
