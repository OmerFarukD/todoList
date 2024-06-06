package com.example.todolist.repository.missions;

import com.example.todolist.dtos.missions.MissionResponseDto;
import com.example.todolist.entities.Mission;
import com.example.todolist.utils.pagination.PageRequest;
import com.example.todolist.utils.pagination.Paginate;

import java.util.List;

public sealed interface MissionPaginateRepository permits MissionPaginate {

    default Paginate<MissionResponseDto> convertToPaginateDto(Paginate<Mission> missionPaginate){

        Paginate<MissionResponseDto> responseDtoPaginate = new Paginate<>();

        List<MissionResponseDto> dtos = missionPaginate
                .getItems()
                        .stream()
                                .map(MissionResponseDto::convertToResponseDto)
                                        .toList();
        responseDtoPaginate.setCount(missionPaginate.getCount());
        responseDtoPaginate.setIndex(missionPaginate.getIndex());
        responseDtoPaginate.setSize(missionPaginate.getSize());
        responseDtoPaginate.setItems(dtos);
        return responseDtoPaginate;
    }


    Paginate<MissionResponseDto> getAllByPaginate(PageRequest pageRequest);


}
