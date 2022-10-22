package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.SalesPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
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
        var errors = buyPage.getErrors(emptyCard);

        String[] excepted = {"Поле обязательно для заполнения", "Поле обязательно для заполнения",
                "Поле обязательно для заполнения", "Поле обязательно для заполнения", "Поле обязательно для заполнения"};
        assertArrayEquals(excepted, errors);
    }
}
