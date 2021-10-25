
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
public class StussyProductCrawler extends ProductCrawler {

  private String domain = "https://www.stussy.co.kr";
  private String nameAnchorSelector = "div.product-card a.product-card__title";
  private String priceSelector = "div.product-card a.product-card__price";
  private String thumbnailSelector = "div.product-card a.variant-card img";
  private String soldOutSelector = "div.product-card div.product-card__variant-overlay";

  private List<WebElement> wrapElements;

  public StussyProductCrawler() {
    String connectUrl = "https://www.stussy.co.kr/collections/new-arrivals";
    this.webDriver = super.getChromeDriver(connectUrl);
    this.wrapElements = getWrapElement();
  }

  @Override
  List<WebElement> getWrapElement() {
    return webDriver.findElements(By.cssSelector(".collection__products > .collection__product"));
  }

  @Override
  public ProductDto getProductByIndex(int index) throws IllegalAccessException {
    if(CollectionUtils.isEmpty(wrapElements)) {
      throw new IllegalAccessException("warp element is empty!!");
    }

    WebElement wrapElement = wrapElements.get(index);
    return new ProductDto(
        getId(wrapElement),
        getName(wrapElement),
        getPrice(wrapElement),
        getUrl(wrapElement),
        getThumbnail(wrapElement),
        getSoldOut(wrapElement)
    );
  }

  @Override
  String getId(WebElement target) {
    WebElement nameAnchorTag = target.findElement(By.cssSelector(nameAnchorSelector));
    String hrefLink = nameAnchorTag.getAttribute("href");
    final String[] splitList = hrefLink.split("/?variant=");
    return splitList[0];
  }

  @Override
  String getName(WebElement target) {
    WebElement nameAnchorTag = target.findElement(By.cssSelector(nameAnchorSelector));
    return nameAnchorTag.getText();
  }

  @Override
  String getPrice(WebElement target) {
    WebElement nameAnchorTag = target.findElement(By.cssSelector(priceSelector));
    return nameAnchorTag.getText();
  }

  @Override
  String getThumbnail(WebElement target) {
    WebElement nameAnchorTag = target.findElement(By.cssSelector(thumbnailSelector));
    return nameAnchorTag.getAttribute("src");
  }

  @Override
  String getUrl(WebElement target) {
    WebElement nameAnchorTag = target.findElement(By.cssSelector(nameAnchorSelector));
    String hrefLink = nameAnchorTag.getAttribute("href");
    return domain + hrefLink;
  }

  @Override
  boolean getSoldOut(WebElement target) {
    return target.findElement(By.cssSelector(soldOutSelector)).isEnabled();
  }
}
