package com.example.USEME_SpringServer.controller;


import com.example.USEME_SpringServer.model.Homework;
import com.example.USEME_SpringServer.model.Task;
import com.example.USEME_SpringServer.model.statistic.Statistic;
import com.example.USEME_SpringServer.repository.StatisticRepository;
import com.example.USEME_SpringServer.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping
    public List<Statistic> getStatistics(@RequestParam(name = "student_id") Long studentId,
                                         @RequestParam(name = "homework_id") Long homeworkId,
                                         @RequestParam(name = "task_id") Long taskId) {
        return statisticService.getStatistic(studentId, homeworkId, taskId);
    }

    @GetMapping("/group")
    public List<Statistic> findStatisticInGroup(@RequestParam(name = "student_id") Long studentId,
                                                @RequestParam(name = "group_id") Long groupId) {
        return statisticService.findStatisticInGroup(studentId, groupId);
    }

    @GetMapping("/student")
    public List<Statistic> findStudentStatistic(@RequestParam(name = "student_id") Long studentId) {
        return statisticService.findStudentStatistic(studentId);
    }

    @GetMapping("/homework")
    public List<Statistic> findHomeworkStatistic(@RequestParam(name = "homework_id") Long homeworkId) {
        return statisticService.findHomeworkStatistic(homeworkId);
    }
    @PostMapping("/create")
    public List<Statistic> createHomeworkStatistic(@RequestBody List<Statistic> statistics) {
        return statisticService.createHomeworkStatistic(statistics);
    }
}
