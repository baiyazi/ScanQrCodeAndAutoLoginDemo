package com.example.scan.Controller;

import com.example.scan.Bean.User;
import com.example.scan.Bean.UserMap;
import com.example.scan.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;


    @GetMapping(value="/checklogin")
    // 返回静态二维码的登录页面
    public String getLoginPage(){
        return "loginpage";
    }

    @GetMapping(value="/checkscan")
    @ResponseBody
    // 需要前端轮询这个接口，判断是否确认登录
    public boolean hasScan(HttpSession session,
                          @RequestParam("uuid") String uuid){
        User scanedUser = UserMap.getScanUserByUUID(uuid);
        if(scanedUser == null)
            return false;
        if(userService.hasUser(scanedUser.getUserID())){
            String username = (String) session.getAttribute("name");
            if(username == null){
                session.setAttribute("name", uuid);
            }
            return true;
        }
        return false;
    }

    @GetMapping(value="/scanlogin")
    @ResponseBody
    // 手机端确定登录后调用该接口，将用户加入到登录Map
    // http://localhost:8080/login?uuid=Wxfsfdfskfj==&userid=qwe&username=123
    public String scanLogin(HttpSession session,
                               @RequestParam("uuid") String uuid,
                               @RequestParam("userid") String userid,
                               @RequestParam("username") String name){
        String UUID = "Wxfsfdfskfj==";
        User user = new User(name, userid);
        if(uuid.equals(UUID) && userService.checkUserInfo(user)){
            UserMap.loginUser(UUID, new User(name, userid));
            return "SUCCESS LOGIN!";
        }else {
            return "ERROR UserInfo!";
        }
    }

    @GetMapping(value="/home")
    // 校验Session的主页
    public String getHomePage(HttpSession session){
        String username = (String) session.getAttribute("name");
        if(username == null){
            return "redirect:/notlogin";
        }
        return "homepage";
    }

    @GetMapping(value="/notlogin")
    public String getNotLoginpage(){
        return "notlogin";
    }
}
