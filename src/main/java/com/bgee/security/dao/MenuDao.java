package com.bgee.security.dao;

import com.bgee.security.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    List<Menu> list();
    Menu get(Integer id);
    int insert(Menu menu);
    int update(Menu menu);
    int del(Integer id);
}
