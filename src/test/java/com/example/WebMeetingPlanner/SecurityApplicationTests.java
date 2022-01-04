package com.example.WebMeetingPlanner;

import com.example.WebMeetingPlanner.Service.Service;
import com.example.WebMeetingPlanner.controller.SmsRequest;
import com.example.WebMeetingPlanner.controller.TwilioConfiguration;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    SecurityApplicationTests(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }
    @Autowired
    private Service service;

    @Test
    void contextLoads() {
    }

    @Test
    public void smsTest(){
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setPhoneNumber("+254728845679");
        smsRequest.setMessage("Karori uko sawa");
        service.sendSms(smsRequest);
//        String number =
        System.out.println("test finished and casted "+ "+254" + "0713234684");

    }

//    @Test
//    void SmsTest(){
//        SmsRequest newer = new SmsRequest();
//        Twilio.init(twilioConfiguration.getAccountSid(),twilioConfiguration.getAuthToken());
//        newer.setMessage("Mine");
//        String number = newer.setPhoneNumber("+254728845679");
//        com.twilio.type.PhoneNumber to = new com.twilio.type.PhoneNumber(newer.setPhoneNumber(number));
//        com.twilio.type.PhoneNumber from = new com.twilio.type.PhoneNumber(twilioConfiguration.getTrialNumber());
//        String message = newer.setMessage("Dream league is enjoyable");
//
//        MessageCreator messageCreator = Message.creator(to,from,message);
//
//        messageCreator.create();
//        System.out.println("sms sent "+message);
//    }
}
