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
public class ProjectTermsRequest {

    public Long Id;

    @Schema(example= "Marine", description = "Name of industry")
    @NotNull(message = "Industry name shouldn't be null")
    public String name;

    @Schema(example= "ROV Vehicle", description = "Multiple item and services requirement")
    @NotNull(message = "terms shouldn't be null")
    public Object terms;

}
