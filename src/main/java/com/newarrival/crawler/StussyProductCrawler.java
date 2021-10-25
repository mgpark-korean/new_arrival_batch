
/*
 * Created by Mingi Park on 2021/10/25
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Dev Backend Team <mingi@bigin.io>, 2021/10/25
 */

package com.newarrival.crawler;

import com.newarrival.dto.ProductDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.util.CollectionUtils;

/**
 * create on 2021/10/25. create by IntelliJ IDEA.
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
public class StussyProductCrawler extends ProductCrawler {

  private String wrapElementSelector = ".collection__products > .collection__product";
  private String nameAnchorSelector = "div.product-card a.product-card__title";
  private String priceSelector = ".product-card__price";
  private String thumbnailSelector = "div.product-card a.variant-card img";

  private List<WebElement> wrapElements;

  public StussyProductCrawler() {
    super();
    webDriver.get("https://www.stussy.co.kr/collections/new-arrivals");
    this.wrapElements = getWrapElement();
  }

  @Override
  List<WebElement> getWrapElement() {
    return webDriver.findElements(By.cssSelector(wrapElementSelector));
  }

  @Override
  public ProductDto getProductByIndex(int index) throws IllegalAccessException {
    if(CollectionUtils.isEmpty(wrapElements)) {
      throw new IllegalAccessException("warp element is empty!!");
    }

    if(wrapElements.size() <= index) {
      return null;
    }

    WebElement wrapElement = wrapElements.get(index);
    ProductDto product =  new ProductDto(
        getId(wrapElement),
        getName(wrapElement),
        getPrice(wrapElement),
        getUrl(wrapElement),
        getThumbnail(wrapElement)
    );

    log.info("crawling new arrival cursor {} / {}", index+1,wrapElements.size());
    return product;
  }

  @Override
  String getId(WebElement target) {
    WebElement nameAnchorTag = target.findElement(By.cssSelector(nameAnchorSelector));
    String hrefLink = nameAnchorTag.getAttribute("href");
    final String[] splitList = hrefLink.split("/?variant=");
    return splitList[1];
  }

  @Override
  String getName(WebElement target) {
    WebElement nameAnchorTag = target.findElement(By.cssSelector(nameAnchorSelector));
    return nameAnchorTag.getText().trim();
  }

  @Override
  String getPrice(WebElement target) {
    WebElement priceElement = target.findElement(By.cssSelector(priceSelector));
    return priceElement.getText().trim();
  }

  @Override
  String getThumbnail(WebElement target) {
    WebElement imageElement = target.findElement(By.cssSelector(thumbnailSelector));
    return imageElement.getAttribute("src");
  }

  @Override
  String getUrl(WebElement target) {
    WebElement nameAnchorTag = target.findElement(By.cssSelector(nameAnchorSelector));
    return nameAnchorTag.getAttribute("href");
  }
}
