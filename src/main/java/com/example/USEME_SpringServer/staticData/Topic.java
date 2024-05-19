package com.example.USEME_SpringServer.staticData;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "topics")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Topic {


    @EmbeddedId
    private TopicPK pk;

    @Column(name = "topic_name")
    private String topicName;
}
