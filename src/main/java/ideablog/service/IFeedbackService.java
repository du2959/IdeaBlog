package ideablog.service;

import ideablog.model.Feedback;

import java.util.List;

public interface IFeedbackService {

    List<Feedback> selectAllFeedbacks();
    Boolean addFeedback(String title, String email, String content, String time);
    Boolean switchStatusById(long id, int status);
    Boolean deleteFeedbackById(long id);
}