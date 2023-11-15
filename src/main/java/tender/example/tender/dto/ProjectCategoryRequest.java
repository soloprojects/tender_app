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
public class ProjectCategoryRequest {

    public Long Id;

    @Schema(example= "Himalayas Welding", description = "Name of project")
    @NotNull(message = "project name shouldn't be null")
    public String name;

}
