package com.bgee.security.service;

import com.bgee.security.dao.MenuDao;
import com.bgee.security.entity.Menu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {

    @Resource  private MenuDao menuDao;

    public List<Menu> list(){return menuDao.list();}
    public Menu get(Integer id){return menuDao.get(id);}
    public int insert(Menu menu){return menuDao.insert(menu);}
    public int update(Menu menu){return menuDao.update(menu);}
    public int del(Integer id){return menuDao.del(id);}
}
