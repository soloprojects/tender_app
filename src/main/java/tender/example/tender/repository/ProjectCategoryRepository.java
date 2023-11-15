package tender.example.tender.repository;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tender.example.tender.entity.ProjectCategory;

@Repository
public interface ProjectCategoryRepository extends JpaRepository<ProjectCategory, Long> {
}
