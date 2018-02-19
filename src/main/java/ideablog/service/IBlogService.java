package ideablog.service;

import ideablog.model.Blog;

import java.util.List;

public interface IBlogService {

    Blog selectBlogById(long id);
    Blog selectPublicBlogById(long id);
    List<Blog> selectAllBlogs();
    List<Blog> selectNewBlogs();
    List<Blog> selectHotBlogs();
    List<Blog> selectNewBlogsByUserId(long userId);
    List<Blog> selectCollectBlogsByUserId(long userId);
    List<Blog> selectFollowBlogsByUserId(long followerId);
    Boolean insertBlog(long userId, String title, String tag, String content, String editTime, int status);
    Boolean updateBlogById(long id, String title, String tag, String content, String editTime);
    Boolean switchStatusById(long id, int status);
    Boolean deleteBlogById(long id);
    Boolean increaseViewsById(long id);
    Boolean increaseCollectsById(long id);
    Boolean decreaseCollectsById(long id);
}