package com.runninggames.dungeon_run.Controllers;

import com.runninggames.dungeon_run.Dtos.ClanInputDto;
import com.runninggames.dungeon_run.Dtos.MessageInputDto;
import com.runninggames.dungeon_run.Models.Clan;
import com.runninggames.dungeon_run.Models.Message;
import com.runninggames.dungeon_run.Repositories.UserRepository;
import com.runninggames.dungeon_run.Services.MessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    public  String createMessage(MessageInputDto messageInputDto) {

        // check sender is logged in user

      message.setTime_send(LocalDateTime.now());
        Optional<User>reciever = userRepository.g
        message.setReciever(messageInputDto.getReciever());

        newclan.setClan_name(clanInputDto.getClan_name());
        Clan clan = clanRepository.save(newclan);

        return clan.getClan_name();
    }




}
