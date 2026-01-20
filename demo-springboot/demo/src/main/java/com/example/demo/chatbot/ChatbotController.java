package com.example.demo.chatbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {
    private final List<JsonNode> questions;

    public ChatbotController() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new ClassPathResource("chatbot_questions.json").getInputStream());
        questions = new ArrayList<>();
        root.forEach(questions::add);
    }

    @GetMapping("/questions")
    public ResponseEntity<List<JsonNode>> getQuestions() {
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/ask")
    public ResponseEntity<Map<String, String>> ask(@RequestParam String question, @RequestParam String lang) {
        for (JsonNode q : questions) {
            if (q.get(lang) != null && q.get(lang).asText().equalsIgnoreCase(question)) {
                Map<String, String> response = new HashMap<>();
                response.put("answer", q.get("answer_" + lang).asText());
                return ResponseEntity.ok(response);
            }
        }
        Map<String, String> notFound = new HashMap<>();
        notFound.put("answer", "Question non trouvée / Question not found / السؤال غير موجود / السؤال ما كايناش");
        return ResponseEntity.ok(notFound);
    }
}
