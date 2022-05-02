package com.springboot.application.OnlineBookStore.exceptions;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandlingController implements ErrorController {

    @RequestMapping("/error")
    public String handleError()
    {
        return"error.html";
    }
}
