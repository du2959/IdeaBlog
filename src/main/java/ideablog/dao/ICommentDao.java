package ideablog.dao;

import ideablog.model.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentDao {

    List<Comment> selectCommentsByBlogId(long blogId);
    List<Comment> selectCommentsByReplyUserId(long replyUserId);
    Boolean insertComment(@Param("blogId") long blogId, @Param("userId") long userId, @Param("content") String content, @Param("type") int type, @Param("replyUserId") long replyUserId, @Param("postTime") String postTime);
}
