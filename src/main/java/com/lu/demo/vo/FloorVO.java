package com.lu.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FloorVO {
    private Integer floorId;
    private Integer oldFloorId;
    private String desc;
}
