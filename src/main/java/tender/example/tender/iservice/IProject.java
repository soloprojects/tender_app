package tender.example.tender.iservice;

import tender.example.tender.dto.ProjectRequest;
import tender.example.tender.entity.Project;

import java.util.List;

public interface IProject {

    Project findById(Long Id);

    List<Project> findAll();

    void create(ProjectRequest requestDto);

    Project update(ProjectRequest requestDto);

    void delete(Long Id);

    void deleteMultiple(List<Long> Id);
}
