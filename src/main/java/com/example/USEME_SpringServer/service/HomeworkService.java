package com.example.USEME_SpringServer.service;


import com.example.USEME_SpringServer.exception.NotFoundException;
import com.example.USEME_SpringServer.model.Homework;
import com.example.USEME_SpringServer.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkService {

    @Autowired
    private HomeworkRepository homeworkRepository;


    public List<Homework> findAllHomeworks() {
        return homeworkRepository.findAll();
    }

    public List<Homework> findByGroup(Long id) {
        return homeworkRepository.findByGroup_Id(id);
    }

    public Homework findHomeworkById(Long id) {
        return homeworkRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Домашнее задание с id " + id + " не найдено"));
    }

    public Homework createHomework(Homework homework) {
        System.out.println(homework.toString());
        return homeworkRepository.save(homework);
    }

    public Homework updateHomework(Long id, Homework homework) {
        Homework old = findHomeworkById(id);
        old.setDateOfIssue(homework.getDateOfIssue());
        old.setDeadline(homework.getDeadline());
        old.setTitle(homework.getTitle());
        old.setTasks(homework.getTasks());
        return homeworkRepository.save(old);
    }


    public void deleteHomework(Long id) {
        homeworkRepository.deleteById(id);
    }
}
