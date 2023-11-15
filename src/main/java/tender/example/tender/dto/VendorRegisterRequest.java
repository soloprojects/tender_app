package tender.example.tender.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorRegisterRequest {

    public Long Id;

    @Schema(example= "Solomon", description = "firstname of user")
    @NotNull(message = "Firstname shouldn't be null")
    public String firstname;

    @Schema(example= "Nweze", description = "lastname of user")
    @NotNull(message = "Firstname shouldn't be null")
    public String lastname;

    @Schema(example= "solomonnweze@gmail.com", description = "email of user")
    @Email(message = "Firstname shouldn't be null")
    public String email;

    @NotNull(message = "Password shouldn't be null")
    @Schema(example= "password", description = "password of user")
    public String password;

    @NotNull(message = "Confirmation Password shouldn't be null")
    @Schema(example= "Confirmation password", description = "password of user")
    public String confirmPassword;

    @NotNull(message = "Role shouldn't be null")
    @Schema(example= "2", description = "Role of user")
    public Long role_id;

    @NotNull(message = "location shouldn't be null")
    @Schema(example= "Lekki phase 1, Lagos", description = "Location of user vendor")
    public String location;

    @Schema(example= "A company that deals in value creation", description = "details of vendor")
    @NotNull(message = "Details of vendor shouldn't be null")
    public String details;

    @NotNull(message = "Industry shouldn't be null")
    @Schema(example= "1", description = "Industry of user vendor")
    public Long industryId;

}
