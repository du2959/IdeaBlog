package ideablog.service.impl;

import ideablog.dao.ILogDao;
import ideablog.model.Log;
import ideablog.service.ILogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("logService")
public class LogServiceImpl implements ILogService {

    @Resource
    private ILogDao logDao;

    @Override
    public List<Log> selectAllLogs() {
        return this.logDao.selectAllLogs();
    }

    @Override
    public List<Log> selectLogsByUserId(long userId) {
        return this.logDao.selectLogsByUserId(userId);
    }

    @Override
    public Boolean insertLog(Long userId, String method, String description, String reqIp, String opTime) {
        return this.logDao.insertLog(userId, method, description, reqIp, opTime);
    }

    @Override
    public Boolean deleteLogById(long id) {
        return this.logDao.deleteLogById(id);
    }
}