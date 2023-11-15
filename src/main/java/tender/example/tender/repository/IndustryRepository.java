package tender.example.tender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tender.example.tender.entity.Industry;

import java.util.List;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from industry i where i.id in(?1)", nativeQuery = true)
    void deleteByIdIn(List<Long> idList);

}
