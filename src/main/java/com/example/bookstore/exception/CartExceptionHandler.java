package com.example.bookstore.exception;

import com.example.bookstore.dto.ResponseBookDTO;
import com.example.bookstore.dto.ResponseCartDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CartExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseCartDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
        List<String> errMsg=errorList.stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseCartDTO responseCartDTO =new ResponseCartDTO("Exception while processing REST request", errMsg.toString());
        return new ResponseEntity<>(responseCartDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CartException.class)
    public ResponseEntity<ResponseCartDTO> handlePayrollException(CartException exception){
        ResponseCartDTO responseCartDTO =new ResponseCartDTO("Exception while processing REST request",exception.getMessage());
        return new ResponseEntity<>(responseCartDTO,HttpStatus.BAD_GATEWAY);
    }
}
