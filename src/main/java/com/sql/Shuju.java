package com.sql;

import com.bean.Bean;

import java.util.List;

public interface Shuju {
    public void add(Bean bean);
    public void delectall();
    public void delect(String name);
    public String select(String name);
    public List<Bean> selectall();
}
