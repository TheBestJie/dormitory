package com.lu.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePath {
    private Long id;
    private Integer roleRank;
    private String path;

}
