package tender.example.tender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tender.example.tender.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
