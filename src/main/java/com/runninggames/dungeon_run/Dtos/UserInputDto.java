package com.runninggames.dungeon_run.Dtos;

import com.runninggames.dungeon_run.Models.DungeonRun;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class UserInputDto {

    //security - login variables.........................................
    private String username;
    private String password;
    private String apikey;
    private String email;
    private boolean enabled = true;

    //training variables.........................................
    private int max_heart_rate;
    private int resting_heart_rate;
    private int running_index;
    private double weight;
    private LocalDate date_of_birth;

    //relations.........................................
    private List<DungeonRun> dungeonRuns;


}
