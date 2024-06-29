package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest {
    private final MyAvro1Client avro1Client;
    private final MyAvro2Client avro2Client;

    public Rest(MyAvro1Client avro1Client, MyAvro2Client avro2Client) {
        this.avro1Client = avro1Client;
        this.avro2Client = avro2Client;
    }


    @GetMapping("hello")
    public String hello() {
         return avro1Client.execute(new MyAvro1("hello")).value();
    }

    @GetMapping("hello2")
    public Integer hello2() {
        return avro2Client.execute(new MyAvro2(1)).value();
    }
}

