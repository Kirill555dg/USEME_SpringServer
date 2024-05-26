package com.example.USEME_SpringServer.service;

import com.example.USEME_SpringServer.exception.NotFoundException;
import com.example.USEME_SpringServer.model.Group;
import com.example.USEME_SpringServer.model.Homework;
import com.example.USEME_SpringServer.model.Student;
import com.example.USEME_SpringServer.model.Task;
import com.example.USEME_SpringServer.model.statistic.Statistic;
import com.example.USEME_SpringServer.model.statistic.StatisticPK;
import com.example.USEME_SpringServer.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
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
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private TaskService taskService;

    public List<Statistic> findStudentStatistic(Long studentId) {
        return statisticRepository.getAllStatistics(studentId, -1L, -1L);
    }

    public List<Statistic> findHomeworkStatistic(Long homeworkId) {
        return statisticRepository.getAllStatistics(-1L, homeworkId, -1L);
    }

    public List<Statistic> createHomeworkStatistic(List<Statistic> homeworkStatistic) {
        for (Statistic statistic : homeworkStatistic) {
            StatisticPK pk = statistic.getPk();
            Student student = studentService.findStudentById(pk.getStudent().getId());
            Homework homework = homeworkService.findHomeworkById(pk.getHomework().getId());
            Task task = taskService.findById(pk.getTask().getId());
            statistic.setPk(new StatisticPK(student, homework, task));
        }
        return statisticRepository.saveAll(homeworkStatistic);
    }

    public List<Statistic> getAllStatistics(Long studentId, Long homeworkId, Long taskId) {
        return statisticRepository.getAllStatistics(studentId,homeworkId,taskId);
    }

    public List<Statistic> findStatisticInGroup(Long studentId, Long groupId) {
        Student student = studentService.findStudentById(studentId);
        Group group = groupService.findGroupById(groupId);
        List<Statistic> statistics = new ArrayList<>();
        for (Homework homework : group.getHomeworks()){
            if (statisticRepository.existsByPk_StudentAndPk_Homework(student, homework)){
                statistics.addAll(statisticRepository.getAllStatistics(studentId, homework.getId(), -1L));
            }
        }
        return statistics;
    }

    public Statistic getStatistic(Long studentId, Long homeworkId, Long taskId) {
        Student student = studentService.findStudentById(studentId);
        Homework homework = homeworkService.findHomeworkById(homeworkId);
        Task task = taskService.findById(taskId);
        return statisticRepository.findById(new StatisticPK(student, homework, task))
                .orElseThrow(() -> new NotFoundException("Указанной статистики не существует"));
    }
}
