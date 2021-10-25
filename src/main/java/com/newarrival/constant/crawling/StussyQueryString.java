
/*
 * Created by Mingi Park on 2021/10/24
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Dev Backend Team <mingi@bigin.io>, 2021/10/24
 */

package com.newarrival.constant.crawling;

import lombok.Getter;

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
public enum StussyQueryString {
  DOMAIN("https://www.stussy.co.kr/"),
  PRODUCT_NAME("li.collection__product div.product-card a.product-card__title"),
  PRODUCT_PRICE("li.collection__product div.product-card a.product-card__price"),
  PRODUCT_THUMBNAIL("li.collection__product div.product-card a.variant-card img"),
  PRODUCT_SOLDOUT("li.collection__product div.product-card div.product-card__variant-overlay");

  @Getter
  private final String value;

  StussyQueryString(String value) {
    this.value = value;
  }
}
