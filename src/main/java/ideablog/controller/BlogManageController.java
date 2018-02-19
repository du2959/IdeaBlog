package ideablog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ideablog.aop.SystemControllerLog;
import ideablog.model.Blog;
import ideablog.service.IBlogService;
import ideablog.utils.MyTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static ideablog.utils.Constant.HEADICONVIRTUALPATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class BlogManageController {

    @Resource
    private IBlogService blogService;

    @RequestMapping(value = "/blog_manage", method = GET)
    public String blogManage(){
        return "blog_manage";
    }

    @RequestMapping(value = "/showUserBlogs.do", method = POST)//获取用户博客
    public void showUserBlogs(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<Blog> blogList = this.blogService.selectNewBlogsByUserId(Long.parseLong(session.getAttribute("userId").toString()));
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(blogList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/switchBlogStatus.do", method = POST)//切换博客发布状态
    public void switchBlogStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Boolean sw;
        sw = this.blogService.switchStatusById(Long.parseLong(request.getParameter("blogId")), Integer.parseInt(request.getParameter("status")));
        if(sw) {
            System.out.println("博客发布状态切换成功！");
            response.getWriter().write("博客发布状态切换成功！");
            response.getWriter().close();
        } else {
            System.out.println("博客发布状态切换失败！");
            response.getWriter().write("博客发布状态切换失败！");
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/editBlogPage", method = GET)//编辑博客页面
    public String toEditBlogPage(HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        session.setAttribute("blogId", request.getParameter("blogId"));
        return "redirect:/edit_blog";
    }

    @RequestMapping(value = "/edit_blog")//编辑博客页面
    public String editBlogPage() {
        return "edit_blog";
    }

    @RequestMapping(value = "/showBlogById.do", method = POST)//获取博客
    public void showBlogById(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        Blog blog = this.blogService.selectBlogById(Long.parseLong(session.getAttribute("blogId").toString()));
        if(blog != null) {
            blog.setHeadIconPath(HEADICONVIRTUALPATH + blog.getUserId() + "_headIcon.png");
            ObjectMapper mapper = new ObjectMapper();
            String strJson = mapper.writeValueAsString(blog);
            response.getWriter().write(strJson);
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/updateBlog.do", method = POST)//更新博客
    public String updateBlog(HttpSession session, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("UTF-8");
        Boolean update;
        update = this.blogService.updateBlogById(Long.parseLong(session.getAttribute("blogId").toString()), request.getParameter("blogtitle"), request.getParameter("blogtag"), request.getParameter("blogcontent"), MyTime.getMyTime());
        if(update) {
            System.out.println("博客更新成功！");
            request.setAttribute("status", "博客更新成功！");
        } else {
            System.out.println("博客更新失败！");
            request.setAttribute("status", "博客更新失败！");
        }
        return "edit_blog";
    }

    @RequestMapping(value = "/deleteBlog.do", method = POST)//删除博客
    @SystemControllerLog(description = "删除博客")
    public void deleteBlog(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Boolean delete;
        delete = this.blogService.deleteBlogById(Long.parseLong(request.getParameter("blogId")));
        if(delete) {
            System.out.println("博客删除成功！");
            response.getWriter().write("博客删除成功！");
            response.getWriter().close();
        } else {
            System.out.println("博客删除失败！");
            response.getWriter().write("博客删除失败！");
            response.getWriter().close();
        }
    }
}
