package com.producer.main.contoller;

import com.producer.main.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/start")
    public String startSending(){

        if(!kafkaProducerService.isRunning()){
            kafkaProducerService.startSending();
            return "Started sending random patient data to kafka";
        }

        return "Producer is already running";

    }

    @GetMapping("/stop")
    public String stopSending(){

        if(kafkaProducerService.isRunning()){
            kafkaProducerService.stopSending();
            return "Stopped sending random patient data to kafka";
        }

        return "Producer is not running";

    }

}
