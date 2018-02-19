package ideablog.dao;

import ideablog.model.Statistic;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatisticDao {

    Statistic selectStatisticByUserId(long userId);
}
