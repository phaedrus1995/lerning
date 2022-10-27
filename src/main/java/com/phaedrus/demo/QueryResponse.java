package com.phaedrus.demo;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QueryResponse {
    public Boolean status;
    public String message;
}
