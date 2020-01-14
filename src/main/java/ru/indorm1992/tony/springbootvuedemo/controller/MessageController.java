package ru.indorm1992.tony.springbootvuedemo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.indorm1992.tony.springbootvuedemo.domain.Message;
import ru.indorm1992.tony.springbootvuedemo.domain.Views;
import ru.indorm1992.tony.springbootvuedemo.repo.MessageRepo;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepo messageRepo;

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> getMessages() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message createMessage(@RequestBody Message newMessage) {
        newMessage.setCreationDate(LocalDateTime.now());
        return messageRepo.save(newMessage);
    }

    @PutMapping("{id}")
    public Message updateMessage(@PathVariable("id") Message messageFromDb,
                                 @RequestBody Message messageForUpdate) {
        BeanUtils.copyProperties(messageForUpdate, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Message message) {
        messageRepo.delete(message);
    }

}