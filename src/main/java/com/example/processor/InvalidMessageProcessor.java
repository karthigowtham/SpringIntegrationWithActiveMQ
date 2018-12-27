package com.example.processor;

import org.springframework.stereotype.Service;

@Service("invalidMessageProcessor")
public class InvalidMessageProcessor {
	
	public void processInvalidMsg(String msg) {	
		//processing logic for invalid message or just logger 
		System.out.println("Input Message is invalid");
		System.out.println("****** "+ msg + "*****");
	}

}
