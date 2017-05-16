package com.zweihander.navup.notification;

import com.zweihander.navup.notification.service.Notification;
import com.zweihander.navup.notification.service.NotificationImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;



@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan("com.zweihander.navup.notification")
@Slf4j
public class NotificationApplication {

    private static SpringApplication application = new SpringApplication(NotificationApplication.class);

    public static void main(String[] args){
        //Environment env = application.run(args).getEnvironment();

        application.run("--debug");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Notification notification(){
        return new NotificationImpl();
    }

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("kendy.up.ac.za");
        javaMailSender.setPort(25);

        return javaMailSender;
    }

    public static void setApplication(SpringApplication application) {
        NotificationApplication.application = application;
    }
}
