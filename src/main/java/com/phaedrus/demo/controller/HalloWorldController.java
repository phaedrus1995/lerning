package com.phaedrus.demo.controller;

import com.phaedrus.demo.entity.StoreResponse;
import com.phaedrus.demo.service.HalloWorldService;
import com.phaedrus.demo.entity.QueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HalloWorldController {

    private final HalloWorldService halloWorldService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return halloWorldService.sayHallo(name);
    }

    @GetMapping("/query")
    public QueryResponse query() {
        Integer numberOfEmptyLockers = halloWorldService.query();
        QueryResponse queryResponse = new QueryResponse();
        queryResponse.setNumberOfFreeLocker(numberOfEmptyLockers);
        String message = numberOfEmptyLockers > 0 ? "it is free" : "sorry, no free slot";
        queryResponse.setMessage(message);
        return queryResponse;
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
