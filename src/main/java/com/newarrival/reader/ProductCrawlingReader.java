
/*
 * Created by Mingi Park on 2021/10/24
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Dev Backend Team <mingi@bigin.io>, 2021/10/24
 */

package com.newarrival.reader;

import com.newarrival.crawler.ProductCrawler;
import com.newarrival.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;

/**
 * create on 2021/10/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Mingi Park
 * @version 1.0
 * @see
 * @since 1.0
 */
@Slf4j
public class ProductCrawlingReader<T extends ProductDto> implements ItemReader<ProductDto> {

  private int cursorIdx;
  private final ProductCrawler crawler;

  public ProductCrawlingReader(ProductCrawler crawler) {
    this.cursorIdx = 0;
    this.crawler = crawler;
  }

  @Override
  public ProductDto read() throws IllegalAccessException {
    return crawler.getProductByIndex(cursorIdx++);
  }
}
