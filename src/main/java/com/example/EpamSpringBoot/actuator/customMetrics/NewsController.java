package com.example.EpamSpringBoot.actuator.customMetrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/actuator")
public class NewsController {

    private Counter counter;

    public NewsController(MeterRegistry registry) {
         this.counter = Counter.builder("news_fetch_request_total").
                tag("version", "v1").
                description("News Fetch Count").
                register(registry);
    }

    @GetMapping("/news")
    public List getNews() {
        counter.increment();
        return List.of(new String("Good News!"), new String("Bad News!"));
    }

}