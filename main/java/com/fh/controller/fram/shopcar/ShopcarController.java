package com.fh.controller.fram.shopcar;


import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.fram.ShopcarService;
import com.fh.util.Const;
import com.fh.util.PageData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 类名称：OrdersController
 * 创建人：WANG.DELONG
 * 创建时间：2017年7月12日
 * @version 1.0
 */


@Controller
@RequestMapping(value="/shopcar")
public class ShopcarController extends BaseController {

    @Resource(name="shopcarService")
    private ShopcarService shopcarService;


    /**
     * 显示购物列表
     */
    @RequestMapping(value="/listShopcar")
    public ModelAndView listShopcar(Page page)throws Exception{

        ModelAndView mv = this.getModelAndView();

        PageData pd = new PageData();
        pd = this.getPageData();

        // 前台参数获取
        //获用户ID
        String user_id = pd.getString("user_id");
        if(null != user_id && !"".equals(user_id)){
            user_id = user_id.trim();
            pd.put("user_id", user_id);
        }


        page.setPd(pd);

        //列出购物列表
        List<PageData> shopcarList = shopcarService.listPdPageShopcars(page);

        mv.setViewName("fram/shopcars/shopcar_list");
        mv.addObject("shopcarList", shopcarList);
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


