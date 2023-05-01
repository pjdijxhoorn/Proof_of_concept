package com.runninggames.dungeon_run.Dtos;

import com.runninggames.dungeon_run.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class MessageInputDto {

    private String text;
    private String reciever;


}
