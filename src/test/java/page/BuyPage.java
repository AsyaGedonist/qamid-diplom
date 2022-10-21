package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BuyPage {
    private SelenideElement heading = $$("h3.heading").findBy(Condition.text("Оплата по карте"));
    private ElementsCollection input = $$(".input_type_text");
    private SelenideElement cardNumber = input.find(exactText("Номер карты")).$(".input__control");
    private SelenideElement month = input.find(exactText("Месяц")).$(".input__control");
    private SelenideElement year = input.find(exactText("Год")).$(".input__control");
    private SelenideElement owner = input.find(exactText("Владелец")).$(".input__control");
    private SelenideElement code = input.find(exactText("CVC/CVV")).$(".input__control");
    private SelenideElement button = $$(".button span.button__text").find(exactText("Продолжить"));
    private SelenideElement notification = $(".notification__content");
    public BuyPage() {
        heading.shouldBe(visible);
    }

    public void inputData(DataHelper.CardInfo cardInfo){
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getOwner());
        code.setValue(cardInfo.getCode());
        button.click();
    }

    public String approvedCard(DataHelper.CardInfo cardInfo){
        inputData(cardInfo);
        notification.shouldBe(Condition.visible, Duration.ofSeconds(15));
        return notification.text();
    }
}
