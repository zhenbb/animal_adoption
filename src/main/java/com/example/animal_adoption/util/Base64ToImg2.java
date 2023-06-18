package com.example.animal_adoption.util;


import com.example.animal_adoption.constants.RtnCode;
import com.example.animal_adoption.vo.ImgResponse;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;

public class Base64ToImg2 {

  static final String animalFilePath = "C:\\WebStorm Project\\rocket_vue\\public\\img\\animal";
  static final String productFilePath = "C:\\WebStorm Project\\rocket_vue\\public\\img\\productWall_img";

  // Base64轉圖片功能
  public static ImgResponse base64ToImg(String imgBase64, String sort, int id) throws IOException {

    String filePath = "";
    //  決定儲存位置
    if (sort.equals("a")) {
      filePath += animalFilePath;
    }
    if (sort.equals("s")) {
      filePath += productFilePath;
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
    String stringTooLong = IOUtils.toString(fis, StandardCharsets.UTF_8);
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
              .filter(name -> name.matches(id + "-\\d+\\.png"))
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


  // 數檔案數量功能
  public static ImgResponse countImg(String sort, int id) {
    String filePath = "";
    //  決定儲存位置
    if (sort.equals("a")) {
      filePath += animalFilePath;
    }
    if (sort.equals("s")) {
      filePath += productFilePath;
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

