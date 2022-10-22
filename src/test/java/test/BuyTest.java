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
        DataHelper.CardInfo approvedCard = DataHelper.getApprovedCard();
        DataHelper.CardInfo newCard = new DataHelper.CardInfo(cardNumber,
                approvedCard.getMonth(), approvedCard.getYear(), approvedCard.getOwner(), approvedCard.getCode());
        var buyPage = salesPage.getBuyPage();
            buyPage.clickGoOn(newCard);
        var cardNumberError = buyPage.getCardNumberError();
        assertEquals(expected, cardNumberError);
    }
}
