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
public class IndustryRequest {

    public Long Id;

    @Schema(example= "Marine", description = "Name of industry")
    @NotNull(message = "Industry name shouldn't be null")
    public String name;

    @Schema(example= "Under sea operations", description = "details of industry")
    @NotNull(message = "Details shouldn't be null")
    public String details;

}
