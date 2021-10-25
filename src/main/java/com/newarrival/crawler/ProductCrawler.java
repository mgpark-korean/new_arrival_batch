
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
public abstract class ProductCrawler {

  protected WebDriver webDriver;

  ProductCrawler() {
    System.setProperty("webdriver.chrome.driver",
        "src/main/resources/chromedriver");
    this.webDriver = initWebDriver();
  }

  protected WebDriver initWebDriver() {
    ChromeOptions options = new ChromeOptions();
    options.setHeadless(true);
    return new ChromeDriver(options);
  }

  public abstract ProductDto getProductByIndex(int index) throws IllegalAccessException;

  abstract List<WebElement> getWrapElement();

  abstract String getId(WebElement target);

  abstract String getName(WebElement target);

  abstract String getPrice(WebElement target);

  abstract String getThumbnail(WebElement target);

  abstract String getUrl(WebElement target);
}
