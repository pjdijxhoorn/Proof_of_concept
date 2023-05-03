package com.runninggames.dungeon_run.Controllers;

import com.runninggames.dungeon_run.Dtos.UserInputDto;
import com.runninggames.dungeon_run.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping(value = "")
    public ResponseEntity<UserInputDto> createUser(@RequestBody UserInputDto userInputDto) {;

        String newUsername = userService.createUser(userInputDto);
        //userService.addAuthority(newUsername, "ROLE_USER");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }



}
