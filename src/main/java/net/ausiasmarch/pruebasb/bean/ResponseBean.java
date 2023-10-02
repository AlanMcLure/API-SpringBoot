package net.ausiasmarch.pruebasb.bean;

public class ResponseBean {

    String message="";
    Integer code=200;


    public ResponseBean() {
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }

    
    
}
