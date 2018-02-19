package ideablog.controller;

import ideablog.service.IFeedbackService;
import ideablog.utils.MyTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class HelpAboutController {

    @Resource
    private IFeedbackService feedbackService;

    @RequestMapping(value = "/help_about", method = GET)//帮助关于页面
    public String helpAbout(){
        return "help_about";
    }

    @RequestMapping(value = "/feedback.do", method = POST)//添加反馈
    public String addFeedback(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("UTF-8");
        Boolean add = false;
        try {
            add = this.feedbackService.addFeedback(request.getParameter("title"), request.getParameter("email"), request.getParameter("content"), MyTime.getMyTime());
        }catch (org.springframework.dao.DuplicateKeyException e){
            System.out.println("捕获异常！");
        }
        if(add) {
            System.out.println("反馈成功！");
            request.setAttribute("status", "反馈成功！感谢您的支持！");
        }
        else {
            System.out.println("反馈失败！");
            request.setAttribute("status", "反馈失败！");
        }
        return "help_about";
    }
}
