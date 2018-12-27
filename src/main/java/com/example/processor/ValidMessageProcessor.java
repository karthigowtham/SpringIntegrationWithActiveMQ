package com.example.processor;

import org.springframework.stereotype.Service;

@Service("validMessageProcessor")
public class ValidMessageProcessor {
	
	public String processValidMsg(String msg) {		
		//processing logic for valid message or just logger 
		System.out.println("Input Message is valid");
		System.out.println("*************"+ msg +" ***********  " );
		return msg;
	}

}
