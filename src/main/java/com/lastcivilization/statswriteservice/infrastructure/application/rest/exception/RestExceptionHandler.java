package com.lastcivilization.statswriteservice.infrastructure.application.rest.exception;

import com.lastcivilization.statswriteservice.domain.exception.ApplicationException;
import com.lastcivilization.statswriteservice.domain.exception.NotEnoughMoneyException;
import com.lastcivilization.statswriteservice.domain.exception.StatsNotFoundException;
import com.lastcivilization.statswriteservice.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    ErrorEntity handleApplicationException(ApplicationException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    @ResponseStatus(BAD_REQUEST)
    ErrorEntity handleNotEnoughMoneyException(NotEnoughMoneyException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(StatsNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ErrorEntity handleStatsNotFoundException(StatsNotFoundException exception){
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ErrorEntity handleUserNotFoundException(UserNotFoundException exception){
        return new ErrorEntity(exception.getMessage());
    }
}
