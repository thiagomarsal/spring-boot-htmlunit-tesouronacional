package com.example.config;

import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HtmlUnitConfig {

    @Value("${waitForBackgroundJavaScript}")
    private long waitForBackgroundJavaScript;

    @Bean
    public WebClient getWebClient() {
        final WebClient webClient = new WebClient();
        webClient.waitForBackgroundJavaScript(waitForBackgroundJavaScript);
        return webClient;
    }
}
