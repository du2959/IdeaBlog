package ideablog.utils;

public class Constant {

    //对应Tomcat->server.xml配置<Context path="/IdeaBlogFiles" docBase="E:\IdeaBlogFiles" reloadable="true" debug="0" crossContext="true"/>
    private static final String FILEBASEPATH = "E:/IdeaBlogFiles/";
//    public static final String FILEBASEPATH = "/home/IdeaBlogFiles/";//云服务器版
    public static final String CLOUDFILEPATH = FILEBASEPATH + "cloudFile/";
    public static final String HEADICONPATH = FILEBASEPATH + "headIcon/";
    private static final String VIRTUALPATH = "/IdeaBlogFiles/";
    public static final String HEADICONVIRTUALPATH = VIRTUALPATH + "headIcon/";
}
