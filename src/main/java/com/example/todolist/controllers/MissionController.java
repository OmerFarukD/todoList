package com.example.todolist.controllers;

import com.example.todolist.dtos.missions.MissionCreateDto;
import com.example.todolist.dtos.missions.MissionResponseDto;
import com.example.todolist.dtos.missions.MissionUpdateDto;
import com.example.todolist.services.missions.MissionService;
import com.example.todolist.utils.pagination.PageRequest;
import com.example.todolist.utils.pagination.Paginate;
import com.example.todolist.utils.results.NoDataDto;
import com.example.todolist.utils.results.ReturnModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/missions")
@RequiredArgsConstructor
public final class MissionController {


    private final MissionService missionService;

    @PostMapping("/add")
    public ResponseEntity<ReturnModel<MissionResponseDto>> add(@RequestBody MissionCreateDto dto){
        var result = missionService.createMission(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/getall")
    public ResponseEntity<ReturnModel<List<MissionResponseDto>>> getAll(){
        var result = this.missionService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/getbyid")
    public ResponseEntity<ReturnModel<MissionResponseDto>> getById(@RequestParam Long id){
        var result = this.missionService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/getallpaginate")
    public ResponseEntity<ReturnModel<Paginate<MissionResponseDto>>> getAllPaginate(
            @RequestParam("index") int index,
            @RequestParam("size") int size
            ){
        var result = this.missionService.getAllByPaginate(new PageRequest(index,size));
        return ResponseEntity.ok(result);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ReturnModel<NoDataDto>> delete(@RequestParam Long id){
        var result = this.missionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<ReturnModel<MissionResponseDto>> update(@Valid @RequestBody MissionUpdateDto dto){
        var result = this.missionService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/complete")
    public  ResponseEntity<ReturnModel<NoDataDto>> completeMission(@RequestParam Long id){
        var result = this.missionService.completeMission(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
