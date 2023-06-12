package com.example.animal_adoption.util;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Base64ToImg2 {

    // 圖片轉換成 Base64 字串
    public static String getImageStr() {
      String imgFile = "D:\\tupian\\a.jpg"; // 待處理的圖片
      try {
        byte[] data = Files.readAllBytes(Path.of(imgFile));
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data); // 返回 Base64 編碼過的字节数组字符串
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }

    // Base64 字串轉換成圖片
    /*public static boolean generateImage(String imgStr) {
      if (imgStr == null) {
        // 圖像數據為空
        return false;
      }
      Base64.Decoder decoder = Base64.getDecoder();
      try {
        byte[] b = decoder.decode(imgStr);
        Path imgFilePath = Path.of("D:\\Intellij_java\\animal_adoption\\src\\main\\resources\\img\\new.jpg"); // 新生成的圖片路徑
        Files.write(imgFilePath, b, StandardOpenOption.CREATE);
        return true;
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }*/

  public static boolean generateImageFromBase64Segments(List<String> base64Segments, String filePath) {
    if (base64Segments == null || base64Segments.isEmpty()) {
      // 圖像數據為空
      return false;
    }
    Base64.Decoder decoder = Base64.getDecoder();
    Path imgFilePath = Path.of(filePath); // 新生成的圖片路徑

    try (OutputStream outputStream = Files.newOutputStream(imgFilePath, StandardOpenOption.CREATE)) {
      for (String segment : base64Segments) {
        byte[] imageData = decoder.decode(segment);
        outputStream.write(imageData);
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }



  public static List<String> splitString(String input, int splitSize) {
    List<String> parts = new ArrayList<>();
    int length = input.length();
    for (int i = 0; i < length; i += splitSize) {
      int end = Math.min(length, i + splitSize);
      parts.add(input.substring(i, end));
    }
    return parts;
  }

}

