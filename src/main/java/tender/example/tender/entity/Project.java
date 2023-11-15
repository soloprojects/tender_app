package tender.example.tender.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "project")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_date;

    private Double cost;

    private String name;

    private String details;

    @ManyToOne
    @JoinColumn(name = "industry_id", insertable = false, updatable = false)
    private Industry industry;
    private long industry_id;

    @ManyToOne
    @JoinColumn(name = "project_category_id", insertable = false, updatable = false)
    private ProjectCategory projectCategory;
    private long project_category_id;

    @ManyToOne
    @JoinColumn(name = "project_terms_id", insertable = false, updatable = false)
    private ProjectTerms projectTerms;
    private long project_term_id;


}
