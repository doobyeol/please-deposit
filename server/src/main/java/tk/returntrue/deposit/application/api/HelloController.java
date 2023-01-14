package tk.returntrue.deposit.application.api;

import org.springframework.web.bind.annotation.GetMapping;
import tk.returntrue.deposit.application.annotations.ApiController;

@ApiController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
