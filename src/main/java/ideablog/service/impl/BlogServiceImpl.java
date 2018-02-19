package ideablog.service.impl;

import ideablog.dao.IBlogDao;
import ideablog.dao.IUserDao;
import ideablog.model.Blog;
import ideablog.model.User;
import ideablog.service.IBlogService;
import ideablog.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("blogService")
public class BlogServiceImpl implements IBlogService {

    @Resource
    private IBlogDao blogDao;

    @Override
    public Blog selectBlogById(long id) {
        return this.blogDao.selectBlogById(id);
    }

    @Override
    public Blog selectPublicBlogById(long id) {
        return this.blogDao.selectPublicBlogById(id);
    }

    @Override
    public List<Blog> selectAllBlogs() {
        return this.blogDao.selectAllBlogs();
    }

    @Override
    public List<Blog> selectNewBlogs() {
        return this.blogDao.selectNewBlogs();
    }

    @Override
    public List<Blog> selectHotBlogs() {
        return this.blogDao.selectHotBlogs();
    }

    @Override
    public List<Blog> selectNewBlogsByUserId(long userId) {
        return this.blogDao.selectNewBlogsByUserId(userId);
    }

    @Override
    public List<Blog> selectCollectBlogsByUserId(long userId) {
        return this.blogDao.selectCollectBlogsByUserId(userId);
    }

    @Override
    public List<Blog> selectFollowBlogsByUserId(long followerId) {
        return this.blogDao.selectFollowBlogsByUserId(followerId);
    }

    @Override
    public Boolean insertBlog(long userId, String title, String tag, String content, String editTime, int status) {
        return this.blogDao.insertBlog(userId, title, tag, content, editTime, status);
    }

    @Override
    public Boolean updateBlogById(long id, String title, String tag, String content, String editTime) {
        return this.blogDao.updateBlogById(id, title, tag, content, editTime);
    }

    @Override
    public Boolean switchStatusById(long id, int status) {
        return this.blogDao.switchStatusById(id, status);
    }

    @Override
    public Boolean deleteBlogById(long id) {
        return this.blogDao.deleteBlogById(id);
    }

    @Override
    public Boolean increaseViewsById(long id) {
        return this.blogDao.increaseViewsById(id);
    }

    @Override
    public Boolean increaseCollectsById(long id) {
        return this.blogDao.increaseCollectsById(id);
    }

    @Override
    public Boolean decreaseCollectsById(long id) {
        return this.blogDao.decreaseCollectsById(id);
    }
}