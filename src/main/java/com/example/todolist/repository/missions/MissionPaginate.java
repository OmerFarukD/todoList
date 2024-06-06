package com.example.todolist.repository.missions;

import com.example.todolist.dtos.missions.MissionResponseDto;
import com.example.todolist.entities.Mission;
import com.example.todolist.utils.pagination.PageRequest;
import com.example.todolist.utils.pagination.Paginate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public final class MissionPaginate implements MissionPaginateRepository {

    private final EntityManager entityManager;
    @Override
    public Paginate<MissionResponseDto> getAllByPaginate(PageRequest pageRequest) {
        Paginate<Mission> missionPaginate = new Paginate<>();
        missionPaginate.setSize(pageRequest.size());
        missionPaginate.setIndex(pageRequest.index());

        String countQuery ="SELECT COUNT(m) FROM Mission m";
        int count = entityManager.createQuery(countQuery,Integer.class).getSingleResult();

        missionPaginate.setCount(count);


        String  jpql = "SELECT m From Mission m ORDER BY m.title asc";
        TypedQuery<Mission> query =  entityManager.createQuery(jpql,Mission.class);

        query.setFirstResult(pageRequest.index()* pageRequest.size());
        query.setMaxResults(pageRequest.size());

        missionPaginate.setItems(query.getResultList());


        return convertToPaginateDto(missionPaginate);
    }
}
