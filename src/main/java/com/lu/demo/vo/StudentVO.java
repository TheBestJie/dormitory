package com.lu.demo.vo;

import com.lu.demo.model.Absent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentVO {
    private Long userId;
    private String username;
    private String password;
    private String sex;
    private String phone;
    private Integer floorId;
    private Integer roleRank;
    private List<Absent> absentList;
}
