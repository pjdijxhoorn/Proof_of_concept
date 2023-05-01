package com.runninggames.dungeon_run.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "messages")
public class Message {

    @Id
    private long id;
    private String text;
    private LocalDateTime time_send;
    private String sender;

    @ManyToOne
    private User reciever;

}
