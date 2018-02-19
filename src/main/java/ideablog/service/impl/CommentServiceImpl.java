package ideablog.service.impl;

import ideablog.dao.ICommentDao;
import ideablog.model.Comment;
import ideablog.service.ICommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements ICommentService {

    @Resource
    private ICommentDao commentDao;

    @Override
    public List<Comment> selectCommentsByBlogId(long blogId) {
        return this.commentDao.selectCommentsByBlogId(blogId);
    }

    @Override
    public List<Comment> selectCommentsByReplyUserId(long replyUserId) {
        return this.commentDao.selectCommentsByReplyUserId(replyUserId);
    }

    @Override
    public Boolean insertComment(long blogId, long userId, String content, int type, long replyUserId, String postTime) {
        return this.commentDao.insertComment(blogId, userId, content, type, replyUserId, postTime);
    }
}