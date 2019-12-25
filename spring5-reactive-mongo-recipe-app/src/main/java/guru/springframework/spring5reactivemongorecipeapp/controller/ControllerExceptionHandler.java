package guru.springframework.spring5reactivemongorecipeapp.controller;

import guru.springframework.spring5reactivemongorecipeapp.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(Exception exception, Model model){

        model.addAttribute("exception", exception);

        return "recipe/404error";
    }

    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public String handleNumberFormat(Exception exception, Model model){

        model.addAttribute("exception", exception);

        return "recipe/400error";
    }
*/
}
