package com.joy.web;

import com.joy.service.IZKNotifyService;
import com.joy.service.ServiceFactory;
import com.joy.util.RenderUtil;
import com.joy.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: gaojiechen
 * Date: 14-6-19
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class NotifyController {

    private IZKNotifyService notifyService = ServiceFactory.getZkNotifyService();

    @RequestMapping(value="/function/zk/notify.go")
    public String notifyZK(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        //为了防止CMS的主从库同步不及时，接受到通知后不能及时读取最新的数据，所以要在通知之前sleep 2秒钟。
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean succeed = false;
        String code = RequestUtil.getRequestString(request, "code", "");

        if (code.equals("zknode")) {
            succeed = isSetCommonZKNodeSucceed(request);
        } else {
            String queryString = request.getQueryString();
            succeed = notifyService.setZKNode(code, queryString);
        }

        if(succeed){
            RenderUtil.renderText(response, "set zk node success!");
        }
        else{
            RenderUtil.renderText(response, "set zk node fail!");
        }
        return null;
    }

    /**
     * 设置通用zknode设置
     * @param request
     * @return
     */
    private boolean isSetCommonZKNodeSucceed(HttpServletRequest request) {
        String zkNodePath = "";
        String data = "";
        zkNodePath = RequestUtil.getRequestString(request, "path", "");
        data = RequestUtil.getRequestString(request, "data", "");
        boolean succeed = notifyService.setZKNodeValue(zkNodePath, data);
        return succeed;
    }
}
