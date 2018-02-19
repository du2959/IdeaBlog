package ideablog.service;

import ideablog.model.Statistic;

public interface IStatisticService {

    Statistic selectStatisticByUserId(long userId);
}