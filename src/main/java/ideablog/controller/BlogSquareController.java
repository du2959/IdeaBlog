package ideablog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ideablog.model.Blog;
import ideablog.model.Comment;
import ideablog.model.RCollect;
import ideablog.model.RFollow;
import ideablog.service.*;
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
public class BlogSquareController {

    @Resource
    private IBlogService blogService;
    @Resource
    private IRFollowService rFollowService;
    @Resource
    private IRCollectService rCollectService;
    @Resource
    private IUserService userService;
    @Resource
    private ICommentService commentService;

    @RequestMapping(value = "/blog_square", method = GET)
    public String blogSquare(){
        return "blog_square";
    }

    @RequestMapping(value = "/showAllBlogs.do", method = POST)//综合排序
    public void showAllBlogs(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<Blog> blogList = this.blogService.selectAllBlogs();
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(blogList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/showNewBlogs.do", method = POST)//最新排序
    public void showNewBlogs(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<Blog> blogList = this.blogService.selectNewBlogs();
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(blogList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/showHotBlogs.do", method = POST)//热门排序
    public void showHotBlogs(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<Blog> blogList = this.blogService.selectHotBlogs();
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(blogList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/blogPage", method = GET)//博客页面
    public String blogPage(HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Long blogId = Long.parseLong(request.getParameter("blogId"));
        session.setAttribute("blogId", blogId);
        System.out.println("博客ID：" + blogId);
        return "blog";
    }

    @RequestMapping(value = "/increaseViews.do", method = POST)//浏览数+1
    public void increaseViews(HttpSession session) {
        if (this.blogService.increaseViewsById(Long.parseLong(session.getAttribute("blogId").toString()))) {
            System.out.println("浏览数+1");
        } else {
            System.out.println("浏览数自增失败");
        }
    }

    @RequestMapping(value = "/getRCollect.do", method = POST)//获取收藏关系
    public void getRCollect(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        RCollect rCollect = this.rCollectService.selectRCollect(Long.parseLong(session.getAttribute("blogId").toString()), Long.parseLong(session.getAttribute("userId").toString()));
        if(rCollect != null) {
            System.out.println("已收藏");
            response.getWriter().write("1");
            response.getWriter().close();
        }
        else {
            System.out.println("未收藏");
            response.getWriter().write("0");
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/collect.do", method = POST)//收藏博客
    public void collect(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        Boolean rCollect;
        rCollect = this.rCollectService.insertRCollect(Long.parseLong(session.getAttribute("blogId").toString()), Long.parseLong(session.getAttribute("userId").toString()));
        this.blogService.increaseCollectsById(Long.parseLong(session.getAttribute("blogId").toString()));
        if(rCollect) {
            System.out.println("收藏成功");
            response.getWriter().write("1");
            response.getWriter().close();
        }
        else {
            System.out.println("收藏失败");
            response.getWriter().write("0");
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/uncollect.do", method = POST)//取消收藏
    public void uncollect(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        Boolean rCollect;
        rCollect = this.rCollectService.deleteRCollect(Long.parseLong(session.getAttribute("blogId").toString()), Long.parseLong(session.getAttribute("userId").toString()));
        this.blogService.decreaseCollectsById(Long.parseLong(session.getAttribute("blogId").toString()));
        if(rCollect) {
            System.out.println("取消收藏成功");
            response.getWriter().write("0");
            response.getWriter().close();
        }
        else {
            System.out.println("取消收藏失败");
            response.getWriter().write("1");
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/getRFollow.do", method = POST)//获取关注关系
    public void getRFollow(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        Blog blog = this.blogService.selectBlogById(Long.parseLong(session.getAttribute("blogId").toString()));
        RFollow rFollow = this.rFollowService.selectRFollow(blog.getUserId(), Long.parseLong(session.getAttribute("userId").toString()));
        if(rFollow != null) {
            System.out.println("已关注");
            response.getWriter().write("1");
            response.getWriter().close();
        }
        else {
            System.out.println("未关注");
            response.getWriter().write("0");
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/follow.do", method = POST)//关注用户
    public void follow(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        Blog blog = this.blogService.selectBlogById(Long.parseLong(session.getAttribute("blogId").toString()));
        Boolean rFollow;
        rFollow = this.rFollowService.insertRFollow(blog.getUserId(), Long.parseLong(session.getAttribute("userId").toString()));
        this.userService.increaseFollowsById(blog.getUserId());
        if(rFollow) {
            System.out.println("关注成功");
            response.getWriter().write("1");
            response.getWriter().close();
        }
        else {
            System.out.println("关注失败");
            response.getWriter().write("0");
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/unfollow.do", method = POST)//取消关注
    public void unfollow(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        Blog blog = this.blogService.selectBlogById(Long.parseLong(session.getAttribute("blogId").toString()));
        Boolean rFollow;
        rFollow = this.rFollowService.deleteRFollow(blog.getUserId(), Long.parseLong(session.getAttribute("userId").toString()));
        this.userService.decreaseFollowsById(blog.getUserId());
        if(rFollow) {
            System.out.println("取消关注成功");
            response.getWriter().write("0");
            response.getWriter().close();
        }
        else {
            System.out.println("取消关注失败");
            response.getWriter().write("1");
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/getHeadIconPath.do", method = POST)//获取作者头像路径
    public void getHeadIconPath(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        Blog blog = this.blogService.selectBlogById(Long.parseLong(session.getAttribute("blogId").toString()));
        response.getWriter().write(HEADICONVIRTUALPATH + blog.getUserId() + "_headIcon.png");
        response.getWriter().close();
    }

    @RequestMapping(value = "/showComments.do", method = POST)//获取评论
    public void showComments(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<Comment> commentList = this.commentService.selectCommentsByBlogId(Long.parseLong(session.getAttribute("blogId").toString()));
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(commentList);
        System.out.println(respJson);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/comment.do", method = POST)//评论
    public void comment(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Boolean add = false;
        try {
            add = this.commentService.insertComment(Long.parseLong(session.getAttribute("blogId").toString()), Long.parseLong(session.getAttribute("userId").toString()), request.getParameter("content"), Integer.parseInt(request.getParameter("type")), !request.getParameter("replyUserId").equals("0") ? Long.parseLong(request.getParameter("replyUserId")) : 0, MyTime.getMyTime());
        }catch (org.springframework.dao.DuplicateKeyException e){
            System.out.println("捕获异常！");
        }
        if(add) {
            System.out.println("评论发布成功！");
            response.getWriter().write("评论发布成功！");
            response.getWriter().close();
        }
        else {
            System.out.println("评论发布失败！");
            response.getWriter().write("评论发布失败！");
            response.getWriter().close();
        }
    }
}
