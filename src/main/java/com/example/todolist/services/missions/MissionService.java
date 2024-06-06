package com.example.todolist.services.missions;

import com.example.todolist.dtos.missions.MissionCreateDto;
import com.example.todolist.dtos.missions.MissionResponseDto;
import com.example.todolist.utils.results.NoDataDto;
import com.example.todolist.utils.results.ReturnModel;

import java.util.List;

public sealed interface MissionService permits MissionManager {
    ReturnModel<MissionResponseDto> createMission(MissionCreateDto dto);
    ReturnModel<List<MissionResponseDto>> getAll();

    ReturnModel<MissionResponseDto> getById(Long id);

    ReturnModel<NoDataDto> delete(Long id);



}
