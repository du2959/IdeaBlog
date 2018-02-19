package ideablog.service;

public interface IFeedbackService {

    Boolean addFeedback(String title, String email, String content, String time);
}