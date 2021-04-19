package com.dao;

import java.util.List;

import com.dto.ClassInfo;


public interface HibernateDao {
    public Integer addClassInfo(ClassInfo employee);
    public List<ClassInfo> listClassInfo();
    public ClassInfo searchClassByName(Integer employeeID);
}
