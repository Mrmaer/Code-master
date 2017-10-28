package com.sql;

import com.bean.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Shuju extends ShujuMapper{
    public void add(Bean bean);
    public void delectall();
    public void delect(String name);
    public String select(String name);
    public List<Bean> selectall();
}
