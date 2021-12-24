package com.lu.demo.service.dynamicSql;

import com.lu.demo.vo.FloorVO;

public class Dynamic {

    public String selectDUserDynamic(Long userId,String username,String phone,Integer floorId,Integer page,Integer limit,Integer roleRank){
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT `d_user`.`id`,`d_user`.`user_id`,`d_user`.`username`,`d_user`.`password`,`d_user`.`sex`,`d_user`.`phone`,`d_user`.`floor_id`,`role`.`role_rank` FROM `d_user` LEFT JOIN `role` ON `d_user`.`user_id` = `role`.`user_id` " +
                "WHERE 1 = 1 ");
        if(userId!=null){
            buffer.append("AND `d_user`.`user_id` = #{userId} ");
        }
        if(username!=null && !"".equals(username)){
            buffer.append("AND `username` = #{username} ");
        }
        if(phone!=null && !"".equals(phone)){
            buffer.append("AND `phone` = #{phone} ");
        }
        if(floorId!=null){
            buffer.append("AND `floor_id` = #{floorId} ");
        }
        if(roleRank!=null){
            buffer.append("AND `role_rank` = #{roleRank} ");
        }
        buffer.append("ORDER BY `id` DESC ");
        if(page!=null && limit!=null){
            buffer.append("LIMIT #{page},#{limit}");
        }
        return buffer.toString();
    }

    public String updateFloor(FloorVO floorVO){
        StringBuffer buffer = new StringBuffer("update `floor` set ");
        if(floorVO.getFloorId()!=null){
            buffer.append("`floor_id` = #{floorId}");
        }
        if(floorVO.getDesc()!=null && !"".equals(floorVO.getDesc())){
            if(buffer.toString().substring(buffer.length()-1).equals("}")){
                buffer.append(",`desc` = #{desc}");
            }else {
                buffer.append("`desc` = #{desc}");
            }
        }
        buffer.append("where `floor_id` = #{oldFloorId}");
        return buffer.toString();
    }

    public String selectFloorAll(Integer page,Integer limit){
        StringBuffer buffer = new StringBuffer("select `id`,`floor_id`,`desc` from `floor` ");
        buffer.append("ORDER BY `id` DESC ");
        if(page!=null && limit!=null){
            buffer.append("LIMIT #{page},#{limit} ");
        }
        return buffer.toString();
    }

    public String selectAbsentAll(String username, String date, Integer page,Integer limit){
        StringBuffer buffer = new StringBuffer("select `absent`.`id`,`absent`.`user_id`,`d_user`.`username`,`absent`.`date`,`absent`.`desc` from `absent` left join `d_user` on `absent`.`user_id` = `d_user`.`user_id` where 1 = 1 ");
        if(username!=null && !"".equals(username)){
            buffer.append("and `d_user`.`username` = #{username} ");
        }
        if(date!=null && !"".equals(date)){
            buffer.append("and `absent`.`date` = #{date} ");
        }
        buffer.append("ORDER BY `absent`.`id` DESC ");
        if(page!=null && limit!=null){
            buffer.append("LIMIT #{page},#{limit} ");
        }
        return buffer.toString();
    }

}
