package ideablog.dao;

import ideablog.model.RCollect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRCollectDao {

    RCollect selectRCollect(@Param("blogId") long blogId, @Param("userId") long userId);
    Boolean insertRCollect(@Param("blogId") long blogId, @Param("userId") long userId);
    Boolean deleteRCollect(@Param("blogId") long blogId, @Param("userId") long userId);
}
