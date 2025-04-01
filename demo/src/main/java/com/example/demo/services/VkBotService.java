package com.example.demo.services;

import com.example.demo.dao.VkBotDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VkBotService {

    private final VkBotDao vkBotDao;

    @Value("${vk.api.token}")
    private String vkApiToken;

    public VkBotService(VkBotDao vkBotDao) {
        this.vkBotDao = vkBotDao;
    }

    public void handleNewMessage(JSONObject json) {
        JSONObject object = json.optJSONObject("object");
        if (object != null) {
            JSONObject message = object.optJSONObject("message");
            if (message != null) {
                String userId = message.optString("from_id");
                String text = message.optString("text");
                vkBotDao.sendMessage(userId, text, vkApiToken);
            }
        }
    }
}
