package ideablog.service.impl;

import ideablog.dao.IFeedbackDao;
import ideablog.model.Feedback;
import ideablog.service.IFeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImpl implements IFeedbackService {

    @Resource
    private IFeedbackDao feedbackDao;

    @Override
    public List<Feedback> selectAllFeedbacks() {
        return this.feedbackDao.selectAllFeedbacks();
    }

    @Override
    public Boolean addFeedback(String title, String email, String content, String time) {
        return this.feedbackDao.insertFeedback(title, email, content, time);
    }

    @Override
    public Boolean switchStatusById(long id, int status) {
        return this.feedbackDao.switchStatusById(id, status);
    }

    @Override
    public Boolean deleteFeedbackById(long id) {
        return this.feedbackDao.deleteFeedbackById(id);
    }
}