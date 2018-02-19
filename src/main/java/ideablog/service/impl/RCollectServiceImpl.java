package ideablog.service.impl;

import ideablog.dao.IRCollectDao;
import ideablog.model.RCollect;
import ideablog.service.IRCollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("rCollectService")
public class RCollectServiceImpl implements IRCollectService {

    @Resource
    private IRCollectDao rCollectDao;


    @Override
    public RCollect selectRCollect(long blogId, long userId) {
        return this.rCollectDao.selectRCollect(blogId, userId);
    }

    @Override
    public Boolean insertRCollect(long blogId, long userId) {
        return this.rCollectDao.insertRCollect(blogId, userId);
    }

    @Override
    public Boolean deleteRCollect(long blogId, long userId) {
        return this.rCollectDao.deleteRCollect(blogId, userId);
    }
}