package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

class MoneyTransferTest {


  @Test
  void shouldTransferFromFirstToSecond() {
    var loginPage = open("http://localhost:9999", LoginPage.class);
    var authInfo = getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var firstCard = getFirstCard();
    var secondCard = getSecondCard();
    var firstCardBalance = dashboardPage.getCardBalance(firstCard);
    var secondCardBalance = dashboardPage.getCardBalance(secondCard);
    var amount = generateValidAmount(firstCardBalance);
    var expectedBalanceFirstCard = firstCardBalance - amount;
    var expectedBalanceSecondCard = secondCardBalance + amount;
    var transferPage = dashboardPage.selectCardToTransfer(secondCard);
    dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCard);
    var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
    var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCard);
    assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
    assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
  }

  @Test
  void shouldTransferFromSecondToFirst() {
    var loginPage = open("http://localhost:9999", LoginPage.class);
    var authInfo = getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var firstCard = getFirstCard();
    var secondCard = getSecondCard();
    var firstCardBalance = dashboardPage.getCardBalance(firstCard);
    var secondCardBalance = dashboardPage.getCardBalance(secondCard);
    var amount = generateValidAmount(firstCardBalance);
    var expectedBalanceFirstCard = firstCardBalance + amount;
    var expectedBalanceSecondCard = secondCardBalance - amount;
    var transferPage = dashboardPage.selectCardToTransfer(firstCard);
    dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), secondCard);
    var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
    var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCard);
    assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
    assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
  }

  @Test
  void shouldTransferFromFirstToSecondOverLimit() {
    var loginPage = open("http://localhost:9999", LoginPage.class);
    var authInfo = getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var firstCard = getFirstCard();
    var secondCard = getSecondCard();
    var firstCardBalance = dashboardPage.getCardBalance(firstCard);
    var secondCardBalance = dashboardPage.getCardBalance(secondCard);
    var amount = generateInvalidAmount(firstCardBalance);
    var transferPage = dashboardPage.selectCardToTransfer(secondCard);
    transferPage.makeTransfer(String.valueOf(amount), secondCard);
    transferPage.findErrorMessage("Выполнена попытка перевода суммы, превышающей лимит");
    var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
    var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCard);
    assertEquals(firstCardBalance, actualBalanceFirstCard);
    assertEquals(secondCardBalance, actualBalanceSecondCard);
  }


}
