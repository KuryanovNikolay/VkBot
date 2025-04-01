package com.example.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;

import java.util.Random;

@Repository
public class VkBotDao {

    public void sendMessage(String userId, String text, String vkApiToken) {
        String url = "https://api.vk.com/method/messages.send";
        long randomId = new Random().nextLong();
        String messageText = "Вы сказали: " + text;

        String params = String.format(
                "user_id=%s&message=%s&access_token=%s&random_id=%d&v=5.199",
                userId, messageText, vkApiToken, randomId);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url + "?" + params,
                    HttpMethod.GET,
                    null,
                    String.class
            );
            System.out.println("Сообщение успешно отправлено: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Ошибка при отправке сообщения: " + e.getMessage());
        }
    }
}
