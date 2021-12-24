package com.lu.demo.service;

import com.lu.demo.vo.AdminVO;

import java.util.List;

public interface AdminService {

    AdminVO selectAdminVOByUserId(Long userId);

    void updatePassword(Long userId, String password);

    Integer selectRoleLengthByRoleRank(Integer roleRank);

    List<AdminVO> selectUserByWhere(Long userId,String username, String phone, Integer floorId, Integer page, Integer limit, Integer roleRank);

    Integer insertAdmin(AdminVO adminVO);

    Integer deleteAdmin(Long userId);

    Integer updateAdmin(AdminVO adminVO);
}
