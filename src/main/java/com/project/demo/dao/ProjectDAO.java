package com.project.demo.dao;

import com.project.demo.mapper.ProjectMapper;
import com.project.demo.model.Project;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.util.Collection;

public class ProjectDAO extends JdbcDaoSupport {



    @Autowired
    public ProjectDAO(DataSource dataSource){this.setDataSource(dataSource);}

    public Project FindProjectNames(){
        String SQL = "select name from project where 1";


        //Object[] params = new Object[] { userName };
        ProjectMapper mapper = new ProjectMapper();
        try {
            Project project = this.getJdbcTemplate().queryForObject(SQL, mapper);
            return project;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
