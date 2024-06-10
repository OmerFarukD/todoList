package com.example.todolist.services.missions;
import com.example.todolist.dtos.missions.MissionCreateDto;
import com.example.todolist.dtos.missions.MissionResponseDto;
import com.example.todolist.dtos.missions.MissionUpdateDto;
import com.example.todolist.entities.Mission;
import com.example.todolist.entities.enums.MissionStatus;
import com.example.todolist.repository.missions.MissionPaginateRepository;
import com.example.todolist.repository.missions.MissionRepository;
import com.example.todolist.utils.exceptions.NotFoundException;
import com.example.todolist.utils.pagination.PageRequest;
import com.example.todolist.utils.pagination.Paginate;
import com.example.todolist.utils.results.NoDataDto;
import com.example.todolist.utils.results.ReturnModel;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public non-sealed class MissionManager implements MissionService {

    private final MissionRepository missionRepository;
    private final MissionBusinessRules businessRules;
    private final MissionPaginateRepository paginateRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = MissionMessages.missionCache, allEntries = true)
    })
    public ReturnModel<MissionResponseDto> createMission(MissionCreateDto dto) {

        businessRules.missionTitleMustBeUnique(dto.title());

        Mission mission = MissionCreateDto.convertToEntity(dto);

        Mission createdMission = this.missionRepository.save(mission);

        MissionResponseDto responseDto = MissionResponseDto.convertToResponseDto(createdMission);
        return ReturnModel.success(responseDto,MissionMessages.missionAddedMessage);
    }

    @Override
    @Cacheable(cacheNames = MissionMessages.missionCache, key = "'allMissions'")
    public ReturnModel<List<MissionResponseDto>> getAll() {
        List<Mission> missions = this.missionRepository.findAll();
        List<MissionResponseDto> responseDtos = missions
                .stream()
                .map(MissionResponseDto::convertToResponseDto)
                .toList();

        return ReturnModel.success(responseDtos);
    }

    @Override
    @Cacheable(cacheNames = MissionMessages.missionCache, key = "#id")
    public ReturnModel<MissionResponseDto> getById(Long id) {
        Mission mission = this.missionRepository.findById(id).orElseThrow(()-> new NotFoundException(MissionMessages.missionNotFoundMessage(id)));
        return ReturnModel.success(MissionResponseDto.convertToResponseDto(mission));

    }

    @Override
    @Cacheable(cacheNames = MissionMessages.missionCache, key = "(#pageRequest.index().toString() + #pageRequest.size().toString())")
    public ReturnModel<Paginate<MissionResponseDto>> getAllByPaginate(PageRequest pageRequest) {

        Paginate<MissionResponseDto> pages = paginateRepository.getAllByPaginate(pageRequest);

        return ReturnModel.success(pages);

    }

    @Override
    @CacheEvict(cacheNames = MissionMessages.missionCache, key="#id")
    public ReturnModel<NoDataDto> delete(Long id) {
        Mission mission = this.missionRepository.findById(id).orElseThrow(()-> new NotFoundException(MissionMessages.missionNotFoundMessage(id)));

        missionRepository.delete(mission);
        return ReturnModel.success(MissionMessages.missionDeletedMessage);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = MissionMessages.missionCache, allEntries = true)
    })
    public ReturnModel<MissionResponseDto> update(MissionUpdateDto dto) {
        Mission mission = this.missionRepository.findById(dto.id()).orElseThrow(()-> new NotFoundException(MissionMessages.missionNotFoundMessage(dto.id())));
        mission.setMissionStatus(dto.missionStatus());
        mission.setTitle(dto.title());
        mission.setDescription(dto.description());
        mission.setStartTime(dto.startTime());
        mission.setEndDate(dto.endDate());
        mission.setPriority(dto.priority());
     Mission updateMission = missionRepository.save(mission);

     MissionResponseDto responseDto = MissionResponseDto.convertToResponseDto(updateMission);

        return ReturnModel.success(responseDto,MissionMessages.missionUpdatedMessage);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = MissionMessages.missionCache, allEntries = true)
    })

    public ReturnModel<NoDataDto> completeMission(Long id) {
        Mission mission = this.missionRepository.findById(id).orElseThrow(()-> new NotFoundException(MissionMessages.missionNotFoundMessage(id)));
        mission.setMissionStatus(MissionStatus.DONE);
        mission.setEndDate(new Date());
        return ReturnModel.success(MissionMessages.missionCompletedMessage);
    }


}
