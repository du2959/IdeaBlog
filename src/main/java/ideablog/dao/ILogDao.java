package ideablog.dao;

import ideablog.model.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILogDao {

    List<Log> selectAllLogs();
    List<Log> selectLogsByUserId(long userId);
    Boolean insertLog(@Param("userId") Long userId, @Param("method") String method, @Param("description") String description, @Param("reqIp") String reqIp, @Param("opTime") String opTime);
    Boolean deleteLogById(long id);
}
