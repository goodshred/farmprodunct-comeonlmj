package com.fh.service.fram;


import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("shopcarService")
public class ShopcarService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /*
     *购物车列表(分页)
     */
    public List<PageData> listPdPageShopcars(Page page)throws Exception{
        return (List<PageData>) dao.findForList("ShopcarMapper.shopcarlistPage", page);
    }


}
