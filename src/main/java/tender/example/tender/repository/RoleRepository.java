package tender.example.tender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tender.example.tender.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
