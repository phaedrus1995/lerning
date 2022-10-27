package com.phaedrus.demo;

import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class HalloWorldRepository {
    String sayHallo(String name) {
        return String.format("Hallo %s!", name);
    }

    void test() {
        /* Online Java Compiler and Editor */
                System.out.println("Hello, World!");
                String[] test = {"walawala", "wuluwulu", "ilijili"};
                String[] nachStream = Arrays.stream(test).map(str -> str.concat("lala")).toArray(String[]::new);
                System.out.println(nachStream[1]);
    }

    Boolean query() {
        return true;
    }
}
