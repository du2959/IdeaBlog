package ideablog.controller;

import ideablog.aop.SystemControllerLog;
import ideablog.model.User;
import ideablog.service.IUserService;
import ideablog.utils.Encrypt;
import ideablog.utils.IdeaBlogMail;
import ideablog.utils.MyTime;
import ideablog.utils.NumberCode6;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static ideablog.utils.Constant.HEADICONVIRTUALPATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class HomeController {

    @Resource
    private IUserService userService;
    private String numberCode;

    @RequestMapping(value = {"/", "/loginPage"}, method = GET)//登录页面（默认）
    public String loginPage(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @RequestMapping(value = "/login.do", method = POST)//用户登录
    @SystemControllerLog(description = "登录系统")
    public String login(HttpSession session, HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        User user = this.userService.login(request.getParameter("username"), new Encrypt().SHA256(request.getParameter("password")));
        if(user != null) {
            if(user.getStatus() == 1) {
                System.out.println("登录成功！用户Id：" + user.getId());
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", user.getUsername());
                session.setAttribute("userPass", user.getPassword());
                session.setAttribute("userEmail", user.getEmail());
                session.setAttribute("userRole", user.getRole());
                session.setAttribute("userRegTime", new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(user.getRegTime()));
                session.setAttribute("userGender", user.getGender());
                session.setAttribute("userAge", user.getAge());
                session.setAttribute("userProvince", user.getProvince());
                session.setAttribute("userCity", user.getCity());
                session.setAttribute("userTel", user.getTel());
                session.setAttribute("headIconPath", HEADICONVIRTUALPATH + user.getId() + "_headIcon.png");
                return "redirect:/home";
            }
            else {
                System.out.println("登录失败！");
                request.setAttribute("error", "登录失败！账号" + user.getUsername() + "已被冻结！");
                return "login";
            }
        }
        else {
            System.out.println("登录失败！");
            request.setAttribute("error", "登录失败！用户名或密码错误！");
            return "login";
        }
    }

    @RequestMapping(value = {"/loginout.do"}, method = GET)//注销登录
    public String loginout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @RequestMapping(value = "/visit", method = GET)//游客访问
    public String visit(HttpSession session){
        session.invalidate();
        return "home";
    }

    @RequestMapping(value = "/home", method = GET)//主页
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/registerPage", method = GET)//新用户注册页面
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register.do", method = POST)//新用户注册
    public String register(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("UTF-8");
        Boolean reg = false;
        try {
            reg = this.userService.register(request.getParameter("username"), new Encrypt().SHA256(request.getParameter("password")), request.getParameter("email"), MyTime.getMyTime());
        } catch (org.springframework.dao.DuplicateKeyException e) {
            System.out.println("捕获异常！");
        }
        if (reg) {
            System.out.println("注册成功！");
            IdeaBlogMail.sendMail(request.getParameter("email"), "注册成功！", "尊敬的用户你好，欢迎使用Idea Blog。您的邮箱已作为用户安全邮箱，可用于找回账号密码，请及时查看新邮件。如不是本人操作，请忽略此邮件。如有打扰，敬请谅解！");
            return "login";
        } else {
            System.out.println("注册失败！");
            request.setAttribute("error", "注册失败！用户名或邮箱已被占用！");
            return "register";
        }
    }

    @RequestMapping(value = "/findPage", method = GET)//找回账号密码页面
    public String find() {
        return "find";
    }

    @RequestMapping(value = "/findUser.do", method = POST)//找回账号
    public void findUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        User user = this.userService.selectUserByEmail(request.getParameter("email"));
        if (user != null) {
            response.getWriter().write(user.getUsername());
            numberCode = NumberCode6.getNumberCode6();
            System.out.println("6位验证码为：" + numberCode);
            IdeaBlogMail.sendMail(user.getEmail(), "验证码 Idea Blog", "尊敬的用户您好，您的验证码是：" + numberCode + "。如不是本人操作，请忽略此邮件。如有打扰，敬请谅解！");
        } else {
            response.getWriter().write("用户名获取失败！请检查邮箱是否正确");
        }
        response.getWriter().close();
    }

    @RequestMapping(value = "/find.do", method = POST)//重置密码
    public String find(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("UTF-8");
        Boolean update = false;
        if(numberCode.equals(request.getParameter("code"))) {
            System.out.println("用户输入的验证码正确！");
            update = this.userService.updatePasswordByUserName(request.getParameter("username"), new Encrypt().SHA256(request.getParameter("password")));
        }
        if(update) {
            System.out.println("密码重置成功！");
            return "login";
        }
        else {
            System.out.println("密码重置失败！");
            request.setAttribute("error", "密码重置失败！请检查验证码是否正确！");
            return "find";
        }
    }
}
