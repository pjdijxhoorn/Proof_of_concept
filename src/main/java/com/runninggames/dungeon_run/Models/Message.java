package com.runninggames.dungeon_run.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private long id;
    private String text;
    private LocalDateTime time_send;
    private String sender;

    @ManyToOne
    private User reciever;

    @ManyToOne
    private Clan clan;

}
