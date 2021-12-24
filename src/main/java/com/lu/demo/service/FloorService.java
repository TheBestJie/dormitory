package com.lu.demo.service;

import com.lu.demo.model.Floor;
import com.lu.demo.vo.FloorVO;

import java.util.List;

public interface FloorService {

    List<Floor> selectFloorAll(Integer page,Integer limit);

    Integer insertFloor(Floor floor);

    Integer updateFloor(FloorVO floor);

    Integer deleteFloor(Integer floorId);

    Integer floorLength();

}
