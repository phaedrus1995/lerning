package com.phaedrus.demo;

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
        Boolean status = halloWorldService.query();
        QueryResponse queryResponse = new QueryResponse();
        queryResponse.setStatus(status);
        String message = status ? "it is free" : "sorry, no free slot";
        queryResponse.setMessage(message);
        return queryResponse;
    }
}
