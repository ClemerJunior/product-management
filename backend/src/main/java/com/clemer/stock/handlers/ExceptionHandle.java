package com.clemer.stock.handlers;

import com.clemer.stock.exceptions.CategoryDoesNotExistException;
import com.clemer.stock.exceptions.CategoryNotFoundException;
import com.clemer.stock.exceptions.ProductNotFoundException;
import com.clemer.stock.utils.Messages;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandle {

    Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseError handleNotFoundException(NoSuchElementException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.NOT_FOUND.value());
        responseError.setDescription(Messages.RESOURCE_NOT_FOUND);
        logger.error(Messages.RESOURCE_NOT_FOUND, ex);
        return responseError;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseError handleProductNotFoundException(EntityNotFoundException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.NOT_FOUND.value());
        responseError.setDescription(Messages.PRODUCT_NOT_FOUND);
        logger.error(ex.getMessage(), ex);
        return responseError;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseError handleProductNotFoundException(ProductNotFoundException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.NOT_FOUND.value());
        responseError.setDescription(Messages.PRODUCT_NOT_FOUND);
        return responseError;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseError handleCategoryNotFoundException(CategoryNotFoundException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.NOT_FOUND.value());
        responseError.setDescription(Messages.CATEGORY_NOT_FOUND);
        return responseError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryDoesNotExistException.class)
    public ResponseError handleCategoryDoesNotExistException(CategoryDoesNotExistException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.BAD_REQUEST.value());
        responseError.setDescription(Messages.CATEGORY_DOES_NOT_EXIST);
        return responseError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseError handleBadCredentialsException(BadCredentialsException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.BAD_REQUEST.value());
        responseError.setDescription(Messages.INVALID_CREDENTIALS);
        logger.error(ex.getMessage(), ex);
        return responseError;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseError handleException(Exception ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseError.setDescription(Messages.INTERNAL_ERROR);
        logger.error(ex.getMessage(), ex);
        return responseError;
    }
}
