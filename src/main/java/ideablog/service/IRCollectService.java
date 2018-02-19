package ideablog.service;

import ideablog.model.RCollect;

public interface IRCollectService {

    RCollect selectRCollect(long blogId, long userId);
    Boolean insertRCollect(long blogId, long userId);
    Boolean deleteRCollect(long blogId, long userId);
}