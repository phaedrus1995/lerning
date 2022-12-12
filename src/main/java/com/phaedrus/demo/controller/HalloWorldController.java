package com.phaedrus.demo.controller;

import com.phaedrus.demo.annotation.RuntimeAnnotation;
import com.phaedrus.demo.annotation.RuntimeMethodAnnotation;
import com.phaedrus.demo.entity.StoreResponse;
import com.phaedrus.demo.service.HalloWorldService;
import com.phaedrus.demo.entity.QueryResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class HalloWorldController {

    @Autowired
    private final HalloWorldService halloWorldService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return halloWorldService.sayHallo(name);
    }

    @GetMapping("/query")
    public QueryResponse query() throws NoSuchFieldException, NoSuchMethodException {
        Integer numberOfEmptyLockers = halloWorldService.query();
        QueryResponse queryResponse = new QueryResponse();
        System.out.println("Field Annotation-print!!!!!!!!!!!");
        System.out.println(QueryResponse.class.getField("message").isAnnotationPresent(RuntimeAnnotation.class));
        testMethodAnnotation();
        System.out.println(HalloWorldController.class.getMethod("testMethodAnnotation").isAnnotationPresent(RuntimeMethodAnnotation.class));
        queryResponse.setNumberOfFreeLocker(numberOfEmptyLockers);
        String message = numberOfEmptyLockers > 0 ? "it is free" : "sorry, no free slot";
        queryResponse.setMessage(message);
        return queryResponse;
    }
    @RuntimeMethodAnnotation
    public void testMethodAnnotation() {
        System.out.println("method annotation!!!");
    }

    @PostMapping("/store")
    public StoreResponse store() {
        return halloWorldService.store();
    }

    @PostMapping("/locker/{customerNumber}")
    public StoreResponse getLockerByCustomerNumber(@PathVariable String customerNumber) {
        return halloWorldService.release(customerNumber);
    }
}
