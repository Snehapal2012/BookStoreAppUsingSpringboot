package com.example.bookstore.exception;

import com.example.bookstore.dto.ResponseUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseUserDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
        List<String> errMsg=errorList.stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Exception while processing REST request", errMsg.toString());
        return new ResponseEntity<>(responseUserDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseUserDTO> handlePayrollException(UserException exception){
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Exception while processing REST request",exception.getMessage());
        return new ResponseEntity<>(responseUserDTO,HttpStatus.BAD_GATEWAY);
    }
}
