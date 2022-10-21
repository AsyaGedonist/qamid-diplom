package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.SalesPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }

    @Test
    void happyPathBuy(){
        var salesPage = new SalesPage();
        var approvedCard = DataHelper.getApprovedCard();
        var BuyPage = salesPage.getBuyPage();
        var notification = BuyPage.approvedCard(approvedCard);
        assertEquals("Операция одобрена Банком.", notification);
    }

//    @Test
//    void happyPathCredit(){
//        var salesPage = new SalesPage();
//        var declinedCard = DataHelper.getDeclinedCard();
//        var CreditPage = salesPage.getCreditPage();
//    }
}
