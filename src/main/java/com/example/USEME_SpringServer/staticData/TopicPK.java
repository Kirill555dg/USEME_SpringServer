package com.example.USEME_SpringServer.staticData;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopicPK implements Serializable {

    @Column(name = "subject_name")
    private String subject;
    @Column(name = "topic_num")
    private Short topicNumber;
}
