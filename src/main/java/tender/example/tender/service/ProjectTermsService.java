package tender.example.tender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tender.example.tender.dto.ProjectCategoryRequest;
import tender.example.tender.dto.ProjectTermsRequest;
import tender.example.tender.entity.ProjectCategory;
import tender.example.tender.entity.ProjectTerms;
import tender.example.tender.exception.BusinessException;
import tender.example.tender.iservice.IProjectTerms;
import tender.example.tender.repository.ProjectTermsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectTermsService implements IProjectTerms {

    private final ProjectTermsRepository projectTermsRepo;

    @Override
    public ProjectTerms findById(Long Id) {
        ProjectTerms find = projectTermsRepo.findById(Id)
                .orElseThrow(() -> new BusinessException("Data not found", HttpStatus.NOT_FOUND));
        return find;
    }

    @Override
    public List<ProjectTerms> findAll() {

        List<ProjectTerms> findAll = projectTermsRepo.findAll();
        return findAll;
    }

    @Override
    public void create(ProjectTermsRequest requestDto) {
        ProjectTerms create = ProjectTerms.builder()
                .name(requestDto.getName())
                .terms(String.valueOf(requestDto.getTerms()))
                .build();
        projectTermsRepo.save(create);
    }

    @Override
    public ProjectTerms update(ProjectTermsRequest requestDto) {
        ProjectTerms data = this.findById(requestDto.getId());
        data.setName(requestDto.getName());
        this.projectTermsRepo.save(data);
        return data;
    }

    @Override
    public void delete(Long Id) {
        projectTermsRepo.deleteById(Id);
    }

    @Override
    public void deleteMultiple(List<Long> idList) {
        projectTermsRepo.deleteAllById(idList);
    }
}
