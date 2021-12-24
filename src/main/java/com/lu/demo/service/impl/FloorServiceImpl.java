package com.lu.demo.service.impl;

import com.lu.demo.mapper.FloorMapper;
import com.lu.demo.model.Floor;
import com.lu.demo.service.FloorService;
import com.lu.demo.vo.FloorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {

    @Autowired
    private FloorMapper floorMapper;

    @Override
    public List<Floor> selectFloorAll(Integer page,Integer limit) {
        return floorMapper.selectFloorAll(page,limit);
    }

    @Override
    @Transactional
    public Integer insertFloor(Floor floor) {
        return floorMapper.insertFloor(floor);
    }

    @Override
    @Transactional
    public Integer updateFloor(FloorVO floor) {
        return floorMapper.updateFloor(floor);
    }

    @Override
    @Transactional
    public Integer deleteFloor(Integer floorId) {
        return floorMapper.deleteFloor(floorId);
    }

    @Override
    public Integer floorLength() {
        return floorMapper.floorLength();
    }
}
