package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private ElementsCollection cards = $$(".list__item div");
  private SelenideElement reloadButton = $("[data-test-id='action-reload']");

  public DashboardPage() {
    heading.shouldBe(visible);
  }

  public int getCardBalance(DataHelper.Card card){
    var text = cards.findBy(Condition.text(card.getNumberCard().substring(16, 19))).getText();
    return extractBalance(text);
  }

  public TransferPage selectCardToTransfer(DataHelper.Card card){
    cards.findBy(attribute("data-test-id", card.getTestId())).$("button").click();
    return new TransferPage();
  }

  private int extractBalance(String text){
    var start = text.indexOf(balanceStart);
    var finish = text.indexOf(balanceFinish);
    var value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }



//  public TransferPage transferFirstCardBalance() {
//    cards.first().$("button").click();
//    return new TransferPage();
//  }
//
//  public TransferPage transferSecondCardBalance() {
//    cards.last().$("button").click();
//    return new TransferPage();
//  }
//
//  public TransferPage upDate() {
//    reloadButton.click();
//    return new TransferPage();
//  }
}