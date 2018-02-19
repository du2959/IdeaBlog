package ideablog.service.impl;

import ideablog.dao.IFeedbackDao;
import ideablog.service.IFeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("feedbackService")
public class FeedbackServiceImpl implements IFeedbackService {

    @Resource
    private IFeedbackDao feedbackDao;

    @Override
    public Boolean addFeedback(String title, String email, String content, String time) {
        return this.feedbackDao.insertFeedback(title, email, content, time);
    }
}