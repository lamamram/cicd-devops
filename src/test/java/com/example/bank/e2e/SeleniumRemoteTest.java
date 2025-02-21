package com.example.bank.e2e;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import java.net.URL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SeleniumRemoteTest {
  // pour manipuler un navigateur personnel OU un serveur Selenium
  private WebDriver driver;

  @BeforeEach
  public void connect() {
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("-headless");
    try {
      // selenium: port par défaut 4444 de type standalone => /wd/hub
      // selenium: le nom de domaine vient du fichier .gitlab-ci.yml
      driver = new RemoteWebDriver(new URL("http://selenium-server:4444/wd/hub"), options);
    } catch (Exception e) {
      System.exit(1);
    }
  }

  // Cleanup
  @AfterEach
  public void close() {
    driver.close();
  }

  @Tag("E2E")
  @Test
  public void testDawanHomePage() {
    driver.get("https://dawan.fr");
    assertThat(driver.getTitle()).contains("Dawan");
  }
}
