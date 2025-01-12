package com.flightcoordinator.server.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.HttpStatus;

import com.flightcoordinator.server.exception.AppError;

public class AlgorithmServiceEncryptor {
  private static final String SECRET_KEY = "";

  public static String createSignature(String data, String timestamp) {
    try {
      String payload = data + "|" + timestamp;
      Mac mac = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");
      mac.init(secretKeySpec);
      byte[] hmacBytes = mac.doFinal(payload.getBytes());
      return Base64.getEncoder().encodeToString(hmacBytes);
    } catch (IllegalStateException | InvalidKeyException | NoSuchAlgorithmException e) {
      throw new AppError(
          e.getLocalizedMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
  }

  public static String getTimestamp() {
    return String.valueOf(System.currentTimeMillis() / 1000);
  }
}
