package com.thairshop.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	 private static final long serialVersionUID = 1L;
	 
	 public ResourceNotFoundException(int code, String message) {
	        super();
	        this.code = code;
	        this.message = message;
	    }

	    private int code;
	    private String message;

	    public int getCode() {
	        return code;
	    }

	    public void setCode(int code) {
	        this.code = code;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
}
