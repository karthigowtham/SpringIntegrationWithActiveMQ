package com.sample.restcontroller;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.ActiveMQConfig;

@RestController
public class QueueController {
	
	//Call this REST service to put message in queue, in case if you do not running ActiveMQ locally and having a in-memory active MQ
	//set spring.activemq.in-memory=true, this will create in-memory queue.(No need to start ActiveMQ in local)
	
	@Autowired
	@Qualifier("jmsConnectionFactory")
	private ConnectionFactory jmsConnectionFactory;
	
	private String queueName = ActiveMQConfig.INPUT_MQ_QUEUE;
	private MessageProducer jmsamqproducer;
	private Destination jmsamqdestination;
	private Session jmsamqsession;
	private Connection jmsamqconn;
	
	//Preparing sample XML message
	private StringBuffer sf = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
			.append("<Document><CstmrCdtTrfInitn><GrpHdr><MsgId>ABC/090928/CCT001</MsgId><CtrlSum>11500000</CtrlSum><Nm>ABC Corporation</Nm>")
			.append("<PstlAdr><StrtNm>Times Square</StrtNm><BldgNb>7</BldgNb><PstCd>NY 10036</PstCd><TwnNm>New York</TwnNm><Ctry>US</Ctry></PstlAdr></GrpHdr></CstmrCdtTrfInitn></Document>");	
	
	@GetMapping(value = "/putMessage")
	@ResponseStatus(HttpStatus.OK)
	public String putMessage() {
		try {
			setUpJmsSession();
			sendMsg();
		} catch (Exception e) {
			System.out.println("Exception Happened:" +e);
		}	
		finally {
			try {
				jmsamqsession.close();
				jmsamqconn.close();
			} catch (Exception e) {
				System.out.println("Exception Happened - Closing connection :" +e);
			}	
			
			
		}
		return "Success";		
	}	
	
	public void setUpJmsSession() throws JMSException {
		jmsamqconn = jmsConnectionFactory.createConnection();
		jmsamqconn.start();
		jmsamqsession = jmsamqconn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		jmsamqdestination = jmsamqsession.createQueue(queueName);
		jmsamqproducer = jmsamqsession.createProducer(jmsamqdestination);
		jmsamqproducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}	

	public void sendMsg() throws JMSException {
		TextMessage msg = jmsamqsession.createTextMessage(sf.toString());
		jmsamqproducer.send(jmsamqdestination, msg);
	}

}