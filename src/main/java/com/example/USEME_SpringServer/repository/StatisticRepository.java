package com.example.USEME_SpringServer.repository;

import com.example.USEME_SpringServer.model.statistic.Statistic;
import com.example.USEME_SpringServer.model.statistic.StatisticPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, StatisticPK> {

}
