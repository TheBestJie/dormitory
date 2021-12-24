package com.lu.demo.service.impl;

import com.lu.demo.mapper.AbsentMapper;
import com.lu.demo.service.AbsentService;
import com.lu.demo.vo.AbsentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsentServiceImpl implements AbsentService {

    @Autowired
    private AbsentMapper absentMapper;

    @Override
    public List<AbsentVO> selectAbsentAll(String username, String date, Integer page,Integer limit) {
        List<AbsentVO> absentVOS = absentMapper.selectAbsentAll(username, date, page, limit);
        System.out.println();
        return absentMapper.selectAbsentAll(username, date, page, limit);
    }

    @Override
    public Integer selectAbsentLength() {
        return absentMapper.selectAbsentLength();
    }

    @Override
    public Integer insertAbsent(AbsentVO absentVO) {
        return absentMapper.insertAbsent(absentVO);
    }

    @Override
    public Integer deleteAbsent(Long id) {
        return absentMapper.deleteAbsent(id);
    }
}
