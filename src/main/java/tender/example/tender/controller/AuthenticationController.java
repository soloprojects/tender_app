package tender.example.tender.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tender.example.tender.dto.AuthenticationRequest;
import tender.example.tender.dto.RoleRequest;
import tender.example.tender.service.AuthenticationService;
import tender.example.tender.service.RoleService;
import tender.example.tender.utility.ResponseHandler;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  private final RoleService roleService;
  private final AuthenticationManager authenticationManager;

  @PostMapping("/authenticate")
  public ResponseEntity<Object> authenticate(
      @Valid @RequestBody AuthenticationRequest request
  ) {
    try{
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),
                      request.getPassword()
              )
      );
      return ResponseHandler.generateResponse("User Logged in successfully", HttpStatus.OK, service.authenticate(request));
    }catch(Exception ex){
      return ResponseHandler.generateResponse("Unexpected error occurred", HttpStatus.MULTI_STATUS, ex.getMessage());
    }

  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

  @PostMapping("/logout")
  public ResponseEntity<Object> logout(
          HttpServletRequest request,
          HttpServletResponse response,
          Authentication authentication
  ) {
    service.logout(request, response, authentication);
    return ResponseHandler.generateResponse("User has Logged out", HttpStatus.OK, null);

  }

  @PostMapping("/role/create")
  public ResponseEntity<Object> createRole(
          @Valid @RequestBody RoleRequest request
  ) {

    return ResponseHandler.generateResponse("Role created successfully", HttpStatus.CREATED, roleService.saveRole(request));
  }

  @GetMapping("/role/get")
  public ResponseEntity<Object> getRoles() {

    return ResponseHandler.generateResponse("List of roles retrieved successfully", HttpStatus.OK, roleService.getAll());
  }

}
