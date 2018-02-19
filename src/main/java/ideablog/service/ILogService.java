package ideablog.service;

import ideablog.model.Log;

import java.util.List;

public interface ILogService {

    List<Log> selectAllLogs();
    List<Log> selectLogsByUserId(long userId);
    Boolean insertLog(Long userId, String method, String description, String reqIp, String opTime);
    Boolean deleteLogById(long id);
}