package com.example.demo.controllers;

import com.example.demo.services.VkBotService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class VkBotController {

    private final VkBotService vkBotService;

    @Value("${vk.confirmation.code}")
    private String confirmationCode;

    public VkBotController(VkBotService vkBotService) {
        this.vkBotService = vkBotService;
    }

    @PostMapping
    public ResponseEntity<String> handleCallback(@RequestBody String body) {
        JSONObject json;
        try {
            json = new JSONObject(body);
        } catch (JSONException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format");
        }

        String type = json.optString("type");

        if ("confirmation".equals(type)) {
            return ResponseEntity.ok(confirmationCode);
        }

        if (type.equals("message_new")) {
            vkBotService.handleNewMessage(json);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported event type");
        }

        return ResponseEntity.ok("ok");
    }
}
