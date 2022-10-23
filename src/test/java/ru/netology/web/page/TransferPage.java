package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement transfer = $("[data-test-id='action-transfer']");
    private SelenideElement heading = $(byText("Пополнение карты"));
    private SelenideElement error = $("[data-test-id='error-message']");

    public TransferPage() {
        heading.shouldBe(Condition.visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.Card card){
        makeTransfer(amountToTransfer, card);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.Card card){
        amount.setValue(amountToTransfer);
        from.setValue(card.getNumberCard());
        transfer.click();
    }

    public void  findErrorMessage(String expectedText){
        error.shouldHave(Condition.exactText(expectedText), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}
