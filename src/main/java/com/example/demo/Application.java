package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(RouteUriConfiguration.class)
public class Application {
  @Autowired
  private RouteUriConfiguration routeUriConfiguration;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("path_route1", r -> r.path("/route1").uri(routeUriConfiguration.getRoute1()))
        .route("path_route2", r -> r.path("/route2").uri(routeUriConfiguration.getRoute2()))
        .route("path_route3", r ->
            r.path("/**")
                .filters(f ->
                    f.rewritePath(".*", "/")
                        .redirect(302, routeUriConfiguration.getDefaultRoute()))
                .uri(routeUriConfiguration.getDefaultRoute()))
        .build();
  }
}
