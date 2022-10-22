package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.SalesPage;

import javax.xml.crypto.Data;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyTest {

    @BeforeEach
    void setup() {
//        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }

    @Test
    void buyHappyPathApproved1(){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var BuyPage = salesPage.getBuyPage();
        var notification = BuyPage.showNotification(approvedCard);
        assertEquals("Операция одобрена Банком.", notification);
    }

    @Test
    void buyHappyPathDeclinedCard2(){
        var salesPage = new SalesPage();
        var declinedCard = DataHelper.getDeclinedCard();
        var BuyPage = salesPage.getBuyPage();
        var notification = BuyPage.showNotification(declinedCard);
        assertEquals("Ошибка! Банк отказал в проведении операции.", notification);
    }

    @Test
    void buyAllEmpty3 (){
        var salesPage = new SalesPage();
        var emptyCard = DataHelper.getEmptyCard();
        var buyPage = salesPage.getBuyPage();
            buyPage.clickGoOn(emptyCard);
        var errors = buyPage.getAllErrors();

        String[] excepted = {"Поле обязательно для заполнения", "Поле обязательно для заполнения",
                "Поле обязательно для заполнения", "Поле обязательно для заполнения", "Поле обязательно для заполнения"};
        assertArrayEquals(excepted, errors);
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
        var cardNumberError = buyPage.getCardNumberError();
        assertEquals(expected, cardNumberError);
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
        var cardNumberValue = buyPage.getValueCardNumber();
        assertEquals(expected, cardNumberValue);
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
        var monthError = buyPage.getMonthError();
        assertEquals(expected, monthError);
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
        var monthValue = buyPage.getValueMonth();
        assertEquals(expected, monthValue);
    }

    @ParameterizedTest
    @CsvSource({
            "12", "01"
    })
    void buyMonthValidation5_78 (String month){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                month, approvedCard.getYear(), approvedCard.getOwner(), approvedCard.getCode());
        var BuyPage = salesPage.getBuyPage();
        var notification = BuyPage.showNotification(newCard);
        assertEquals("Операция одобрена Банком.", notification);
    }

    @ParameterizedTest
    @CsvSource({
            "Поле обязательно для заполнения, ",
            "Неверный формат, 1",
            "Неверно указан срок действия карты, 13",
            "Неверно указан срок действия карты, 00"
    })
    void buyYearValidation6_1234 (String expected, String year){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), year, approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
            buyPage.clickGoOn(newCard);
        var yearError = buyPage.getYearError();
        assertEquals(expected, yearError);
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
        var yearValue = buyPage.getValueYear();
        assertEquals(expected, yearValue);
    }

    @Test
    void buyYearValidation6_78 (){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), "26", approvedCard.getOwner(), approvedCard.getCode());
        var BuyPage = salesPage.getBuyPage();
        var notification = BuyPage.showNotification(newCard);
        assertEquals("Операция одобрена Банком.", notification);
    }
    @Test
    void buyOwnerValidation7_1 (){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), "", approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
        buyPage.clickGoOn(newCard);
        var ownerError = buyPage.getOwnerError();
        assertEquals("Поле обязательно для заполнения", ownerError);
    }
    @ParameterizedTest
    @CsvSource({
            "Anastasiia-.Anastasi, Anastasiia-.Anastasii",
            "Anastasiia, Anastasiiaй",
            "Anastasiia, Anastasiia1",
            "Anastasiia, Anastasiia%",
    })
    void buyOwnerValidation7_23 (String expected, String owner){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), owner, approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
        buyPage.inputData(newCard);
        var ownerValue = buyPage.getValueOwner();
        assertEquals(expected, ownerValue);
    }
    @ParameterizedTest
    @CsvSource({
            "Anastasiia-.Anastas", "Anastasiia-.Anastasi"
    })
    void buyOwnerValidation7_45 (String owner){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var newCard = new DataHelper.CardInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), owner, approvedCard.getCode());
        var BuyPage = salesPage.getBuyPage();
        var notification = BuyPage.showNotification(newCard);
        assertEquals("Операция одобрена Банком.", notification);
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
        var codeError = buyPage.getCodeError();
        assertEquals(expected, codeError);
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
        var codeValue = buyPage.getValueCode();
        assertEquals(expected, codeValue);
    }
}
