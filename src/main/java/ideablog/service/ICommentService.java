package ideablog.service;

import ideablog.model.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> selectCommentsByBlogId(long blogId);
    List<Comment> selectCommentsByReplyUserId(long replyUserId);
    Boolean insertComment(long blogId, long userId, String content, int type, long replyUserId, String postTime);
}