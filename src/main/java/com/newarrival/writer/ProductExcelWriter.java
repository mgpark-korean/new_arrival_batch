/*
 * Created by Hochan Son on 2021/10/25
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Dev Backend Team <hochan@bigin.io>, 2021/10/25
 */

package com.newarrival.writer;

import com.newarrival.dto.ProductDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * create on 2021/10/25.
 * create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Hochan Son
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Component
public class ProductExcelWriter implements ItemWriter<ProductDto> {
  @Override
  public void write(List<? extends ProductDto> items) throws Exception {
    Workbook workbook = new HSSFWorkbook();

    var sheet = workbook.createSheet();

    String[] header = new String[] {
            "id", "name", "price", "url", "thumbnail"
    };
    var firstRow = sheet.createRow(0);
    for (int i = 0; i < header.length; i++) {
      var cell = firstRow.createCell(i);
      cell.setCellValue(header[i]);
    }

    int rownum = 1;
    for (ProductDto dto : items) {
      var row = sheet.createRow(rownum++);
      var cell = row.createCell(0);
      cell.setCellValue(dto.getId());
      cell = row.createCell(1);
      cell.setCellValue(dto.getName());
      cell = row.createCell(2);
      cell.setCellValue(dto.getPrice());
      cell = row.createCell(3);
      cell.setCellValue(dto.getUrl());
      cell = row.createCell(4);
      cell.setCellValue(dto.getThumbnail());
    }

    var file = new File("excel");

    if (!file.mkdirs()) {
      file.mkdirs();
    }
    FileOutputStream stream = new FileOutputStream("excel/product.xlsx");

    workbook.write(stream);
  }

}
