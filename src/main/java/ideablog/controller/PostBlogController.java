package ideablog.controller;

import ideablog.service.IBlogService;
import ideablog.utils.MyTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PostBlogController {

    @Resource
    private IBlogService blogService;

    @RequestMapping(value = "/post_blog", method = GET)//发布博客页面
    public String postBlogPage(){
        return "post_blog";
    }

    @RequestMapping(value = "/saveBlog.do", method = POST)//私密发布博客
    public void saveBlog(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Boolean add = false;
        try {
            add = this.blogService.insertBlog(Long.parseLong(session.getAttribute("userId").toString()), request.getParameter("blogtitle"), request.getParameter("blogtag"), request.getParameter("blogcontent"), MyTime.getMyTime(), 1);
        }catch (org.springframework.dao.DuplicateKeyException e){
            System.out.println("捕获异常！");
        }
        if(add) {
            System.out.println("博客私密发布成功！");
            response.getWriter().write("博客私密发布成功！");
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/postBlog.do", method = POST)//公开发布博客
    public void postBlog(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Boolean add = false;
        try {
            add = this.blogService.insertBlog(Long.parseLong(session.getAttribute("userId").toString()), request.getParameter("blogtitle"), request.getParameter("blogtag"), request.getParameter("blogcontent"), MyTime.getMyTime(), 2);
        }catch (org.springframework.dao.DuplicateKeyException e){
            System.out.println("捕获异常！");
        }
        if(add) {
            System.out.println("博客公开发布成功！");
            response.getWriter().write("博客公开发布成功！");
            response.getWriter().close();
        }
    }
}
