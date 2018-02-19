package ideablog.dao;

import ideablog.model.RFollow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRFollowDao {

    RFollow selectRFollow(@Param("userId") long userId, @Param("followerId") long followerId);
    Boolean insertRFollow(@Param("userId") long userId, @Param("followerId") long followerId);
    Boolean deleteRFollow(@Param("userId") long userId, @Param("followerId") long followerId);
}
