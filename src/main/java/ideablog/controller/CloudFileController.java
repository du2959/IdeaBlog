package ideablog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ideablog.aop.SystemControllerLog;
import ideablog.model.CloudFile;
import ideablog.service.ICloudFileService;
import ideablog.utils.MyTime;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static ideablog.utils.Constant.CLOUDFILEPATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class CloudFileController {

    @Resource
    private ICloudFileService cloudFileService;

    @RequestMapping(value = "/cloud_file", method = GET)
    public String cloudFile(){
        return "cloud_file";
    }

    @RequestMapping(value = "/showUserFiles.do", method = POST)//云文件列表
    public void showUserFiles(HttpSession session, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        List<CloudFile> cloudFileList = this.cloudFileService.selectCloudFilesByUserId(Long.parseLong(session.getAttribute("userId").toString()));
        String respJson;
        ObjectMapper mapper = new ObjectMapper();
        respJson = mapper.writeValueAsString(cloudFileList);
        System.out.println(respJson);
        response.getWriter().write(respJson);
        response.getWriter().close();
    }

    @RequestMapping(value = "/switchFileStatus.do", method = POST)//切换文件状态
    public void switchFileStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Boolean sw;
        sw = this.cloudFileService.switchStatusById(Long.parseLong(request.getParameter("fileId")), Integer.parseInt(request.getParameter("status")));
        if(sw) {
            System.out.println("文件状态切换成功！");
            response.getWriter().write("文件状态切换成功！");
            response.getWriter().close();
        } else {
            System.out.println("文件状态切换失败！");
            response.getWriter().write("文件状态切换失败！");
            response.getWriter().close();
        }
    }

    private void uploadFileInfo(Long userId, String fileName, String fileSize, String filePath) {
        Boolean add = false;
        try {
            add = this.cloudFileService.insertCloudFiles(userId, fileName, fileSize, filePath, MyTime.getMyTime());
        }catch (org.springframework.dao.DuplicateKeyException e){
            System.out.println("捕获异常！");
        }
        if(add) {
            System.out.println(fileName + "上传成功！");
        }
    }

    @RequestMapping("/uploadFiles.do")//上传文件
    public void uploadFiles(HttpSession session, HttpServletResponse response, @RequestParam("files") MultipartFile[] files) throws IOException {
        if (files != null && files.length > 0) {//判断file数组不能为空并且长度大于0
            for (MultipartFile file : files) {//循环获取file数组中得文件
                //保存文件
                if (!file.isEmpty()) {// 判断文件是否为空
                    try {
                        String path = CLOUDFILEPATH + session.getAttribute("userId") + "/";//上传文件路径
                        String fileName = file.getOriginalFilename();//上传文件名
                        Long fSize = file.getSize();
                        String fileSize;
                        if (fSize >= 1024 * 1024) {
                            fileSize = String.format("%.1f", fSize * 1.0 / (1024 * 1024)) + "MB";
                        } else if (fSize >= 1024) {
                            fileSize = String.format("%.1f", fSize * 1.0 / 1024) + "KB";
                        } else {
                            fileSize = fSize + "B";
                        }
                        File filepath = new File(path, fileName);
                        if (!filepath.getParentFile().exists()) {//判断路径是否存在，如果不存在就创建一个
                            filepath.getParentFile().mkdirs();
                        }
                        file.transferTo(new File(path + File.separator + fileName));//将上传文件保存到一个目标文件当中
                        uploadFileInfo(Long.parseLong(session.getAttribute("userId").toString()), fileName, fileSize, path);
                        response.getWriter().write("{}");//成功时返回一个空json
                        response.getWriter().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        response.getWriter().write("{'error':'上传失败'}");//出错时返回一个包含error属性的json,前端控件会显示错误状态并输出对应信息
                        response.getWriter().close();
                    }
                }
            }
        }
    }

    @RequestMapping(value="/downloadFile.do")//下载文件
    public ResponseEntity<byte[]> download(HttpServletRequest request)throws Exception {
        CloudFile cloudFile = this.cloudFileService.selectCloudFileById(Long.parseLong(request.getParameter("fileId")));
        File file = new File(cloudFile.getFilePath() + File.separator + cloudFile.getFileName());
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFileName = new String(cloudFile.getFileName().getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开文件
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteFile.do", method = POST)//删除文件
    @SystemControllerLog(description = "删除文件")
    public void deleteFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        CloudFile cloudFile = this.cloudFileService.selectCloudFileById(Long.parseLong(request.getParameter("fileId")));
        File file = new File(cloudFile.getFilePath() + cloudFile.getFileName());
        Boolean delete;
        delete = this.cloudFileService.deleteCloudFileById(Long.parseLong(request.getParameter("fileId")));
        if(file.delete() && delete) {
            System.out.println(cloudFile.getFileName() + "文件删除成功！");
            response.getWriter().write(cloudFile.getFileName() + "文件删除成功！");
            response.getWriter().close();
        } else {
            response.getWriter().write(cloudFile.getFileName() + "文件删除失败！");
            response.getWriter().close();
        }
    }
}
