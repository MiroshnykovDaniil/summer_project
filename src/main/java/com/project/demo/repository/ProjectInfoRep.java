package com.project.demo.repository;

import com.project.demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectInfoRep extends JpaRepository<Project,Integer> {

    default List<Project> findAllByIdName(String iterable) {
        return null;
    }
}
