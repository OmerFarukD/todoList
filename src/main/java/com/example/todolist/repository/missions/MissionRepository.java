package com.example.todolist.repository.missions;

import com.example.todolist.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission,Long> {
    public long countByTitle(String title);

}
