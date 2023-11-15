package tender.example.tender.iservice;

import tender.example.tender.dto.ProjectTermsRequest;
import tender.example.tender.entity.ProjectTerms;

import java.util.List;

public interface IProjectTerms {

    ProjectTerms findById(Long Id);

    List<ProjectTerms> findAll();

    void create(ProjectTermsRequest requestDto);

    ProjectTerms update(ProjectTermsRequest requestDto);

    void delete(Long Id);

    void deleteMultiple(List<Long> Id);

}
