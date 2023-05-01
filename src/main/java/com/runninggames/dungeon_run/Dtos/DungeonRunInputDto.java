package com.runninggames.dungeon_run.Dtos;

import com.runninggames.dungeon_run.Models.User;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Setter
public class DungeonRunInputDto {

    //variables.........................................
    private  double distance_In_Km;
    private double heartbeat_Per_Minute;
    private LocalTime training_Time;

    //relations.........................................
    private User user;

}

