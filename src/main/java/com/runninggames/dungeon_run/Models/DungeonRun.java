package com.runninggames.dungeon_run.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "dungeon_runs")
public class DungeonRun {

    //variables.........................................
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime date;
    private  double distance_In_Km;
    private LocalTime training_Time;
    private double heartbeat_Per_Minute;
    private double fitness_score;
    private double speed_meter_per_minute;

    //relations.........................................
    @ManyToOne
    private User user;

}
