package com.finicity.token.tokenservice.api;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NgramServiceImpl implements NgramService {

  HashMap<String, Integer> frequencyMap;

  public NgramServiceImpl() {
    this.frequencyMap = new HashMap<>();
  }

  @Override
  public List<String> uploadAndGetTokenNGrams(String text, boolean isWrite) {
    List<String> list = new ArrayList<>();
    String[] words = text.split(" ");
    if (words.length < 2) {
      log.info("text is not large enough to parse and upload");
      return list;
    }
    for (int i = 1; i < words.length; i++) {
      StringBuilder sb = new StringBuilder();
      sb.append(words[i - 1]).append(" ").append(words[i]);
      String ngramToken = sb.toString();
      if (isWrite) {
        frequencyMap.put(ngramToken, frequencyMap.getOrDefault(ngramToken, 0) + 1);
      }
      list.add(ngramToken);
    }
    log.info("frequency of tokens {}", frequencyMap);
    log.info("list of tokens {}", list);
    return list;
  }

  public HashMap<String, Integer> getFrequencyCount(String str) {
    List<String> tokenNGrams = uploadAndGetTokenNGrams(str, false);
    HashMap<String, Integer> resultMap = new HashMap();
    for (String s : tokenNGrams) {
      if (frequencyMap.containsKey(s)) {
        resultMap.put(s, frequencyMap.get(s));
      }
    }
    return resultMap;
  }
}
