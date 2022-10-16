package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

  private SelenideElement heading = $("[data-test-id=dashboard]");
  private SelenideElement reloadButton = $("[data-test-id='action-reload']");

  public DashboardPage() {
    heading.shouldBe(visible);
  }

  private ElementsCollection cards = $$(".list__item");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";

  public TransferPage transferFirstCardBalance() {
    cards.first().$("button").click();
    return new TransferPage();
  }

  public TransferPage transferSecondCardBalance() {
    cards.last().$("button").click();
    return new TransferPage();
  }

  public TransferPage upDate() {
    reloadButton.click();
    return new TransferPage();
  }
}