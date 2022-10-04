package ru.example1.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(name = "printer.enabled", havingValue = "true")
@Configuration("ru.example1")
public class PrinterAutoconfiguration {
    @Bean
    public TimedBeanPostProcessor timedBeanPostProcessor() {
        return new TimedBeanPostProcessor();
    }
}

