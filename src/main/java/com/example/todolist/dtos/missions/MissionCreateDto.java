package com.example.todolist.dtos.missions;

import com.example.todolist.entities.Category;
import com.example.todolist.entities.Mission;
import com.example.todolist.entities.enums.MissionStatus;
import com.example.todolist.entities.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record MissionCreateDto(

        @NotNull
        @NotEmpty
        @NotBlank
        String title,

        @NotNull
        @NotEmpty
        @NotBlank
        String description,

        @NotNull
        @NotEmpty
        @NotBlank
        Priority priority,
        Date startTime,
        Date endDate,
        int categoryId
        )  {

    public static Mission convertToEntity(MissionCreateDto missionCreateDto){

        Category category = new Category();
        category.setId(missionCreateDto.categoryId);

        return Mission.builder()
                .missionStatus(MissionStatus.IN_PROCESS)
                .createdTime(new Date())
                .category(category)
                .description(missionCreateDto.description)
                .endDate(missionCreateDto.endDate)
                .priority(missionCreateDto.priority)
                .title(missionCreateDto.title)
                .startTime(missionCreateDto.startTime)
                .build();


    }

}
