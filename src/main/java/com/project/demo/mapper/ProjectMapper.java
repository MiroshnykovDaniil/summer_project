package com.project.demo.mapper;

import com.project.demo.model.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {

        int projectId = rs.getInt("project_ID");
        String projectName = rs.getString("name");

        return new Project(projectId, projectName);
    }
}
