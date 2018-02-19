package ideablog.service.impl;

import ideablog.dao.IStatisticDao;
import ideablog.model.Statistic;
import ideablog.service.IStatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("statisticService")
public class StatisticServiceImpl implements IStatisticService {

    @Resource
    private IStatisticDao statisticDao;

    @Override
    public Statistic selectStatisticByUserId(long userId) {
        return this.statisticDao.selectStatisticByUserId(userId);
    }
}