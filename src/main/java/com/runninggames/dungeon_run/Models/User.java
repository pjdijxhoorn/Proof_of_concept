package com.runninggames.dungeon_run.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    //security - login variables.........................................
    @Id
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, length = 255)
    private String password;
    @Column
    private String apikey;
    @NotNull
    @Email
    private String email;
    @Column(nullable = false)
    private boolean enabled = true;

    //training variables.........................................
    private int max_heart_rate;
    private int resting_heart_rate;
    private int running_index;
    private double weight;
    private LocalDate date_of_birth;
    private int xp;
    private int level;

    //relations.........................................
    @OneToMany(mappedBy = "user")
    private List<DungeonRun> dungeonRuns;
    @OneToMany(mappedBy = "reciever")
    private List<Message>mailbox;
    @ManyToOne
    private Clan clan;
    @OneToOne
    private Clan clanleader;




/*
    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();
*/
    //methodes.........................................

    public int calculateRunningIndex(List<DungeonRun> dungeonRuns) {
        if(dungeonRuns.isEmpty()) {
            throw new IllegalArgumentException("List of dungeon runs is empty.");
        }
        double sumOfLastNSessions = 0;
        int n = Math.min(dungeonRuns.size(), 5); // calculate the sum of last n sessions, where n is minimum of 5 and the size of the input list
        for(int i = dungeonRuns.size()-1; i >= dungeonRuns.size()-n; i--) {
            sumOfLastNSessions += dungeonRuns.get(i).getFitness_score();
        }
        double averageOfLastNSessions = sumOfLastNSessions / n;
        double runningIndex = (100 * averageOfLastNSessions) / (dungeonRuns.size() > 0 ? dungeonRuns.get(dungeonRuns.size()-1).getFitness_score() : 0); // handle the case when there are no dungeon runs provided
        System.out.println(runningIndex);
        return (int) runningIndex;
    }
}
