package ru.example.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(name = "printer.enabled", havingValue = "true")
@EnableConfigurationProperties(PrinterProperties.class)
@Configuration
public class PrinterAutoconfiguration {
    PrinterProperties printerProperties;

    @Autowired
    void setMetricProperties(PrinterProperties printerProperties) {
        this.printerProperties = printerProperties;
    }

    @Bean
    TimedBeanPostProcessor timedBeanPostProcessor() {
        return new TimedBeanPostProcessor();
    }
}

