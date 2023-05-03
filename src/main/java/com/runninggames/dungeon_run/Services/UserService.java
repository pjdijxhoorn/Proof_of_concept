package com.runninggames.dungeon_run.Services;

import com.runninggames.dungeon_run.Dtos.UserInputDto;
import com.runninggames.dungeon_run.Exceptions.BadRequestException;
import com.runninggames.dungeon_run.Exceptions.InvalidPassWordException;
import com.runninggames.dungeon_run.Models.User;
import com.runninggames.dungeon_run.Repositories.UserRepository;
import com.runninggames.dungeon_run.Utilities.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String createUser( UserInputDto userInputDto) {

        // check if the username is taken already and give error accordingly

        String password = userInputDto.getPassword();
        if (!validatePassword(password)) {
            throw new InvalidPassWordException("Your password must contain:\n At least 6 characters, 1 uppercase letter, 1 lowercase letter, 1 special character and may not contain any whitespaces");
        }
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userInputDto.setApikey(randomString);
        User newUser = userRepository.save(TransferDtoToUser(userInputDto));
        return newUser.getUsername();
    }


    public User TransferDtoToUser(UserInputDto userInputDto) {

        User user = new User();

        user.setUsername(userInputDto.getUsername());
        user.setPassword(userInputDto.getPassword());
        user.setEnabled(userInputDto.isEnabled());
        user.setApikey(userInputDto.getApikey());
        user.setEmail(userInputDto.getEmail());
        user.setDate_of_birth(userInputDto.getDate_of_birth());
        user.setMax_heart_rate(CalculateMax_heart_rate(user.getDate_of_birth()));
        user.setResting_heart_rate(userInputDto.getResting_heart_rate());
        user.setWeight(userInputDto.getWeight());
        return user;
    }

    public int CalculateAge(LocalDate date_of_birth){
        return Period.between(date_of_birth, LocalDate.now()).getYears();
    }

    public int CalculateMax_heart_rate(LocalDate date_of_birth){
        // 220 - age is a standard way to calculate Maxhearthrate
        int max_heart_rate = 220- CalculateAge(date_of_birth);
        return  max_heart_rate;
    }

    public Boolean validatePassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*?=])(?=\\S+$).{6,}$");
    }
/*  ^                 - start-of-string
    (?=.*[0-9])       - a digit must occur at least once
    (?=.*[a-z])       - a lower case letter must occur at least once
    (?=.*[A-Z])       - an upper case letter must occur at least once
    (?=.*[@#$%^&+=])  - a special character must occur at least once
    (?=\S+$)          - no whitespace allowed in the entire string
    .{6,}             - anything, at least six places though
    $                 # end-of-string*/


}

