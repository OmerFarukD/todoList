package com.example.todolist.services.missions;

import com.example.todolist.repository.missions.MissionRepository;
import com.example.todolist.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class MissionBusinessRules {

private  final MissionRepository repository;


public void missionTitleMustBeUnique(String title){

    long count = repository.countByTitle(title);

    if (count>=1){
        throw new NotFoundException(MissionMessages.missionNotFoundByTitleMessage(title));
    }
}

}
