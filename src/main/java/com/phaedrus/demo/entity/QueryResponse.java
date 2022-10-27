package com.phaedrus.demo.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QueryResponse {
    public Integer numberOfFreeLocker;
    public String message;
}
