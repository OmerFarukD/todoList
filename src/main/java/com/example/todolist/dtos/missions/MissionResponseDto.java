package com.example.todolist.dtos.missions;

import com.example.todolist.entities.Mission;
import com.example.todolist.entities.enums.MissionStatus;
import com.example.todolist.entities.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Date;

@Builder
public record MissionResponseDto(

        Long id,

        String title,
        String description,

        MissionStatus missionStatus,

        Date createdTime,
        Priority priority,
        Date startTime,
        Date endDate,
        String categoryName

) {

    public static MissionResponseDto convertToResponseDto(Mission mission){
        return MissionResponseDto.builder()
                .id(mission.getId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .missionStatus(mission.getMissionStatus())
                .createdTime(mission.getCreatedTime())
                .priority(mission.getPriority())
                .startTime(mission.getStartTime())
                .endDate(mission.getEndDate())
                .categoryName(mission.getCategory().getName())
                .build();

    }

}
