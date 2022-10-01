package pe.com.mauricio.javac.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/user")
    public ResponseEntity<UserResponse> test(@RequestBody UserRequest request){
        return null;
    }
}
