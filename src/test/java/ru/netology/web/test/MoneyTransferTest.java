package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.DataHelper.*;

class MoneyTransferTest {
    @BeforeEach
    void setup() {
      Configuration.holdBrowserOpen = true;
      open("http://localhost:9999");
    }

  @Test
  void moneyTransferOnTheFirstCard() {

    var authInfo = getAuthInfo();
    var verificationCode = getVerificationCodeFor(authInfo);
    var cardNumber = getSecondCard();

    new LoginPage()
            .validLogin(authInfo)
            .validVerify(verificationCode);

    new ru.netology.page.DashboardPage()
            .transferFirstCardBalance()
            .cardReplenishment(String.valueOf(cardNumber), "1000")
            .upDate();
  }

  @Test
  void moneyTransferOnTheSecondCard() {

    var authInfo = getAuthInfo();
    var verificationCode = getVerificationCodeFor(authInfo);
    var cardNumber = getFirstCard();

    new LoginPage()
            .validLogin(authInfo)
            .validVerify(verificationCode);

    new ru.netology.page.DashboardPage()
            .transferSecondCardBalance()
            .cardReplenishment(String.valueOf(cardNumber), "2000")
            .upDate();
  }

  @Test
  void moneyTransferOverLimit() {

    var authInfo = getAuthInfo();
    var verificationCode = getVerificationCodeFor(authInfo);
    var cardNumber = getSecondCard();

    new LoginPage()
            .validLogin(authInfo)
            .validVerify(verificationCode);

    new ru.netology.page.DashboardPage()
            .transferFirstCardBalance()
            .cardReplenishment(String.valueOf(cardNumber), "23000")
            .upDate();
  }
}

