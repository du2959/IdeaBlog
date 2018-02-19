package ideablog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ideablog.model.Blog;
import ideablog.model.CloudFile;
import ideablog.service.IBlogService;
import ideablog.service.ICloudFileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class FollowCollectController {

    @Resource
    private IBlogService blogService;
    @Resource
    private ICloudFileService cloudFileService;

    @RequestMapping(value = "/follow_collect", method = GET)
    public String followCollect(){
        return "follow_collect";
    }

    @RequestMapping(value = "/showFollowFiles.do", method = POST)//关注用户文件列表
    public void showFollowFiles(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<CloudFile> cloudFileList = this.cloudFileService.selectFollowFilesByUserId(Long.parseLong(session.getAttribute("userId").toString()));
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(cloudFileList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/showFollowBlogs.do", method = POST)//关注用户博客列表
    public void showFollowBlogs(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<Blog> blogList = this.blogService.selectFollowBlogsByUserId(Long.parseLong(session.getAttribute("userId").toString()));
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(blogList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/showCollectBlogs.do", method = POST)//收藏博客列表
    public void showCollectBlogs(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<Blog> blogList = this.blogService.selectCollectBlogsByUserId(Long.parseLong(session.getAttribute("userId").toString()));
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(blogList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }
}
