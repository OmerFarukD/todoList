package com.example.todolist.utils.results;
import lombok.Data;

@Data
public class ReturnModel<T> {

    private ReturnModel() {
    }

    private boolean success;
    private String message;
    private T data;

    public static <T> ReturnModel<T> fail(T data, String message){
        ReturnModel<T> returnModel = new ReturnModel<>();
        returnModel.setData(data);
        returnModel.setSuccess(false);
        returnModel.setMessage(message);
        return  returnModel;
    }

    public static <T> ReturnModel<T> fail(T data){
        ReturnModel<T> returnModel = new ReturnModel<>();
        returnModel.setData(data);
        returnModel.setSuccess(false);
        return  returnModel;
    }

    public  static  ReturnModel<NoDataDto>  fail(String message){
        ReturnModel<NoDataDto> returnModel = new ReturnModel<>();
        returnModel.setMessage(message);
        returnModel.setSuccess(false);
        return  returnModel;
    }

    public static <T> ReturnModel<T> success(T data, String message){
        ReturnModel<T> returnModel = new ReturnModel<>();
        returnModel.setData(data);
        returnModel.setSuccess(true);
        returnModel.setMessage(message);
        return  returnModel;
    }

    public static <T> ReturnModel<T> success(T data){
        ReturnModel<T> returnModel = new ReturnModel<>();
        returnModel.setData(data);
        returnModel.setSuccess(true);
        return  returnModel;
    }

    public  static  ReturnModel<NoDataDto>  success(String message){
        ReturnModel<NoDataDto> returnModel = new ReturnModel<>();
        returnModel.setMessage(message);
        returnModel.setSuccess(true);
        return  returnModel;
    }
}
