package com.lu.demo;

import com.lu.demo.mapper.AbsentMapper;
import com.lu.demo.mapper.RoleListMapper;
import com.lu.demo.model.RoleList;
import com.lu.demo.service.AdminService;
import com.lu.demo.vo.AbsentVO;
import com.lu.demo.vo.AdminVO;
import javafx.scene.input.DataFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DormitoryApplicationTests {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AbsentMapper absentMapper;

    @Test
    void contextLoads() {
        for (int i = 0; i < 100; i++) {
            AdminVO adminVO = new AdminVO();
            adminVO.setUserId((long)(100400 + i));
            adminVO.setUsername("student"+(400+i));
            adminVO.setPassword("123456");
            adminVO.setSex("男");
            adminVO.setPhone("12345678912");
            adminVO.setFloorId(5);
            adminVO.setRoleRank(3);
             adminService.insertAdmin(adminVO);
        }

//        List<AbsentVO> adminVOS = absentMapper.selectAbsentAll();
//        String date = adminVOS.get(0).getDate();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String str = simpleDateFormat.format(date);
//        System.out.println(date);
    }

}
