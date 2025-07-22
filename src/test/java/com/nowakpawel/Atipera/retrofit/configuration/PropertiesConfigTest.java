package com.nowakpawel.Atipera.retrofit.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PropertiesConfigTest {
    @Autowired
    private PropertiesConfig config;

    @Test
    void propertiesAreLoaded() {
        assertNotNull(config.getUrl());
    }
}