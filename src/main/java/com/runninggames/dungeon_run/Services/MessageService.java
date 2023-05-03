package com.runninggames.dungeon_run.Services;

import com.runninggames.dungeon_run.Dtos.MessageInputDto;
import com.runninggames.dungeon_run.Exceptions.RecordNotFoundException;
import com.runninggames.dungeon_run.Models.Clan;
import com.runninggames.dungeon_run.Models.Message;
import com.runninggames.dungeon_run.Models.User;
import com.runninggames.dungeon_run.Repositories.ClanRepository;
import com.runninggames.dungeon_run.Repositories.MessageRepository;
import com.runninggames.dungeon_run.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ClanRepository clanRepository;


    public MessageService(UserRepository userRepository, MessageRepository messageRepository, ClanRepository clanRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.clanRepository = clanRepository;
    }


    public long createMessage(MessageInputDto messageInputDto){
        // set sender to logged in user

        User reciever = userRepository.findById(messageInputDto.getReciever()).orElseThrow(() -> new RecordNotFoundException("no User found with the name " + messageInputDto.getReciever()));
        Message message = new Message();
        message.setReciever(reciever);
        message.setText(messageInputDto.getText());
        message.setTime_send(LocalDateTime.now());
        message.setSender("bob");
        messageRepository.save(message);
        return message.getId();


    }

    public long createMessageClan(MessageInputDto messageInputDto){
        // set sender to logged in user
        // check if user is in this clan

        Clan reciever = clanRepository.findById(messageInputDto.getReciever()).orElseThrow(() -> new RecordNotFoundException("no User found with the name " + messageInputDto.getReciever()));
        Message message = new Message();
        message.setClan(reciever);
        message.setText(messageInputDto.getText());
        message.setTime_send(LocalDateTime.now());
        message.setSender("bob");
        messageRepository.save(message);
        return message.getId();


    }


}
