package com.phaedrus.demo.controller;

import com.phaedrus.demo.service.HalloWorldService;
import com.phaedrus.demo.entity.QueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HalloWorldController {

    private final HalloWorldService halloWorldService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return halloWorldService.sayHallo(name);
    }

    @GetMapping("query")
    public QueryResponse query() {
        Integer status = halloWorldService.query();
        QueryResponse queryResponse = new QueryResponse();
        queryResponse.setNumberOfFreeLocker(status);
        String message = status > 0 ? "it is free" : "sorry, no free slot";
        queryResponse.setMessage(message);
        return queryResponse;
    }
}