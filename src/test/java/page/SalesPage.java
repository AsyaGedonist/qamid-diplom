package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class SalesPage {
    private ElementsCollection buttons = $$(".button span.button__text");
    private SelenideElement buyButton = buttons.findBy(Condition.text("Купить"));
    private SelenideElement creditButton = buttons.findBy(Condition.text("Купить в кредит"));

    public BuyPage getBuyPage(){
        buyButton.click();
        return new BuyPage();
    }
}
