package com.lu.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsentVO {
    private Long id;
    private Long userId;
    private String username;
    private String date;
    private String desc;
}
