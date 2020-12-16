package com.finicity.token.tokenservice.rest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finicity.token.tokenservice.api.NgramServiceImpl;

@Slf4j
@RestController
public class NgramController {

  private NgramServiceImpl tokenService;

  public NgramController(NgramServiceImpl tokenService) {
    this.tokenService = tokenService;
  }

  @PostMapping(value = "/upload")
  public ResponseEntity tokenize(@RequestParam("text") String text) {
    tokenService.uploadAndGetTokenNGrams(text, true);
    log.info("upload successful for the input text = {}", text);
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/frequency")
  public ResponseEntity getFrequencyCount(@RequestParam("text") String text) {
    return ResponseEntity.ok(tokenService.getFrequencyCount(text));
  }
}
