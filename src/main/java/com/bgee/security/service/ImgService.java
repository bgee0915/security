package com.bgee.security.service;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.bgee.security.dao.ImgDao;
import com.bgee.security.entity.Img;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ImgService {

    private Log log = LogFactory.getLog(this.getClass());

    @Resource private ImgDao imgDao;

    public Img get(Integer id){return imgDao.get(id);}
    public Integer insert(Img img){return imgDao.insert(img);}
    public Integer update(Img img){return imgDao.update(img);}
    public Integer del(Integer id){return imgDao.del(id);}

    /**
     * list 参数可为空
     * @param url
     * @param type
     * @param realm
     * @param status
     * @return
     */
    public List<Img> list( String url, Integer type, String realm, Integer status){
        return imgDao.list(url,type,realm,status);
    }
}
