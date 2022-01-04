package com.example.WebMeetingPlanner;

import com.example.WebMeetingPlanner.Model.Role;
import com.example.WebMeetingPlanner.Repository.RoleRepository;
import com.example.WebMeetingPlanner.controller.SchedulingController;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
@EnableScheduling
//        (exclude = DataSourceAutoConfiguration.class)

public class SecurityApplication {




    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);

//        @Autowired
//        RoleRepository repo;
//
//        Role user = new Role("User");
//        Role admin = new Role("Admin");
//        Role customer = new Role("Organisation Officer");
//
//        repo.saveAll(List.of(user, admin, customer));
//
//        List<Role> listRoles = repo.findAll();
//
//        assertThat(listRoles.size()).isEqualTo(3);
    }



}



