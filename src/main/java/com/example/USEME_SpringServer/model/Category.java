package com.example.USEME_SpringServer.model;


import com.example.USEME_SpringServer.model.topic.TopicPK;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private TopicPK pk;

    @Id
    @Column(name = "category_name")
    private String category;
}
