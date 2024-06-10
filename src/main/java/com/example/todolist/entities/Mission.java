package com.example.todolist.entities;

import com.example.todolist.entities.enums.MissionStatus;
import com.example.todolist.entities.enums.Priority;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@Table(name = "missions")
@AllArgsConstructor
@NoArgsConstructor
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "mission_status")
    private MissionStatus missionStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;


    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


}
