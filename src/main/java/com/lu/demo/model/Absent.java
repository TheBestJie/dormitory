package com.lu.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Absent {
    private Long id;
    private Long userId;
    private String date;
    private String desc;
}
