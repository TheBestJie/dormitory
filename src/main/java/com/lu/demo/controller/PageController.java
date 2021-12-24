package com.lu.demo.controller;

import com.lu.demo.model.RolePath;
import com.lu.demo.service.RolePathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dormitory")
public class PageController {

    @Autowired
    private RolePathService rolePathService;

    @GetMapping("/login")
    public String login(HttpSession session){
        List<RolePath> rolePaths = rolePathService.selectRolePathAll();
        Map<Integer,List<String>> map = new HashMap<>();
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        List<String> l3 = new ArrayList<>();
        for (RolePath rolePath : rolePaths) {
            switch (rolePath.getRoleRank()){
                case 1:
                    l1.add(rolePath.getPath());
                    break;
                case 2:
                    l2.add(rolePath.getPath());
                    break;
                case 3:
                    l3.add(rolePath.getPath());
                    break;
            }
        }
        map.put(1,l1);
        map.put(2,l2);
        map.put(3,l3);
        session.setAttribute("path",map);
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/student")
    public String student(){
        return "student";
    }

    @GetMapping("/floor")
    public String floor(){
        return "floor";
    }

    @GetMapping("/absent")
    public String absent(){
        return "absent";
    }


    @GetMapping("/updatePassword")
    public String updatePassword(){
        return "updatePassword";
    }

    @GetMapping("/updateMyInformation")
    public String updateMyInformation(){
        return "updateMyInformation";
    }
}
