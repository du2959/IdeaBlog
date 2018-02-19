package ideablog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ideablog.model.CloudFile;
import ideablog.model.Log;
import ideablog.model.User;
import ideablog.service.ICloudFileService;
import ideablog.service.ILogService;
import ideablog.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class SystemController {

    @Resource
    private IUserService userService;
    @Resource
    private ILogService logService;
    @Resource
    private ICloudFileService cloudFileService;

    @RequestMapping(value = "/system.do", method = GET)//验证管理员身份后跳转
    public String system(HttpSession session) {
        try {
            User user = this.userService.selectUserById(Long.parseLong(session.getAttribute("userId").toString()));
            if(user.getRole() == 2) {
                return "system";
            } else {
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "/showAllLogs.do", method = POST)//全部日志
    public void showAllLogs(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<Log> logList = this.logService.selectAllLogs();
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(logList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/deleteLog.do", method = POST)//删除日志
    public void deleteBlog(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Boolean delete;
        delete = this.logService.deleteLogById(Long.parseLong(request.getParameter("logId")));
        if(delete) {
            System.out.println("日志删除成功！");
            response.getWriter().write("日志删除成功！");
            response.getWriter().close();
        }
        else {
            System.out.println("日志删除失败！");
            response.getWriter().write("日志删除失败！");
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/showAllUsers.do", method = POST)//全部用户
    public void showAllUsers(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<User> userList = this.userService.selectAllUsers();
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(userList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/switchUserStatus.do", method = POST)//切换用户账号状态
    public void switchUserStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Boolean sw;
        sw = this.userService.switchStatusById(Long.parseLong(request.getParameter("userId")), Integer.parseInt(request.getParameter("status")));
        if(sw) {
            System.out.println("用户账号状态切换成功！");
            response.getWriter().write("用户账号状态切换成功！");
            response.getWriter().close();
        } else {
            System.out.println("用户账号状态切换失败！");
            response.getWriter().write("用户账号状态切换失败！");
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/deleteUser.do", method = POST)//删除用户
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Boolean delete;
        delete = this.userService.deleteUserById(Long.parseLong(request.getParameter("userId")));
        if(delete) {
            System.out.println("用户删除成功！");
            response.getWriter().write("用户删除成功！");
            response.getWriter().close();
        }
        else {
            System.out.println("用户删除失败！");
            response.getWriter().write("用户删除失败！");
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/showAllFiles.do", method = POST)//全部共享文件
    public void showAllFiles(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<CloudFile> cloudFileList = this.cloudFileService.selectAllCloudFiles();
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(cloudFileList);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }
}
