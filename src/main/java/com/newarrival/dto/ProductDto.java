
/*
 * Created by Mingi Park on 2021/10/24
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Dev Backend Team <mingi@bigin.io>, 2021/10/24
 */

package com.newarrival.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * create on 2021/10/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Mingi Park
 * @version 1.0
 * @since 1.0
 */
@Getter
@ToString
@Entity(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDto {

  public ProductDto(
      String id,
      String name,
      String price,
      String url,
      String thumbnail,
      boolean soldout) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.url = url;
    this.thumbnail = thumbnail;
    this.soldout = soldout;
  }

  @Id
  private String id;
  private String name;
  private String price;
  private String url;
  private String thumbnail;
  private boolean soldout;
}
