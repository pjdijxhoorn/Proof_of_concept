package com.runninggames.dungeon_run.Services;

import com.runninggames.dungeon_run.Dtos.DungeonRunInputDto;
import com.runninggames.dungeon_run.Exceptions.RecordNotFoundException;
import com.runninggames.dungeon_run.Models.DungeonRun;
import com.runninggames.dungeon_run.Models.User;
import com.runninggames.dungeon_run.Repositories.DungeonRunRepository;
import com.runninggames.dungeon_run.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DungeonRunService {

    private final DungeonRunRepository dungeonRunRepository;
    private final UserRepository userRepository;

    public DungeonRunService(DungeonRunRepository dungeonRunRepository, UserRepository userRepository) {
        this.dungeonRunRepository = dungeonRunRepository;
        this.userRepository = userRepository;
    }


    public long createDungeonRun(DungeonRunInputDto dungeonRunInputDto, String user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new RecordNotFoundException("no User found with id " + user_id));
// dit moet ik nog vervangen voor de user die daadwerkelijk ingelogd is!
        DungeonRun dungeonRun = new DungeonRun();
        dungeonRun.setDate(LocalDateTime.now());
        dungeonRun.setDistance_In_Km(dungeonRunInputDto.getDistance_In_Km());
        dungeonRun.setHeartbeat_Per_Minute(dungeonRunInputDto.getHeartbeat_Per_Minute());
        dungeonRun.setTraining_Time(dungeonRunInputDto.getTraining_Time());
        double timeInSeconds = dungeonRun.getTraining_Time().toSecondOfDay();
        dungeonRun.setSpeed_meter_per_minute((dungeonRunInputDto.getDistance_In_Km()*1000)/( timeInSeconds/60));
        dungeonRun.setUser(user);
        double Fitnessscore = calculateFitnessScore(dungeonRun, user);
        dungeonRun.setFitness_score(Fitnessscore);
        dungeonRun = dungeonRunRepository.save(dungeonRun);

        user.setRunning_index(user.calculateRunningIndex(user.getDungeonRuns()));
        userRepository.save(user);

        return dungeonRun.getId();
    }

    public static double calculateFitnessScore(DungeonRun dungeonRun, User user) {
        double timeInSeconds = dungeonRun.getTraining_Time().toSecondOfDay();
        double distanceInMeters = dungeonRun.getDistance_In_Km()* 1000;
        System.out.println(distanceInMeters+ " afstand in meters");
        double pacePerMinute = (distanceInMeters / (timeInSeconds/60));
        System.out.println(pacePerMinute+" aftstand per minuut");
        double VO2max = (15 * (dungeonRun.getHeartbeat_Per_Minute() /  user.getMax_heart_rate()))  * pacePerMinute  + 3.5;
        System.out.println(VO2max+ " vo2max");
        return  VO2max;
    }
}
