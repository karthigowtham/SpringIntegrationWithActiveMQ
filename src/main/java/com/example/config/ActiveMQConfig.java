package com.example.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import javax.jms.Queue;
 
@Configuration
public class ActiveMQConfig {
 
    public static final String INPUT_MQ_QUEUE = "active.input.queue";
    public static final String OUTPUT_MQ_QUEUE = "active.output.queue";
 
    @Bean
    public Queue inputJMSQueue() {
        return new ActiveMQQueue(INPUT_MQ_QUEUE);
    }
    
    @Bean
    public Queue outputJMSQueue() {
        return new ActiveMQQueue(OUTPUT_MQ_QUEUE);
    }
}
