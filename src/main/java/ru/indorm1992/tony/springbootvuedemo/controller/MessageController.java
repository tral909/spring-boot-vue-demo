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
    public Map<String, String> createMessage(@RequestBody Map<String, String> newMessage) {
        long counter = Long.valueOf(messages.get(messages.size() - 1).get("id"));
        newMessage.put("id", String.valueOf(++counter));
        messages.add(newMessage);
        return newMessage;
    }

    @PutMapping("{id}")
    public Map<String, String> updateMessage(@PathVariable String id, @RequestBody Map<String, String> newMessage) {
        Map<String, String> updateMess = findMessage(id);
        updateMess.put("text", newMessage.get("text"));
        return updateMess;
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
