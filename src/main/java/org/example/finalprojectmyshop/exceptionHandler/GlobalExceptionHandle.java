package org.example.finalprojectmyshop.exceptionHandler;

import org.example.finalprojectmyshop.order.models.dtos.imports.SearchProductByNameDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandle {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ModelAndView processExceptionHandle(Exception e) {
        ModelAndView mnv = new ModelAndView("error-resource-not-found");
        mnv.addObject("searchProductByNameDTO", new SearchProductByNameDTO());

        return mnv;
    }

}
