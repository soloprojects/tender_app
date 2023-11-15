package tender.example.tender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tender.example.tender.entity.ProjectTerms;

@Repository
public interface ProjectTermsRepository extends JpaRepository<ProjectTerms, Long> {
}
