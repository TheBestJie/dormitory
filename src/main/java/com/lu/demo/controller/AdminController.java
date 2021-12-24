package com.lu.demo.controller;

import com.google.gson.Gson;
import com.lu.demo.service.AdminService;
import com.lu.demo.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/dormitory")
public class AdminController {

    @Autowired
    private AdminService adminService;


    /**
     * 登录操作
     * @param userId
     * @param password
     * @param remember
     * @param session
     * @param response
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(
            String userId, String password, String remember,
            HttpSession session, HttpServletResponse response , HttpServletRequest request
    ){
        ModelAndView modelAndView = new ModelAndView();
        //----------------------------------------------------------
        //验证是否为空
        if(userId == null || "".equals(userId)){
            modelAndView.addObject("error","用户名不能为空");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        if(password == null || "".equals(password)){
            modelAndView.addObject("error","密码不能为空");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        //验证输入的用户名是否为纯数字
        Long _userId = null;
        try {
            _userId = Long.parseLong(userId);
        }catch (Exception e){
            e.printStackTrace();
            modelAndView.addObject("error","用户名错误，请重新输入");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        //----------------------------------------------------------

        AdminVO adminVO = adminService.selectAdminVOByUserId(_userId);
        //验证用户名和密码是否正确
        if(adminVO == null || !adminVO.getPassword().equals(password)){
            modelAndView.addObject("error","用户名或密码错误");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        //验证用户名的权限
        if(adminVO.getRoleRank() > 2){
            modelAndView.addObject("error","您不是管理员");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        //保存session
        session.setAttribute("adminVO", adminVO);
        //保存cookie
        if(remember!=null && "on".equals(remember)){
            Cookie ckUserId = new Cookie("ckUserId",userId);
            ckUserId.setMaxAge(24 * 60 * 60);
            ckUserId.setPath(request.getContextPath());
            response.addCookie(ckUserId);
            Cookie ckPassword = new Cookie("ckPassword",password);
            ckUserId.setMaxAge(24 * 60 * 60);
            ckUserId.setPath(request.getContextPath());
            response.addCookie(ckPassword);
        }else {
            //清除cookie
            this.deleteCookie(request,response);
        }

        modelAndView.addObject("adminVO",adminVO);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/exit")
    public String exit(HttpServletRequest request,HttpServletResponse response){
        //清除cookie
        this.deleteCookie(request,response);
        //清除session
        request.getSession().setAttribute("adminVO",null);
        return "login";
    }

    /**
     * 修改密码
     * @param session
     * @param request
     * @param response
     * @param model
     * @param oldPassword
     * @param newPassword
     * @param newPasswordAgain
     * @return
     */
    @PostMapping("/updatePassword")
    public String updatePassword(
            HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model,
            String oldPassword, String newPassword, String newPasswordAgain
    ){
        if(
                oldPassword == null || newPassword == null || newPasswordAgain == null
                        || "".equals(oldPassword) || "".equals(newPassword) || "".equals(newPasswordAgain))
        {
            model.addAttribute("error","密码不能为空");
            return "updatePassword";
        }

        if (!newPassword.equals(newPasswordAgain)){
            model.addAttribute("error","密码不一致");
            return "updatePassword";
        }


        AdminVO adminVO = (AdminVO)session.getAttribute("adminVO");
        if(!adminVO.getPassword().equals(oldPassword)){
            model.addAttribute("error","原密码错误");
            return "updatePassword";

        }
        //更新session
        adminVO.setPassword(newPassword);
        session.setAttribute("adminVO",adminVO);
        //更新cookie
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if("ckPassword".equals(cookie.getName())){
                    cookie.setValue(newPassword);
                    response.addCookie(cookie);
                }
            }
        }
        adminService.updatePassword(adminVO.getUserId(),adminVO.getPassword());
        return "redirect:/dormitory/home";
    }

    //直接返回json数据
    @RequestMapping("/selectAdmin")
    @ResponseBody
    public String selectAdmin(Long userId,String username,String phone,Integer floorId,Integer page,Integer limit){
       return selectDUser(userId,username,phone,floorId,page,limit,2);
    }

    @PostMapping("/insertAdmin")
    @ResponseBody
    public Integer insertAdmin(AdminVO adminVO){
        return adminService.insertAdmin(adminVO);

    }

    @PostMapping("/deleteAdmin")
    @ResponseBody
    public Integer deleteAdmin(Long userId){
        return adminService.deleteAdmin(userId);
    }

    @PostMapping("/updateAdmin")
    @ResponseBody
    public Integer updateAdmin(AdminVO adminVO){
        return adminService.updateAdmin(adminVO);
    }


    public String selectDUser(Long userId,String username,String phone,Integer floorId,Integer page,Integer limit,Integer roleRank){
        List<AdminVO> adminVOList = adminService.selectUserByWhere(userId,username,phone,floorId,(page-1)*10,limit,roleRank);
        Integer count = adminService.selectRoleLengthByRoleRank(roleRank);
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

    /**
     * 删除cookie
     * @param request
     * @param response
     */
    public void deleteCookie(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if("ckUserId".equals(cookie.getName()) || "ckPassword".equals(cookie.getName())){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

}
