package ideablog.service.impl;

import ideablog.dao.IRFollowDao;
import ideablog.model.RFollow;
import ideablog.service.IRFollowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("rFollowService")
public class RFollowServiceImpl implements IRFollowService {

    @Resource
    private IRFollowDao rFollowDao;

    @Override
    public RFollow selectRFollow(long userId, long followerId) {
        return this.rFollowDao.selectRFollow(userId, followerId);
    }

    @Override
    public Boolean insertRFollow(long userId, long followerId) {
        return this.rFollowDao.insertRFollow(userId, followerId);
    }

    @Override
    public Boolean deleteRFollow(long userId, long followerId) {
        return this.rFollowDao.deleteRFollow(userId, followerId);
    }
}