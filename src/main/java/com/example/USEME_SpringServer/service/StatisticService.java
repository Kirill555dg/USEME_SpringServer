package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Homework;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.statistic.Statistic;
import com.example.USEME_SpringServer.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {
    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;

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

    public List<Statistic> findStatisticInGroup(Long studentId, Long groupId) {
        Student student = studentService.findStudentById(studentId);
        Group group = groupService.findGroupById(groupId);
        List<Statistic> statistics = new ArrayList<>();
        for (Homework homework : group.getHomeworks()){
            if (statisticRepository.existsByPk_StudentAndPk_Homework(student, homework)){
                statistics.addAll(statisticRepository.getStatistic(studentId, homework.getId(), -1L));
            }
        }
        return statistics;
    }
}
