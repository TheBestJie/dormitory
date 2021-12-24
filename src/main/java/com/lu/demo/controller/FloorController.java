package com.lu.demo.controller;

import com.google.gson.Gson;
import com.lu.demo.model.Floor;
import com.lu.demo.service.FloorService;
import com.lu.demo.vo.FloorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dormitory")
public class FloorController {

    @Autowired
    private FloorService floorService;

    @RequestMapping("/selectFloor")
    @ResponseBody
    public String selectFloor(Integer page,Integer limit){
        List<Floor> floorList = floorService.selectFloorAll((page-1)*10,limit);
        Integer count = floorService.floorLength();
        Gson gson = new Gson();
        String json = gson.toJson(floorList);
        StringBuffer buffer = new StringBuffer("{");
        buffer.append("\"code\":");
        buffer.append("0,");
        buffer.append("\"msg\":");
        buffer.append("\"\",");
        buffer.append("\"count\":");
        buffer.append(count);
        buffer.append(",");
        buffer.append("\"data\":");
        buffer.append(json);
        buffer.append("}");
        return buffer.toString();
    }

    @PostMapping("/insertFloor")
    @ResponseBody
    public Integer insertFloor(Floor floor){
        return floorService.insertFloor(floor);
    }

    @PostMapping("/updateFloor")
    @ResponseBody
    public Integer updateFloor(FloorVO floor){
        return floorService.updateFloor(floor);
    }

    @PostMapping("/deleteFloor")
    @ResponseBody
    public Integer deleteFloor(Integer floorId){
        return floorService.deleteFloor(floorId);
    }

}
