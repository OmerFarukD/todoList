package com.example.todolist.services.missions;

import com.example.todolist.dtos.missions.MissionCreateDto;
import com.example.todolist.dtos.missions.MissionResponseDto;
import com.example.todolist.entities.Mission;
import com.example.todolist.repository.missions.MissionRepository;
import com.example.todolist.utils.exceptions.NotFoundException;
import com.example.todolist.utils.results.NoDataDto;
import com.example.todolist.utils.results.ReturnModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class MissionManager implements MissionService {

    private final MissionRepository missionRepository;
    private final MissionBusinessRules businessRules;

    @Override
    public ReturnModel<MissionResponseDto> createMission(MissionCreateDto dto) {

        businessRules.missionTitleMustBeUnique(dto.title());

        Mission mission = MissionCreateDto.convertToEntity(dto);

        Mission createdMission = this.missionRepository.save(mission);

        MissionResponseDto responseDto = MissionResponseDto.convertToResponseDto(createdMission);
        return ReturnModel.success(responseDto,MissionMessages.missionAddedMessage);
    }

    @Override
    public ReturnModel<List<MissionResponseDto>> getAll() {
        List<Mission> missions = this.missionRepository.findAll();
        List<MissionResponseDto> responseDtos = missions
                .stream()
                .map(MissionResponseDto::convertToResponseDto)
                .toList();

        return ReturnModel.success(responseDtos);
    }

    @Override
    public ReturnModel<MissionResponseDto> getById(Long id) {
        Mission mission = this.missionRepository.findById(id).orElseThrow(()-> new NotFoundException(MissionMessages.missionNotFoundMessage(id)));
        return ReturnModel.success(MissionResponseDto.convertToResponseDto(mission));

    }

    @Override
    public ReturnModel<NoDataDto> delete(Long id) {
        Mission mission = this.missionRepository.findById(id).orElseThrow(()-> new NotFoundException(MissionMessages.missionNotFoundMessage(id)));

        missionRepository.delete(mission);
        return ReturnModel.success(MissionMessages.missionDeletedMessage);
    }
}
