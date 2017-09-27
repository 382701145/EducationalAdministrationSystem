package com.yyw.eas.bean;


public class LoginResponse {

    private String Data;
    private String DataCount;
    private String IsSuccess;
    private String Message;

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getDataCount() {
        return DataCount;
    }

    public void setDataCount(String dataCount) {
        DataCount = dataCount;
    }

    public String getIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        IsSuccess = isSuccess;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "Data='" + Data + '\'' +
                ", DataCount='" + DataCount + '\'' +
                ", IsSuccess='" + IsSuccess + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}
