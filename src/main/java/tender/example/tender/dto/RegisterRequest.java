package tender.example.tender.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tender.example.tender.entity.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

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
  public Long role_id;
}
