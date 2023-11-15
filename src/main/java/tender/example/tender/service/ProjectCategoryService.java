package tender.example.tender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tender.example.tender.dto.ProjectCategoryRequest;
import tender.example.tender.entity.ProjectCategory;
import tender.example.tender.exception.BusinessException;
import tender.example.tender.iservice.IProjectCategory;
import tender.example.tender.repository.ProjectCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectCategoryService implements IProjectCategory {

    private final ProjectCategoryRepository projectCatRepo;

    @Override
    public ProjectCategory findById(Long Id) {
        ProjectCategory find = projectCatRepo.findById(Id)
                .orElseThrow(() -> new BusinessException("Data not found", HttpStatus.NOT_FOUND));
        return find;
    }

    @Override
    public List<ProjectCategory> findAll() {

        List<ProjectCategory> findAll = projectCatRepo.findAll();
        return findAll;
    }

    @Override
    public void create(ProjectCategoryRequest requestDto) {
        ProjectCategory create = ProjectCategory.builder()
                .name(requestDto.getName())
                .build();
        projectCatRepo.save(create);
    }

    @Override
    public ProjectCategory update(ProjectCategoryRequest requestDto) {
        ProjectCategory data = this.findById(requestDto.getId());
        data.setName(requestDto.getName());
        this.projectCatRepo.save(data);

        return data;
    }

    @Override
    public void delete(Long Id) {
        projectCatRepo.deleteById(Id);
    }

    @Override
    public void deleteMultiple(List<Long> idList) {
        projectCatRepo.deleteAllById(idList);
    }
}
