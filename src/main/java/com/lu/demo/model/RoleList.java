package com.lu.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleList {
    private Long id;
    private String listName;
    private String listUrl;
    private String roleRankList;
}
