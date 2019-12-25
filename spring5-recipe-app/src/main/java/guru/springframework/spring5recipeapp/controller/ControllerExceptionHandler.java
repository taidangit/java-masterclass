package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(Exception exception, Model model){

        log.error("Handling Not Found Exception");
        log.error(exception.getMessage());

        model.addAttribute("exception", exception);

        return "recipe/404error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormat(Exception exception, Model model){

        log.error("Handling Number Format Exception");
        log.error(exception.getMessage());

        model.addAttribute("exception", exception);

        return "recipe/400error";
    }
}
