package ideablog.dao;

import ideablog.model.Feedback;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeedbackDao {

    List<Feedback> selectAllFeedbacks();
    Boolean insertFeedback(@Param("title") String title, @Param("email") String email, @Param("content") String content, @Param("time") String time);
    Boolean switchStatusById(@Param("id") long id, @Param("status") int status);
    Boolean deleteFeedbackById(long id);
}
