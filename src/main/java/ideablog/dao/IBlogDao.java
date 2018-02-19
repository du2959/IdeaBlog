package ideablog.dao;

import ideablog.model.Blog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogDao {

    Blog selectBlogById(long id);
    Blog selectPublicBlogById(long id);
    List<Blog> selectAllBlogs();
    List<Blog> selectNewBlogs();
    List<Blog> selectHotBlogs();
    List<Blog> selectNewBlogsByUserId(long userId);
    List<Blog> selectCollectBlogsByUserId(long userId);
    List<Blog> selectFollowBlogsByUserId(long followerId);
    Boolean insertBlog(@Param("userId") long userId, @Param("title") String title, @Param("tag") String tag, @Param("content") String content, @Param("editTime") String editTime, @Param("status") int status);
    Boolean updateBlogById(@Param("id") long id, @Param("title") String title, @Param("tag") String tag, @Param("content") String content, @Param("editTime") String editTime);
    Boolean switchStatusById(@Param("id") long id, @Param("status") int status);
    Boolean deleteBlogById(long id);
    Boolean increaseViewsById(long id);
    Boolean increaseCollectsById(long id);
    Boolean decreaseCollectsById(long id);
}
