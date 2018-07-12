package com.fh.controller.fram.orders;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Role;
import com.fh.service.fram.OrdersService;
import com.fh.util.Const;
import com.fh.util.PageData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
        * 类名称：OrdersController
        * 创建人：WANG.DELONG
        * 创建时间：2017年7月12日
        * @version 1.0
 */


@Controller
@RequestMapping(value="/orders")
public class OrdersController extends BaseController {


    @Resource(name="ordersService")
    private OrdersService ordersService;

    /**
     * 显示订单列表
     */
    @RequestMapping(value="/listOrders")
    public ModelAndView listOrders(Page page)throws Exception{

        ModelAndView mv = this.getModelAndView();

        PageData pd = new PageData();
        pd = this.getPageData();

        // 前台参数获取
        //获取订单编号
        String order_sn = pd.getString("sn");

        if(null != order_sn && !"".equals(order_sn)){
            order_sn = order_sn.trim();
            pd.put("sn", order_sn);
        }

        //订单创建时间
        String lastLoginStart = pd.getString("lastLoginStart");
        String lastLoginEnd = pd.getString("lastLoginEnd");

        if(lastLoginStart != null && !"".equals(lastLoginStart)){
            lastLoginStart = lastLoginStart+" 00:00:00";
            pd.put("lastLoginStart", lastLoginStart);
        }
        if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
            lastLoginEnd = lastLoginEnd+" 00:00:00";
            pd.put("lastLoginEnd", lastLoginEnd);
        }

        page.setPd(pd);


        List<PageData> ordersList = ordersService.listPdPageOrders(page);//列出订单列表

        mv.setViewName("fram/orders/orders_list");
        mv.addObject("ordersList", ordersList);
        mv.addObject("pd", pd);
        //mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
        return mv;
    }


    /* ===============================权限================================== */
    public Map<String, String> getHC(){
        Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
        Session session = currentUser.getSession();
        return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
    }
    /* ===============================权限================================== */

}
