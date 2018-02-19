package ideablog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ideablog.model.Comment;
import ideablog.model.Log;
import ideablog.model.Statistic;
import ideablog.service.ICommentService;
import ideablog.service.ILogService;
import ideablog.service.IStatisticService;
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
public class NoticeController {

    @Resource
    private ICommentService commentService;
    @Resource
    private ILogService logService;
    @Resource
    private IStatisticService statisticService;

    @RequestMapping(value = "/notice", method = GET)
    public String notice(){
        return "notice";
    }

    @RequestMapping(value = "/showCommentsNotice.do", method = POST)//评论回复通知
    public void showCommentsNotice(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<Comment> commentList = this.commentService.selectCommentsByReplyUserId(Long.parseLong(session.getAttribute("userId").toString()));
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(commentList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/showSystemLogs.do", method = POST)//系统日志通知
    public void showSystemLogs(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<Log> logList = this.logService.selectLogsByUserId(Long.parseLong(session.getAttribute("userId").toString()));
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(logList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/showStatistics.do", method = POST)//统计数据
    public void showStatistics(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        Statistic statistic = this.statisticService.selectStatisticByUserId(Long.parseLong(session.getAttribute("userId").toString()));
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(statistic);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }
}
