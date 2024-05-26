package com.example.USEME_SpringServer.controller;


import com.example.USEME_SpringServer.exception.NotFoundException;
import com.example.USEME_SpringServer.model.Category;
import com.example.USEME_SpringServer.model.Subject;
import com.example.USEME_SpringServer.model.topic.Topic;
import com.example.USEME_SpringServer.model.topic.TopicPK;
import com.example.USEME_SpringServer.repository.CategoryRepository;
import com.example.USEME_SpringServer.repository.SubjectRepository;
import com.example.USEME_SpringServer.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
public class StaticDataController {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private SubjectRepository subjectRepo;

    @Autowired
    private TopicRepository topicRepo;

    @GetMapping("/subjects")
    public List<Subject> getSubjects(){
        return subjectRepo.findAll();
    }

    @GetMapping("/topics")
    public List<Topic> getTopics(@RequestParam String subject) {
        return topicRepo.findAllByPkSubject(subject);
    }

    @PostMapping("/topics/topic")
    public Topic getTopics(@RequestBody TopicPK topicPK) {
        return topicRepo.findById(topicPK)
                .orElseThrow(() -> new NotFoundException("Указанный тип задач не найден"));
    }

    @GetMapping("/categories")
    public List<Category> getCategories(@RequestParam String subject, @RequestParam Short topic) {
        TopicPK pk = new TopicPK(subject, topic);
        return categoryRepo.findAllByPk(pk.getSubject(), pk.getTopicNumber());
    }

}
