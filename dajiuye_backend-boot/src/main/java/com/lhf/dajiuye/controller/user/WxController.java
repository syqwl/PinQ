package com.lhf.dajiuye.controller.user;

//import com.lhf.dajiuye.api.bean.CommonResult2;
//import com.lhf.dajiuye.api.bean.user.LoginInfo;
//import com.lhf.dajiuye.api.bean.Meta;
//import com.lhf.dajiuye.api.bean.user.User;
//import com.lhf.dajiuye.api.service.user.WxService;
//import com.lhf.dajiuye.web.app.log.SysLog;
//import com.lhf.dajiuye.web.app.log.SysLogAnnotation;
import com.lhf.dajiuye.annotation.SysLogAnnotation;
import com.lhf.dajiuye.bean.SysLog;
import com.lhf.dajiuye.bean.user.LoginInfo;
import com.lhf.dajiuye.bean.user.User;
import com.lhf.dajiuye.common.utils.R;
import com.lhf.dajiuye.service.user.WxService;
//import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("user/wx")
public class WxController {

    @Resource
    private WxService wxService;

    /**
     * 根据code获取openid和token
     * @param
     *
     * @return
     */
//    @GetMapping("/openid")
//    @ResponseBody
//    public Object openId(String code){
//        return new CommonResult2(wxService.getOpenIdByCode(code),new Meta("获取成功",200));
//    }

    /**
     * 根据code获取openid和token
     * @param
     *
     * @return
     */
    @GetMapping("/openid")
    @ResponseBody
    public R openIdToken(String code){
        System.out.println("code是"+code);
        return R.ok().setData(wxService.getOpenIdToken(code));
    }

    @GetMapping("log")
    @ResponseBody
    @SysLogAnnotation
    public R testLog(String p){
        System.out.println("Niaho");
        return R.ok().setData(new SysLog());
    }

    /**
     * 返回解密后的用户信息"nickName":"moon","gender":0,"language":"zh_CN","city":""。。。
     * @param

     * @return
     */
    @PostMapping("/handleWXMsg")
    @ResponseBody
    public R handleWXMsg( LoginInfo loginInfo){
        User user=wxService.getUserInfo(loginInfo);
        // 新用户会被保存到数据库中，并且根据openId生成token(username也是openid,password也是openid)
        return R.ok().setData(user);
    }
}
