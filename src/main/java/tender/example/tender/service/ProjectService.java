package tender.example.tender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tender.example.tender.dto.ProjectRequest;
import tender.example.tender.entity.Project;
import tender.example.tender.exception.BusinessException;
import tender.example.tender.iservice.IProject;
import tender.example.tender.repository.ProjectRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService implements IProject {

    private final ProjectRepository projectRepo;

    @Override
    public Project findById(Long Id) {
        Project find = projectRepo.findById(Id)
                .orElseThrow(() -> new BusinessException("Data not found", HttpStatus.NOT_FOUND));
        return find;
    }

    @Override
    public List<Project> findAll() {

        List<Project> findAll = projectRepo.findAll();
        return findAll;
    }

    @Override
    public void create(ProjectRequest requestDto) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date startDate = formatter.parse(requestDto.getStartDate());
            Date endDate = formatter.parse(requestDto.getEndDate());

            Project create = Project.builder()
                    .name(requestDto.getName())
                    .start_date(startDate)
                    .end_date(endDate)
                    .cost(requestDto.getCost())
                    .industry_id(requestDto.getIndustryId())
                    .project_term_id(requestDto.getProjectTermId())
                    .project_category_id(requestDto.getProjectCategoryId())
                    .details(requestDto.getDetails())
                    .build();
            projectRepo.save(create);

        }catch (Exception e) {
            throw new BusinessException("Data not found" + e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Project update(ProjectRequest requestDto) {
        Project data = this.findById(requestDto.getId());
        data.setName(requestDto.getName());
        this.projectRepo.save(data);
        return data;
    }

    @Override
    public void delete(Long Id) {
        projectRepo.deleteById(Id);
    }

    @Override
    public void deleteMultiple(List<Long> idList) {
        projectRepo.deleteAllById(idList);
    }
}
