package com.bgee.security.dao;

import com.bgee.security.entity.Img;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImgDao {
    Img get(Integer id);
    Integer insert(Img img);
    Integer update(Img img);
    Integer del(Integer id);
    List<Img> list( @Param("url")String url,
                    @Param("type")Integer type,
                    @Param("realm")String realm,
                    @Param("status")Integer status);

}
