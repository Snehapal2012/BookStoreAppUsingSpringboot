package com.example.bookstore.exception;

import com.example.bookstore.dto.ResponseBookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;
@ControllerAdvice
public class BookExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBookDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
        List<String> errMsg=errorList.stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseBookDTO responseBookDTO =new ResponseBookDTO("Exception while processing REST request", errMsg.toString());
        return new ResponseEntity<>(responseBookDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookException.class)
    public ResponseEntity<ResponseBookDTO> handlePayrollException(BookException exception){
        ResponseBookDTO responseBookDTO =new ResponseBookDTO("Exception while processing REST request",exception.getMessage());
        return new ResponseEntity<>(responseBookDTO,HttpStatus.BAD_GATEWAY);
    }
}
