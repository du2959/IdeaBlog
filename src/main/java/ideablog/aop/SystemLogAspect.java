package ideablog.aop;

import ideablog.service.ILogService;
import ideablog.utils.MyTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Aspect
@Component
public class SystemLogAspect {

    @Resource
    private ILogService logService;

    @Pointcut("@annotation(ideablog.aop.SystemControllerLog)")
    public void controllerAspect() {
    }

    @AfterReturning("controllerAspect()")
    public void doAfterReturning(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        try {
            Long userId = Long.parseLong(session.getAttribute("userId").toString());
            String method = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
            String description = getControllerMethodDescription(joinPoint);
            String reqIp = request.getRemoteAddr();
            String opTime = MyTime.getMyTime();
            System.out.println("********************正常返回通知开始********************");
            System.out.println("请求用户ID:" + userId);
            System.out.println("请求方法:" + method);
            System.out.println("方法描述:" + description);
            System.out.println("请求IP:" + reqIp);
            System.out.println("操作时间:" + opTime);
            this.logService.insertLog(userId, method, description, reqIp, opTime);//写入数据库
            System.out.println("********************正常返回通知结束********************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
