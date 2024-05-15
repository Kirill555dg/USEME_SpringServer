package com.example.USEME_SpringServer.model.statistic;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "statistics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {

    @EmbeddedId
    private StatisticPK pk;

    @Column(name = "correct")
    private Boolean isCorrect;
}
