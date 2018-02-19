package ideablog.controller;

import ideablog.aop.SystemControllerLog;
import ideablog.model.User;
import ideablog.service.IUserService;
import ideablog.utils.Encrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import static ideablog.utils.Constant.HEADICONPATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class SettingController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/setting", method = GET)
    public String setting(){
        return "setting";
    }

    @RequestMapping(value = "/modifyPassword.do", method = POST)//修改密码
    @SystemControllerLog(description = "修改密码")
    public String modifyPassword(HttpSession session, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("UTF-8");
        Boolean update = false;
        if(session.getAttribute("userPass").equals(new Encrypt().SHA256(request.getParameter("oldpassword")))) {
            System.out.println("用户输入的原密码正确！");
            update = this.userService.updatePasswordByUserName(session.getAttribute("userName").toString(), new Encrypt().SHA256(request.getParameter("password")));
        }
        if(update) {
            System.out.println("密码修改成功！");
            request.setAttribute("modifypasswordstatus", "密码修改成功！");
            User user = this.userService.selectUserById(Long.parseLong(session.getAttribute("userId").toString()));
            session.setAttribute("userPass", user.getPassword());
        }
        else {
            System.out.println("密码修改失败！");
            request.setAttribute("modifypasswordstatus", "密码修改失败！请检查原密码是否正确！");
        }
        return "setting";
    }

    @RequestMapping(value = "/modifyIcon.do", method = POST)//修改头像
    @SystemControllerLog(description = "修改头像")
    public String modifyIcon(HttpSession session, HttpServletRequest request, @RequestParam("myfile") MultipartFile myfile) {
        if (myfile != null) {
            if (!myfile.isEmpty()) {
                String path = HEADICONPATH;//头像路径
                String fileName = session.getAttribute("userId").toString() + "_headIcon.png";//头像文件名
                File filepath = new File(path, fileName);
                if (!filepath.getParentFile().exists()) {//判断路径是否存在，如果不存在就创建一个
                    filepath.getParentFile().mkdirs();
                }
                try {
                    myfile.transferTo(new File(path + File.separator + fileName));//将上传文件保存到一个目标文件当中
                    System.out.println("头像修改成功！");
                    request.setAttribute("modifyiconstatus", "头像修改成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("头像修改失败！");
                    request.setAttribute("modifyiconstatus", "头像修改失败！");
                }
            }
        }
        return "setting";
    }

    @RequestMapping(value = "/updateInfo.do", method = POST)//更新资料
    @SystemControllerLog(description = "更新资料")
    public String updateInfo(HttpSession session, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("UTF-8");
        Boolean update;
        update = this.userService.updateInfoById(Long.parseLong(session.getAttribute("userId").toString()), Integer.parseInt(request.getParameter("gender")), Integer.parseInt(request.getParameter("age")), request.getParameter("province"), request.getParameter("city"), request.getParameter("tel"));
        if(update) {
            System.out.println("资料更新成功！");
            request.setAttribute("status", "个人资料更新成功！");
            User user = this.userService.selectUserById(Long.parseLong(session.getAttribute("userId").toString()));
            session.setAttribute("userGender", user.getGender());
            session.setAttribute("userAge", user.getAge());
            session.setAttribute("userProvince", user.getProvince());
            session.setAttribute("userCity", user.getCity());
            session.setAttribute("userTel", user.getTel());
        }
        else {
            System.out.println("资料更新失败！");
            request.setAttribute("status", "个人资料更新失败！请稍后刷新重试！");
        }
        return "setting";
    }
}
