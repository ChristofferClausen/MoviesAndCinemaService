package com.example.moviesincinemaservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@EnableEurekaClient
@RestController
@Slf4j
@RequestMapping("/api/v1/")
public class MoviesAndCinemasController {

    @Autowired
    RestTemplate restTemplate;
    HttpClient client;

    public MoviesAndCinemasController() {

    }

    @RequestMapping("/cinemas")
    public String callCinemas() throws IOException, InterruptedException {
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/v1/cinemas"))
                .build();
        var json = client.send(request, HttpResponse.BodyHandlers.ofString());
        return json.body();
    }

    @RequestMapping("/cinemas/{id}")
    public String callCinema(@PathVariable Long id) throws IOException, InterruptedException {
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/v1/cinemas/" + id))
                .build();
        var json = client.send(request, HttpResponse.BodyHandlers.ofString());
        return json.body();
    }

    @RequestMapping("/movies")
    public String callMovies() throws IOException, InterruptedException {
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8081/api/v1/movies"))
                .build();
        var json = client.send(request, HttpResponse.BodyHandlers.ofString());
        return json.body();
    }

    @RequestMapping("/movies/{id}")
    public String callMovie(@PathVariable Long id) throws IOException, InterruptedException {
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8081/api/v1/movies/" + id))
                .build();
        var json = client.send(request, HttpResponse.BodyHandlers.ofString());
        return json.body();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
