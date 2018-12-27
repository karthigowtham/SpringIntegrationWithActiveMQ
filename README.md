# SpringIntegrationWithActiveMQ
This is a simple Spring Integration project which reads XML feed from queue and validate against XSD and transform the message. Finally drop the message in another queue.

## Getting Started

First start the ActiveMQ in local then build the application and run as Spring Boot app. 
Once application started put the XML message in queue (active.input.queue). See the sample xml in src/main/resources. 

The input message will be validated against XSD. 
    If valid - message send to validXMLChannel, 
    If invalid - message send to invalidXMLChannel.
Both channels has service activators where you can perform any code logic on valid, invalid messages if needed.

Then, valid message passed to transformerChannel for xslt tranformation. And outbound-channel-adapter drops the final output message into queue (active.output.queue.)

### Prerequisites

You will need ActiveMQ running in your local machine - to put the message in queue and verify the final message.
If you don't want to run the ActiveMQ locally, then create in-memory queue and use Rest endpoint(/putMessage) to drop the message in queue.
