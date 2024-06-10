package com.example.todolist.dtos.missions;

import com.example.todolist.entities.enums.MissionStatus;
import com.example.todolist.entities.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record MissionUpdateDto(

        @NotNull
        Long id,

        @NotNull
        @NotEmpty
        @NotBlank
        String title,

        @NotNull
        @NotEmpty
        @NotBlank
        String description,
        Priority priority,
        Date startTime,
        Date endDate,
        MissionStatus missionStatus
) {
}
