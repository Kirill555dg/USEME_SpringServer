package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.model.statistic.Statistic;
import com.example.USEME_SpringServer.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    @Autowired
    private StatisticRepository statisticRepository;


    public List<Statistic> findStudentStatistic(Long studentId) {
        return statisticRepository.getStatistic(studentId, -1L, -1L);
    }

    public List<Statistic> findHomeworkStatistic(Long homeworkId) {
        return statisticRepository.getStatistic(-1L, homeworkId, -1L);
    }

    public List<Statistic> createHomeworkStatistic(List<Statistic> homeworkStatistic) {

        return statisticRepository.saveAll(homeworkStatistic);
    }

    public List<Statistic> getStatistic(Long studentId, Long homeworkId, Long taskId) {
        return statisticRepository.getStatistic(studentId,homeworkId,taskId);
    }
}
