package com.example.api.resource;

import com.example.api.jwt.CustomJwt;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = { RequestMethod.GET }
)
public class AuthController {

//    @GetMapping("/hello-moderator")
//    @PreAuthorize("hasAuthority('moderator')")
//    public Message helloModerator() {
//        var jwt = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
//        var message = MessageFormat
//                .format("Hello fullstack master {0} {1}, how is it going today?",
//                        jwt.getFirstname(), jwt.getLastname());
//        return new Message(message);
//    }
//
//
//    @GetMapping("/hello-admin")
//    @PreAuthorize("hasAuthority('admin')")
//    public Message helloAdmin() {
//        var jwt = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
//        var message = MessageFormat
//                .format("Hello fullstack master {0} {1}, how is it going today?",
//                        jwt.getFirstname(), jwt.getLastname());
//        return new Message(message);
//    }
//
//
//    @GetMapping("/hello-both")
//    @PreAuthorize("hasAuthority('admin') or hasAuthority('moderator')")
//    public Message helloBoth() {
//        var jwt = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
//        var message = MessageFormat
//                .format("Hello fullstack master {0} {1}, how is it going today?",
//                        jwt.getFirstname(), jwt.getLastname());
//        return new Message(message);
//    }
//
//    record Message(String message) {}
}
