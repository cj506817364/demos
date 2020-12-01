package com.ppi.dt.common;

import com.duitang.saturn.server.common.excption.CipherException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author huahua
 * @date create on 2019/8/6
 */
@Slf4j
public class CipherUtil {

  private static final String AES_KEY = "uIoaECscsnZzXEn9";
  private static final SecretKeySpec AES_KEY_SPEC = new SecretKeySpec(getUTF8Bytes(AES_KEY), "AES");

  private static final String EMPTY_STRING = "";

  public static String defaultEncrypt(String str) {
    return aesEncrypt(str);
  }

  public static String defaultDecrypt(String str) {
    return aesDecrypt(str);
  }


  public static String aesEncrypt(String str) {
    if (null == str){
      return null;
    }
    if (Objects.equals(EMPTY_STRING, str)) {
      return EMPTY_STRING;
    }
    try {
      /*Cipher 是非线程安全的*/
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, AES_KEY_SPEC);
      byte[] result = cipher.doFinal(str.getBytes());
      return Base64.encodeBase64String(result);
    } catch (Exception e) {
      log.error("CipherUtil.aesEncrypt error str:{}", str, e);
      throw new CipherException(str);
    }
  }

  public static String aesDecrypt(String str) {
    if (null == str){
      return null;
    }
    if (Objects.equals(EMPTY_STRING, str)) {
      return EMPTY_STRING;
    }
    try {
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, AES_KEY_SPEC);
      byte[] original = cipher.doFinal(Base64.decodeBase64(str));
      return new String(original, StandardCharsets.UTF_8);
    } catch (Exception e) {
      log.error("CipherUtil.aesDecrypt error str:{}", str, e);
      throw new CipherException(str);
    }
  }


  private static byte[] getUTF8Bytes(String input) {
    return input.getBytes(StandardCharsets.UTF_8);
  }

}
