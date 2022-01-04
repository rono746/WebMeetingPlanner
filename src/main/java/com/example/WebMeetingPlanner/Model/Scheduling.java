package com.example.WebMeetingPlanner.Model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "meets")
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long meeting_id;
    private static final String AU_DATE_FORMAT = "MM-dd-yyyy 'T' HH:mm 'T' a";


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)

    private LocalDateTime EndDate;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    @Column(nullable = false)
//    private LocalTime endTime;

    //
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate startDatee;
//
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    private LocalTime startTimee;
//
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    private LocalTime endTimee;
//
    @Column(nullable = false, length = 20)
    private String eventPattern;

    @Column(nullable = false, length = 20)
    private String capacity;
    @Column(nullable = false, length = 20)
    private String topic;
    @Column(nullable = false, length = 20)
    private String description;
    @Column(nullable = false, length = 20)
    private String resources;


    @ManyToOne
    @JoinColumn(name = "Room_id")
    private TracomRooms tracomRooms;

    @ManyToOne
    @JoinColumn(name = "Organisation_id")
    private Organization organization;

    @ManyToMany
    @JoinTable(name = "users_meeting",
//             , referencedColumnName = "meeting_id"
            joinColumns = @JoinColumn(name = "meeting_id"),
//             , referencedColumnName = "id"
            inverseJoinColumns = @JoinColumn(name = "user_id"))

    private Set<User> users = new HashSet<>();
//     private List<User> users;

    public Scheduling() {
    }

    public Scheduling(long meeting_id, LocalDateTime startDate, LocalDateTime endDate, String eventPattern, String capacity, String topic, String description, String resources, TracomRooms tracomRooms, Organization organization, Set<User> users) {
        this.meeting_id = meeting_id;
        this.startDate = startDate;
        EndDate = endDate;
        this.eventPattern = eventPattern;
        this.capacity = capacity;
        this.topic = topic;
        this.description = description;
        this.resources = resources;
        this.tracomRooms = tracomRooms;
        this.organization = organization;
        this.users = users;
    }
}