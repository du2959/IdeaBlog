package ideablog.service;

import ideablog.model.RFollow;

public interface IRFollowService {

    RFollow selectRFollow(long userId, long followerId);
    Boolean insertRFollow(long userId, long followerId);
    Boolean deleteRFollow(long userId, long followerId);
}