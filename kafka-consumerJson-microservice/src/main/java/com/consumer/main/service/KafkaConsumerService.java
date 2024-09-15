package com.consumer.main.service;


import com.consumer.main.config.WebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


//@Service
//public class KafkaConsumerService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);
//
//    private final WebSocketHandler webSocketHandler;
//
//    public KafkaConsumerService(WebSocketHandler webSocketHandler) {
//        this.webSocketHandler = webSocketHandler;
//    }
//
//    @KafkaListener(topics = "topic_demo", groupId = "my-group")
//    public void listen(String message) {
//        LOGGER.info("Received message: {}", message);
//        webSocketHandler.sendMessage(message);
//    }
//}
//



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;

    private final WebSocketHandler webSocketHandler;

    public KafkaConsumerService(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }



    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);


    //URL: ws://localhost:8080/app/ws/patient-data

    @KafkaListener(topics = "topic_demo_json", groupId = "my-group")
    public void consume(String message) {
        // Broadcast data to all WebSocket subscribers
        LOGGER.info("Received message: {}", message);
//        messagingTemplate.convertAndSend("/topic/patientData", message);
        webSocketHandler.sendMessage(message);
    }
}


//LOGGER.info("Received message: {}", message);

//private static final Logger LOGGER = LoggerFactory.getLogger(PatientDataConsumer.class);
//PatientDataConsumer