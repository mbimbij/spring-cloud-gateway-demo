package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("path_route1", r -> r.path("/get").uri("http://httpbin.org"))
        .route("path_route2", r -> r.path("/toto").uri("http://sherdog.com"))
        .route("path_route3", r ->
            r.path("/**")
                .filters(f ->
                    f.rewritePath(".*", "/")
                        .redirect(302, "https://mvnrepository.com/"))
                .uri("https://mvnrepository.com/"))
        .build();
  }
}
