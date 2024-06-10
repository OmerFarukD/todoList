package com.example.todolist.utils.exceptionHandlers;
import com.example.todolist.utils.exceptions.BusinessException;
import com.example.todolist.utils.exceptions.NotFoundException;
import com.example.todolist.utils.results.NoDataDto;
import com.example.todolist.utils.results.ReturnModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ReturnModel<NoDataDto>> handleNotFoundException(NotFoundException exception){

        ReturnModel<NoDataDto> returnModel = ReturnModel.fail(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnModel);
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ReturnModel<NoDataDto>> handleBusinessException(BusinessException exception){
        ReturnModel<NoDataDto> returnModel = ReturnModel.fail(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnModel);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ReturnModel<Map<String,String>>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((x)-> {
            errors.put(x.getField(),x.getDefaultMessage());
        });
        ReturnModel<Map<String,String>> response = ReturnModel.success(errors,"Validasyon hataları");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
