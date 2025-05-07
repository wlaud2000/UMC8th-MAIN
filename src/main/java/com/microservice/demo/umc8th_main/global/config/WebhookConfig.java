package com.microservice.demo.umc8th_main.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebhookConfig {

    @Value("${discord.webhook.url}")
    private String discordWebhookUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getDiscordWebhookUrl() {
        return discordWebhookUrl;
    }
}
