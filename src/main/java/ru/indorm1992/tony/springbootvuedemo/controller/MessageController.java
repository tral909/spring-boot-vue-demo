package ru.indorm1992.tony.springbootvuedemo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("message")
public class MessageController {
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "one message"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "another message"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "third message"); }});
    }};

    @GetMapping
    public List<Map<String, String>> getMessages() {
        return messages;
    }

    @PostMapping
    public void createMessage(@RequestBody Map<String, String> newMessage) {
        int size = messages.size();
        newMessage.put("id", String.valueOf(++size));
        messages.add(newMessage);
    }

    @PutMapping("{id}")
    public void updateMessage(@PathVariable String id, @RequestBody Map<String, String> newMessage) {
        Map<String, String> foundMess = findMessage(id);
        foundMess.put("text", newMessage.get("text"));
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable String id) {
        Map<String, String> foundMess = findMessage(id);
        messages.remove(foundMess);
    }

    private Map<String, String> findMessage(String id) {
        return messages.stream()
                .filter(msg -> msg.get("id").equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

}
