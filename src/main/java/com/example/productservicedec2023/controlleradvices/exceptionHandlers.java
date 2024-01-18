package com.example.productservicedec2023.controlleradvices;

import com.example.productservicedec2023.dtos.ExceptionDto;
import com.example.productservicedec2023.exceptions.productNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.datatype.DatatypeConfigurationException;

@ControllerAdvice
public class exceptionHandlers {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Void> handleIdException(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }

    @ExceptionHandler(productNotExistsException.class)
    public ResponseEntity<ExceptionDto> handleProductNotExistException(
            productNotExistsException exception){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setDetail("Details: Check the product id. It probably doesn't exist.");

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
