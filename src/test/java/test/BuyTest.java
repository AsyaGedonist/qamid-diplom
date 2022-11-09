package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataBaseHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.BuyPage;
import page.SalesPage;

import javax.xml.crypto.Data;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    void setup() {
//        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }

    @AfterEach
    void cleanAll(){
        DataBaseHelper.cleanAll();
    }

    @Test
    void buyHappyPathApproved1(){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var buyPage = salesPage.getBuyPage();
        buyPage.showNotification(approvedCard, "Операция одобрена Банком.");
        assertEquals(DataBaseHelper.getStatus(), "APPROVED");
    }

    @Test
    void buyHappyPathDeclinedCard2(){
        var salesPage = new SalesPage();
        var declinedCard = DataHelper.getDeclinedCard();
        var buyPage = salesPage.getBuyPage();
        buyPage.showNotification(declinedCard, "Ошибка! Банк отказал в проведении операции.");
        assertEquals(DataBaseHelper.getStatus(), "DECLINED");
    }

    @Test
    void buyAllEmpty3 (){
        var salesPage = new SalesPage();
        var emptyCard = DataHelper.getEmptyCard();
        var buyPage = salesPage.getBuyPage();
            buyPage.clickGoOn(emptyCard);
        buyPage.getCardNumberError("Поле обязательно для заполнения");
        buyPage.getMonthError("Поле обязательно для заполнения");
        buyPage.getYearError("Поле обязательно для заполнения");
        buyPage.getOwnerError("Поле обязательно для заполнения");
        buyPage.getCodeError("Поле обязательно для заполнения");
    }

    @ParameterizedTest
    @CsvSource({
            "Поле обязательно для заполнения, ",
            "Неверный формат, 1234 1234 1234 1234",
            "Неверный формат, 1111 2222 3333 444"
    })
    void buyCardValidation4_123 (String expected, String cardNumber){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(cardNumber,
                approvedCard.getMonth(), approvedCard.getYear(), approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
            buyPage.clickGoOn(newCard);
        buyPage.getCardNumberError(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "1111 2222 3333 4444, 1111 2222 3333 4444 5",
            "1111 2, 1111 2RgДа%."
    })
    void buyCardValidation4_56 (String expected, String cardNumber){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(cardNumber,
                approvedCard.getMonth(), approvedCard.getYear(), approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();

        buyPage.inputData(newCard);
        buyPage.checkValueCardNumber(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "Поле обязательно для заполнения, ",
            "Неверный формат, 1",
            "Неверно указан срок действия карты, 13",
            "Неверно указан срок действия карты, 00"
    })
    void buyMonthValidation5_1234 (String expected, String month){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                month, approvedCard.getYear(), approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
            buyPage.clickGoOn(newCard);
        buyPage.getMonthError(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "12, 123",
            "1, 1q",
            "1, 1а",
            "1, 1%",
    })
    void buyMonthValidation5_56 (String expected, String month){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                month, approvedCard.getYear(), approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();

        buyPage.inputData(newCard);
        buyPage.checkValueMonth(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "12", "01"
    })
    void buyMonthValidation5_78 (String month){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var year = DataHelper.generateMonthYear(12, "yy");
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                month, year, approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
        buyPage.showNotification(newCard, "Операция одобрена Банком.");
        assertEquals(DataBaseHelper.getStatus(), "APPROVED");
    }

    @ParameterizedTest
    @CsvSource({
            "Поле обязательно для заполнения, ",
            "Неверный формат, 1"
    })
    void buyYearValidation6_12 (String expected, String year){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), year, approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
            buyPage.clickGoOn(newCard);
        buyPage.getYearError(expected);
    }

    @Test
    void buyYearValidation6_3 (){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var month = DataHelper.generateMonthYear(59, "MM");
        var year = DataHelper.generateMonthYear(59, "yy");
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                month, year, approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
        buyPage.showNotification(newCard, "Операция одобрена Банком.");
        assertEquals(DataBaseHelper.getStatus(), "APPROVED");
    }

    @Test
    void buyYearValidation6_4 (){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var month = DataHelper.generateMonthYear(61, "MM");
        var year = DataHelper.generateMonthYear(61, "yy");
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                month, year, approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
        buyPage.clickGoOn(newCard);
        buyPage.getYearError("Неверно указан срок действия карты");
    }

    @ParameterizedTest
    @CsvSource({
            "27, 278",
            "2, 2q",
            "2, 2а",
            "2, 2%",
    })
    void buyYearValidation6_56 (String expected, String year){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), year, approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();

        buyPage.inputData(newCard);
        buyPage.checkValueYear(expected);
    }

    @Test
    void buyYearValidation6_78 (){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), "26", approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
        buyPage.showNotification(newCard, "Операция одобрена Банком.");
        assertEquals(DataBaseHelper.getStatus(), "APPROVED");
    }

    @Test
    void buyOwnerValidation7_1 (){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), "", approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
        buyPage.clickGoOn(newCard);
        buyPage.getOwnerError("Поле обязательно для заполнения");
    }

    @ParameterizedTest
    @CsvSource({
            "Anastasiia, Anastasiia1",
            "Anastasiia, Anastasiia%",
    })
    void buyOwnerValidation7_2 (String expected, String owner){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), owner, approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();

        buyPage.inputData(newCard);
        buyPage.checkValueOwner(expected);
    }

    @Test
    void buyOwnerValidation7_3 (){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var owner = DataHelper.generateName("en", 19);
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), owner, approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
        buyPage.showNotification(newCard, "Операция одобрена Банком.");
        assertEquals(DataBaseHelper.getStatus(), "APPROVED");
    }

    @Test
    void buyOwnerValidation7_4 (){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var owner = DataHelper.generateName("en", 21);
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), owner, approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
        buyPage.inputData(newCard);

        var expected = owner.length() < 20 ? owner : owner.substring(0, 20);
        buyPage.checkValueOwner(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "Поле обязательно для заполнения, ",
            "Неверный формат, 12"
    })
    void buyCodeValidation8_12 (String expected, String code){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), approvedCard.getOwner(), code);
        var buyPage = salesPage.getBuyPage();
        buyPage.clickGoOn(newCard);
        buyPage.getCodeError(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "123, 1234",
            "1, 1q",
            "1, 1й",
            "1, 1%"
    })
    void buyCodeValidation8_34 (String expected, String code){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), approvedCard.getOwner(), code);
        var buyPage = salesPage.getBuyPage();

        buyPage.inputData(newCard);
        buyPage.checkValueCode(expected);
    }
}
