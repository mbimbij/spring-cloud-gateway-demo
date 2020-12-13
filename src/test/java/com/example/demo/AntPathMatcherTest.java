package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AntPathMatcherTest {

  private final AntPathMatcher matcher = new AntPathMatcher();
  private final String pattern = "/api/balance/**";

  @Test
  public void twoAsterisks() {
    assertTrue(matching("/api/balance"));
    assertTrue(matching("/api/balance/"));
    assertTrue(matching("/api/balance/abc"));
    assertTrue(matching("/api/balance/abc/"));
    assertTrue(matching("/api/balance/abc/update"));
    assertFalse(matching("/api/bala"));
  }

  @Test
  public void twoAsterisks2() {
    assertTrue(matching("/toto/**","/toto/poihd/poiuhygf"));
  }

  private boolean matching(String pattern, String path) {
    return matcher.match(pattern, path);
  }

  private boolean matching(String path) {
    return matcher.match(pattern, path);
  }

//  @Test
//  fun oneAsterisk() {
//    var pattern = "/api/balance/*"
//
//    var matcher = AntPathMatcher()
//    var matching = { path: String -> matcher.match(pattern, path) }
//
//    assertTrue(matching("/api/balance/"))
//    assertTrue(matching("/api/balance/abc"))
//
//    assertFalse(matching("/api/bala"))
//    assertFalse(matching("/api/balance"))
//    assertFalse(matching("/api/balance/abc/"))
//    assertFalse(matching("/api/balance/abc/update"))
//  }
}
