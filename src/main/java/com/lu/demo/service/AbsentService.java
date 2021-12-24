package com.lu.demo.service;

import com.lu.demo.vo.AbsentVO;

import java.util.List;

public interface AbsentService {

    List<AbsentVO> selectAbsentAll(String username, String date, Integer page,Integer limit);

    Integer selectAbsentLength();

    Integer insertAbsent(AbsentVO absentVO);

    Integer deleteAbsent(Long id);
}
