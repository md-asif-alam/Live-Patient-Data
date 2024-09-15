package com.producer.main.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

//
//@RestController
//public class kafkaProducerService {
//
//
//
//private static final Logger LOGGER = LoggerFactory.getLogger(kafkaProducerService.class);
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    private static final String TOPIC = "topic_demo";
//
//    @GetMapping("/send")
//    public String sendMessage(@RequestParam("message") String message) {
//        kafkaTemplate.send(TOPIC, message);
//        return "Message sent to Kafka topic";
//    }
//
//    @Scheduled(fixedRate = 5000) // Send a message every 5 seconds
//    public void sendScheduledMessage() {
//
////        Map<String,Integer> map=new HashMap<>();
//        String simulatedData = "Temperature: " + (20 + Math.random() * 10) + "°C, Blood Pressure: " + (80 + Math.random() * 20) + " mmHg";
//        kafkaTemplate.send(TOPIC, simulatedData);
//        LOGGER.info("Message Sent: {}", simulatedData);
//
//    }
//}


import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class KafkaProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    private static final String TOPIC = "topic_demo_json";

    private boolean isRunning=false;


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Random random = new Random();



    @Scheduled(fixedRate = 2000)
    public void sendMessage() {

        if(isRunning) {
            String data = generatePatientData();
            LOGGER.info("Message Sent: {}", data);
            kafkaTemplate.send(TOPIC, data);

        }
    }

//    public void sendMessage(String data) {
//        LOGGER.info("Message Sent: {}", data);
//        kafkaTemplate.send(TOPIC, data);
//    }


    public String generatePatientData() {
        int temperature = 36 + random.nextInt(4);  // Generates a random temperature between 36 and 39
        int bloodPressure = 100 + random.nextInt(40); // Generates a random systolic BP between 100 and 140
//        int diastolicBP = 60 + random.nextInt(40); // Generates a random diastolic BP between 60 and 100
        return String.format("{\"temperature\": %d, \"bloodPressure\": %d}", temperature, bloodPressure);
    }

    public void startSending(){
        isRunning=true;
    }

    public void stopSending(){
        isRunning=false;
    }

    public boolean isRunning(){
        return isRunning;
    }





}


//LOGGER.info("Received message: {}", message);

//private static final Logger LOGGER = LoggerFactory.getLogger(PatientDataConsumer.class);