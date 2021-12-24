package com.lu.demo.service.impl;

import com.lu.demo.mapper.RolePathMapper;
import com.lu.demo.model.RolePath;
import com.lu.demo.service.RolePathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePathServiceImpl implements RolePathService {

    @Autowired
    private RolePathMapper rolePathMapper;

    @Override
    public List<RolePath> selectRolePathAll() {
        return rolePathMapper.selectRolePathAll();
    }
}
