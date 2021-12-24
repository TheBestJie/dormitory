package com.lu.demo.controller;

import com.google.gson.Gson;
import com.lu.demo.service.AbsentService;
import com.lu.demo.vo.AbsentVO;
import com.lu.demo.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dormitory")
public class AbsentController {

    @Autowired
    private AbsentService absentService;

    @RequestMapping("/selectAbsent")
    @ResponseBody
    public String selectAbsent(String username,String date, Integer page,Integer limit){
        List<AbsentVO> adminVOList = absentService.selectAbsentAll(username,date,(page-1)*10,limit);
        Integer count = absentService.selectAbsentLength();
        Gson gson = new Gson();
        String json = gson.toJson(adminVOList);
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

    @PostMapping("/insertAbsent")
    @ResponseBody
    public Integer insertStudent(AbsentVO absentVO){
        return absentService.insertAbsent(absentVO);

    }

    @PostMapping("/deleteAbsent")
    @ResponseBody
    public Integer deleteStudent(Long id){
        return absentService.deleteAbsent(id);
    }
}
