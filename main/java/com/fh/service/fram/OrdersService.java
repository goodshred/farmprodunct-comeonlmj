package com.fh.service.fram;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.User;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("ordersService")
public class OrdersService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    //======================================================================================


//    /*
//     *订单列表(全部)
//     */
//    public List<PageData> listAllOrders(PageData pd)throws Exception{
//        return (List<PageData>) dao.findForList("OrdersMapper.orderslistPage", pd);
//    }

    /*
     *订单列表(分页)
     */
    public List<PageData> listPdPageOrders(Page page)throws Exception{
        return (List<PageData>) dao.findForList("OrdersMapper.orderslistPage", page);
    }




}
