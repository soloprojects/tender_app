package tender.example.tender.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {

    public Long Id;

    @Schema(example= "Mountain Climbing", description = "Name of project")
    @NotNull(message = "Project name shouldn't be null")
    public String name;

    @Schema(example= "2024-10-22", description = "Start date of project")
    @NotNull(message = "Start date shouldn't be null")
    public String startDate;

    @Schema(example= "2025-10-22", description = "End date of project")
    @NotNull(message = "End date shouldn't be null")
    public String endDate;

    @Schema(example= "25000000.50", description = "Name of project")
    @NotNull(message = "Project cost shouldn't be null")
    public Double cost;

    @Schema(example= "1", description = "Industry")
    @NotNull(message = "Industry shouldn't be null")
    public Long industryId;

    @Schema(example= "1", description = "Project terms")
    @NotNull(message = "Project terms shouldn't be null")
    public Long projectTermId;

    @Schema(example= "1", description = "Category of project")
    @NotNull(message = "Project category shouldn't be null")
    public Long projectCategoryId;

    @Schema(example= "Under sea operations", description = "details of Project")
    @NotNull(message = "Details shouldn't be null")
    public String details;

}
