package com.lu.demo.interceptor;

import com.lu.demo.service.AdminService;
import com.lu.demo.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
public class MyInterceptor implements HandlerInterceptor {


    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<Integer, List<String>> map = (Map<Integer, List<String>>) request.getSession().getAttribute("path");
        AdminVO adminVO =  (AdminVO) request.getSession().getAttribute("adminVO");

        //验证session是否存在
        if(adminVO != null) {
            //验证是否有权限访问该路径
            List<String> paths = map.get(adminVO.getRoleRank());
            String servletPath = request.getServletPath();
            if(paths.contains(servletPath)){
                return true;
            }else {
                return false;
            }
        }
        //不存在则查看cookie
        Cookie[] cookies = request.getCookies();
        Long userId = null;
        String password = null;
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if("ckUserId".equals(cookie.getName())){
                    userId = Long.parseLong(cookie.getValue());
                }
                if("ckPassword".equals(cookie.getName())){
                    password = cookie.getValue();
                }
            }
            //存在则将cookie中的信息与数据库中的数据校验
            //一致则生产session
            if(userId!=null){
                AdminVO adminVO1 = adminService.selectAdminVOByUserId(userId);
                if(password!=null && adminVO1 !=null && adminVO1.getPassword().equals(password)){
                    request.getSession().setAttribute("adminVO", adminVO);
                    List<String> paths = map.get(adminVO1.getRoleRank());
                    String servletPath = request.getServletPath();
                    if(paths.contains(servletPath)){
                        return true;
                    }else {
                        return false;
                    }
                }
            }
        }

        //转发到登录页面
        request.getRequestDispatcher("/dormitory/login").forward(request,response);
        return false;
    }
}
