package tender.example.tender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tender.example.tender.entity.User;
import tender.example.tender.service.UserService;
import tender.example.tender.utility.ResponseHandler;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("get/{email}")
    public ResponseEntity<Object> getUser(@PathVariable("email") String email){
        User user = userService.findByEmail(email);
        return ResponseHandler.generateResponse("Obtained user successfully", HttpStatus.OK, user);
    }

}
