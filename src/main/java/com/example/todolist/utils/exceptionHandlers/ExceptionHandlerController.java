package com.example.todolist.utils.exceptionHandlers;
import com.example.todolist.utils.exceptions.BusinessException;
import com.example.todolist.utils.exceptions.NotFoundException;
import com.example.todolist.utils.results.NoDataDto;
import com.example.todolist.utils.results.ReturnModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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


}
