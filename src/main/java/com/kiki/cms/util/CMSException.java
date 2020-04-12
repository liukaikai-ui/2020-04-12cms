package com.kiki.cms.util;

import org.springframework.web.servlet.HandlerExceptionResolver;

public class CMSException extends RuntimeException{

	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 1L;

	public String message;
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CMSException() {
		
	}
	
	public CMSException(String message) {
		super(message);
		this.message=message;
	}
}
