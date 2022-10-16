package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement sum = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement transfer = $("[data-test-id='action-transfer']");
    private SelenideElement heading = $("[data-test-id='dashboard']");

    public TransferPage() {
        heading.shouldBe(Condition.visible);
    }

    public ru.netology.page.DashboardPage cardReplenishment(String card, String amount) {
        sum.setValue(amount);
        from.setValue(card);
        transfer.click();
        return new ru.netology.page.DashboardPage();
    }
}
