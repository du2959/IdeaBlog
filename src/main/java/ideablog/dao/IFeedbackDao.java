package ideablog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeedbackDao {

    Boolean insertFeedback(@Param("title") String title, @Param("email") String email, @Param("content") String content, @Param("time") String time);
}
