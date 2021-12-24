package com.lu.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminVO {
    private Long userId;
    private String username;
    private String password;
    private String sex;
    private String phone;
    private Integer floorId;
    private Integer roleRank;
    private List<ListVO> listVOS;
}
//[ListVO(listName=宿舍管理员管理, listUrl=/admin), ListVO(listName=学生管理, listUrl=/student), ListVO(listName=舍楼管理, listUrl=/floor), ListVO(listName=缺勤管理, listUrl=/absent)]