package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "routes")
public class RouteUriConfiguration {
  private String route1;
  private String route2;
  private String defaultRoute;
}
