package com.example.rabbitmq.controller;

import com.example.rabbitmq.model.Employee;
import com.example.rabbitmq.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQWebController {

    @Value("${javainuse.rabbitmq.queue}")
    String queueName;

    @Autowired
    RabbitMQSender rabbitMQSender;

    @GetMapping(value = "/test")
    public String test() {
        return "Appliction working";
    }

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {
        System.out.println("name is " + queueName);
        Employee emp = new Employee();
        emp.setEmpId(empId);
        emp.setEmpName(empName);
        rabbitMQSender.send(emp);
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}