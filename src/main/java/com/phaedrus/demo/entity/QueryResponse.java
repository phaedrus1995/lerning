package com.phaedrus.demo.entity;

import com.phaedrus.demo.annotation.RuntimeAnnotation;
import com.phaedrus.demo.annotation.StaticAnnotation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Data
@Setter
@RequiredArgsConstructor
public class QueryResponse {
    @Id
    public String id;
    @StaticAnnotation(name = "with annotation")
    public Integer numberOfFreeLocker;
    @RuntimeAnnotation(name = "Runtime field annotation appear")
    public String message;
}
