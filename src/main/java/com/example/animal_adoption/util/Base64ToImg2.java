package com.example.animal_adoption.util;


import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.vo.ImgResponse;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;

public class Base64ToImg2 {

  //// 圖片轉換成 Base64 字串
  //public static String getImageStr() {
  //  String imgFile = "D:\\tupian\\a.png"; // 待處理的圖片
  //  try {
  //    byte[] data = Files.readAllBytes(Path.of(imgFile));
  //    Base64.Encoder encoder = Base64.getEncoder();
  //    return encoder.encodeToString(data); // 返回 Base64 編碼過的字节数组字符串
  //  } catch (IOException e) {
  //    e.printStackTrace();
  //    return null;
  //  }
  //}
  //
  //// Base64 字串轉換成圖片
  //public static boolean generateImage(String imgStr) {
  //  if (imgStr == null) {
  //    // 圖像數據為空
  //    return false;
  //  }
  //  Base64.Decoder decoder = Base64.getDecoder();
  //  try {
  //    byte[] b = decoder.decode(imgStr);
  //    Path imgFilePath = Path.of("D:\\Intellij_java\\animal_adoption\\src\\main\\resources\\img\\new.png"); // 新生成的圖片路徑
  //    Files.write(imgFilePath, b, StandardOpenOption.CREATE);
  //    return true;
  //  } catch (IOException e) {
  //    e.printStackTrace();
  //    return false;
  //  }
  //}
  //
  //public static boolean generateImageFromBase64Segments(List<String> base64Segments, String filePath) {
  //  if (base64Segments == null || base64Segments.isEmpty()) {
  //    // 圖像數據為空
  //    return false;
  //  }
  //  Base64.Decoder decoder = Base64.getDecoder();
  //  Path imgFilePath = Path.of(filePath); // 新生成的圖片路徑
  //
  //  try (OutputStream outputStream = Files.newOutputStream(imgFilePath, StandardOpenOption.CREATE)) {
  //    for (String segment : base64Segments) {
  //      byte[] imageData = decoder.decode(segment);
  //      outputStream.write(imageData);
  //    }
  //    return true;
  //  } catch (IOException e) {
  //    e.printStackTrace();
  //    return false;
  //  }
  //}
  //
  //
  //public static List<String> splitString(String input, int splitSize) {
  //  List<String> parts = new ArrayList<>();
  //  int length = input.length();
  //  for (int i = 0; i < length; i += splitSize) {
  //    int end = Math.min(length, i + splitSize);
  //    parts.add(input.substring(i, end));
  //  }
  //  return parts;
  //}
  //
  //public static void test() {
  //  try {
  //    String imageString = "";
  //    Path tempFilePath = Files.createTempFile("temp_image", ".txt");
  //    Files.write(tempFilePath, imageString.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
  //
  //
  //    byte[] imageBytes = {};
  //    try (Base64InputStream base64InputStream = new Base64InputStream(Files.newInputStream(tempFilePath))) {
  //      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  //      byte[] buffer = new byte[1024];
  //      int bytesRead;
  //      while ((bytesRead = base64InputStream.read(buffer)) != -1) {
  //        outputStream.write(buffer, 0, bytesRead);
  //      }
  //      imageBytes = outputStream.toByteArray();
  //    }
  //
  //    try (FileOutputStream fileOutputStream = new FileOutputStream("D:\\Intellij_java\\animal_adoption\\src\\main\\resources\\img\\image.png")) {
  //      fileOutputStream.write(imageBytes);
  //    } catch (IOException e) {
  //      // 處理異常
  //    }
  //
  //    Files.delete(tempFilePath);
  //  } catch (Exception e) {
  //    throw new RuntimeException(e);
  //  }
  //}

  public static ImgResponse base64ToImg(String imgBase64, String sort, int id) throws FileNotFoundException, IOException {

    String filePath = "";
    //  決定儲存位置
    if (sort.equals("a")) {
      filePath += "C:\\IntelliJ IDEA Project\\animal_adoption\\src\\main\\resources\\img\\animal";
    }
    if (sort.equals("s")) {
      filePath += "C:\\IntelliJ IDEA Project\\animal_adoption\\src\\main\\resources\\img\\shop";
    }

    try {
      // 寫入到 txt 檔案
      FileWriter fileWriter = new FileWriter(filePath + "\\base64.txt");
      fileWriter.write(imgBase64);
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 將 txt 檔案讀取並轉換成圖片
    FileInputStream fis = new FileInputStream(filePath + "\\base64.txt");
    String stringTooLong = IOUtils.toString(fis, "UTF-8");
    // 關閉檔案
    fis.close();
    Base64.Decoder decoder = Base64.getDecoder();
    try {

      // 檢查當前資料夾內的照片最後一個編號
      File[] files = new File(filePath).listFiles();
      int maxSerialNumber = Arrays.stream(files)
              .filter(File::isFile)
              .map(File::getName)
              // 圖片檔案名稱為 id - SerialNumber .png
              .filter(name -> name.matches("\\d+-\\d+\\.png"))
              .map(name -> {
                String[] parts = name.replace(".png", "").split("-");
                return Integer.parseInt(parts[1]);
              })
              .max(Comparator.naturalOrder())
              .orElse(0);

      // 生成下一個流水編號
      int nextSerialNumber = maxSerialNumber + 1;

      // 使用生成的流水編號命名當前照片
      String fileName = id + "-" + nextSerialNumber + ".png";

      byte[] b = decoder.decode(stringTooLong);
      Path imgFilePath = Path.of(filePath + "\\" + fileName);
      Files.write(imgFilePath, b, StandardOpenOption.CREATE);

      // 刪除 txt 檔案
      File fileToDelete = new File(filePath + "\\base64.txt");
      if (fileToDelete.delete()) {
        System.out.println("Txt file deleted.");
      }
      else {
        System.out.println("Failed to delete txt file.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ImgResponse(RtnCode.COMPILE_SUCCESS.getMessage());
  }

  // String sort, int id
  public static ImgResponse countImg(String sort, int id) {
    String filePath = "";
    //  決定儲存位置
    if (sort.equals("a")) {
      filePath += "C:\\WebStorm Project\\animal_adoption\\img\\animalAll";
    }
    if (sort.equals("s")) {
      filePath += "C:\\IntelliJ IDEA Project\\animal_adoption\\src\\main\\resources\\img\\shop";
    }

    File[] files = new File(filePath).listFiles();
    int maxSerialNumber = Arrays.stream(files)
            .filter(File::isFile)
            .map(File::getName)
            // 圖片檔案名稱為 id - SerialNumber .png 且以 '1-' 開頭
            .filter(name -> name.matches(id + "-\\d+\\.png"))
            .map(name -> {
              String[] parts = name.replace(".png", "").split("-");
              return Integer.parseInt(parts[1]);
            })
            .max(Comparator.naturalOrder())
            .orElse(0);

    return new ImgResponse(maxSerialNumber, RtnCode.FIND_SUCCESS.getMessage());
  }
}

