package tender.example.tender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tender.example.tender.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
