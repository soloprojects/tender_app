package tender.example.tender.iservice;

import tender.example.tender.dto.ProjectCategoryRequest;
import tender.example.tender.entity.ProjectCategory;

import java.util.List;

public interface IProjectCategory {

    ProjectCategory findById(Long Id);

    List<ProjectCategory> findAll();

    void create(ProjectCategoryRequest requestDto);

    ProjectCategory update(ProjectCategoryRequest requestDto);

    void delete(Long Id);

    void deleteMultiple(List<Long> Id);

}
