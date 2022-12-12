package com.phaedrus.demo.service;

import com.phaedrus.demo.repository.HalloWorldRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class WireService {

    private final HalloWorldRepository halloWorldRepository;

    @Bean
    public HalloWorldService getHalloWorldServcie() {
        return new HalloWorldService(halloWorldRepository);
    }
}
