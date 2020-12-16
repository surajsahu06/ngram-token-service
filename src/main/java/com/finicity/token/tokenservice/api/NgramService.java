package com.finicity.token.tokenservice.api;

import java.util.HashMap;
import java.util.List;

public interface NgramService {
  /**
   * This method will upload the tokenize string
   *
   * @param text    input text
   * @param isWrite true if we are uploading; false when reading
   * @return
   */

  List<String> uploadAndGetTokenNGrams(String text, boolean isWrite);

  HashMap<String, Integer> getFrequencyCount(String text);
}
