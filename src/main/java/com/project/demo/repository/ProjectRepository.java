package com.project.demo.repository;

import com.project.demo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class ProjectRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(Project project){
        System.out.println(project.getProjectName());
        System.out.println(project.getShortName());
        System.out.println(project.getDescription());
        System.out.println(project.getInitialEffort());
        System.out.println(project.getTimeStart());
        System.out.println(project.getTimeEnd());
        System.out.println(project.getRisks());
            String s="INSERT into project.project (name, short_name, description, initial_effort, time_start, time_end, risks,project_ID) VALUES ("+
                    "\""+project.getProjectName()+"\""+","+"\""+project.getShortName()+"\""+","+"\""+project.getDescription()+"\""+","+project.getInitialEffort()+
                    ","+"\""+project.getTimeStart()+"\""+","+"\""+project.getTimeEnd()+"\""+","+"\""+project.getRisks()+"\""+","+project.getProjectId()+")";
        System.out.println(s);
        entityManager.createNativeQuery(s)
                .executeUpdate();
       /* entityManager.createNativeQuery("INSERT into project.project (name, short_name, description, initial_effort, time_start, time_end, risks,project_ID) VALUES (?,?,?,?,?,?,?,?)")
        .setParameter(1,project.getProjectName())
        .setParameter(2,project.getShortName())
        .setParameter(3,project.getDescription())
        .setParameter(4,project.getInitialEffort())
        .setParameter(5,project.getTimeStart())
        .setParameter(6,project.getTimeEnd())
        .setParameter(7,project.getRisks())
        .setParameter(8,project.getProjectId());}
        catch (NullPointerException e){
            e.printStackTrace();
        };*/

    }
}
