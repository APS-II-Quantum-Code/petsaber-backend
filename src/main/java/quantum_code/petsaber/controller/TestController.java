package quantum_code.petsaber.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@PreAuthorize("hasRole('TUTOR')")
public class TestController {

    @GetMapping
    public String test(){
        return "test";
    }
}
