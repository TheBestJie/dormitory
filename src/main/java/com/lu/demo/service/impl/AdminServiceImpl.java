package com.lu.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.lu.demo.mapper.RoleListMapper;
import com.lu.demo.mapper.RoleMapper;
import com.lu.demo.mapper.UserMapper;
import com.lu.demo.model.RoleList;
import com.lu.demo.model.User;
import com.lu.demo.service.AdminService;
import com.lu.demo.vo.AdminVO;
import com.lu.demo.vo.ListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleListMapper roleListMapper;

    private Map<Integer,List<ListVO>> listNameMap;


    //初始化首页列表
    private void selectListNameMap(){
        listNameMap = new HashMap<>();
        List<RoleList> roleListList = roleListMapper.selectRoleListAll();
        List<ListVO> one = new ArrayList<>();
        List<ListVO> two = new ArrayList<>();
        for (RoleList roleList : roleListList) {
            List<Integer> list = JSONArray.parseArray(roleList.getRoleRankList(),Integer.class);
            for (Integer integer : list) {
                switch (integer){
                    case 1:
                        one.add(new ListVO(roleList.getListName(),roleList.getListUrl()));
                        break;
                    case 2:
                        two.add(new ListVO(roleList.getListName(),roleList.getListUrl()));
                        break;
                    default:
                        break;
                }
            }
        }
        listNameMap.put(1,one);
        listNameMap.put(2,two);
    }

    /**
     * 数据装填
     * @param adminVO 视图层
     * @param user 数据层
     */
    private void userForAdminVO(AdminVO adminVO,User user){
        if(adminVO == null || user == null) return;
        adminVO.setUserId(user.getUserId());
        adminVO.setUsername(user.getUsername());
        adminVO.setPassword(user.getPassword());
        adminVO.setSex(user.getSex());
        adminVO.setPhone(user.getPhone());
        adminVO.setFloorId(user.getFloorId());
        adminVO.setRoleRank(user.getRole().getRoleRank());
    }

    /**
     * 通过userId查询数据
     * @param userId
     * @return
     */
    @Override
    public AdminVO selectAdminVOByUserId(Long userId) {
        User user = userMapper.selectUserByUserId(userId);
        if(listNameMap == null){
            this.selectListNameMap();
        }
        AdminVO adminVO = new AdminVO();
        adminVO.setListVOS(listNameMap.get(user.getRole().getRoleRank()));
        this.userForAdminVO(adminVO,user);
        return adminVO;
    }

    @Override
    public void updatePassword(Long userId, String password) {
        userMapper.updatePassword(userId,password);
    }

    @Override
    public Integer selectRoleLengthByRoleRank(Integer roleRank) {
        return roleMapper.selectRoleLengthByRoleRank(roleRank);
    }

    @Override
    public List<AdminVO> selectUserByWhere(Long userId,String username, String phone, Integer floorId, Integer page, Integer limit, Integer roleRank) {
        return userMapper.selectUserByWhere(userId,username,phone,floorId,page,limit,roleRank);
    }


    @Override
    @Transactional
    public Integer insertAdmin(AdminVO adminVO) {
        userMapper.insertUser(adminVO);
        roleMapper.insertRole(adminVO);
        return 1;
    }

    @Override
    @Transactional
    public Integer deleteAdmin(Long userId) {
        roleMapper.deleteRole(userId);
        userMapper.deleteUser(userId);
        return 1;
    }

    @Override
    @Transactional
    public Integer updateAdmin(AdminVO adminVO) {
        roleMapper.updateRole(adminVO);
        userMapper.updateUser(adminVO);
        return 1;
    }
}
