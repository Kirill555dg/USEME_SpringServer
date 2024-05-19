package com.example.USEME_SpringServer.staticData;


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

    @GetMapping("/categories")
    public List<Category> getCategories(@RequestParam String subject, @RequestParam Short topic) {
        TopicPK pk = new TopicPK(subject, topic);
        return categoryRepo.findAllByPk(pk.getSubject(), pk.getTopicNumber());
    }

}
