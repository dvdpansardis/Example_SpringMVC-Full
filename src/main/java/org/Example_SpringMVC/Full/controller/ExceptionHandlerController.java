package org.Example_SpringMVC.Full.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoResultException.class)
    public ModelAndView tratarDetalheNaoEncontrado(Exception exception){
        System.out.println("ERRO GENERICO " + exception.getMessage());
        exception.printStackTrace();

        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("exception", exception.getMessage());

        return modelAndView;
    }

}
