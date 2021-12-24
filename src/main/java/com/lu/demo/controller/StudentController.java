package com.lu.demo.controller;

import com.lu.demo.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dormitory")
public class StudentController {


    @Autowired
    private AdminController adminController;


    @RequestMapping("/selectStudent")
    @ResponseBody
    public String selectStudent(Long userId,String username,String phone,Integer floorId,Integer page,Integer limit){
        return adminController.selectDUser(userId,username,phone,floorId,page,limit,3);
    }

    @PostMapping("/insertStudent")
    @ResponseBody
    public Integer insertStudent(AdminVO adminVO){
        return adminController.insertAdmin(adminVO);

    }

    @PostMapping("/deleteStudent")
    @ResponseBody
    public Integer deleteStudent(Long userId){
        return adminController.deleteAdmin(userId);
    }

    @PostMapping("/updateStudent")
    @ResponseBody
    public Integer updateStudent(AdminVO adminVO){
        return adminController.updateAdmin(adminVO);
    }

}
