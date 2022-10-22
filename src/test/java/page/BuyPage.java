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
    private ElementsCollection inputTypeText = $$(".input_type_text");
    private SelenideElement cardNumber = inputTypeText.find(exactText("Номер карты")).$(".input__control");
    private SelenideElement month = inputTypeText.find(exactText("Месяц")).$(".input__control");
    private SelenideElement year = inputTypeText.find(exactText("Год")).$(".input__control");
    private SelenideElement owner = inputTypeText.find(exactText("Владелец")).$(".input__control");
    private SelenideElement code = inputTypeText.find(exactText("CVC/CVV")).$(".input__control");
    private SelenideElement button = $$(".button span.button__text").find(exactText("Продолжить"));
    private SelenideElement notification = $(".notification__content");
    private ElementsCollection input = $$(".input");
    private SelenideElement errorCardNumber = input.findBy(text("Номер карты"));
    private SelenideElement errorMonth = input.findBy(text("Месяц"));
    private SelenideElement errorYear = input.findBy(text("Год"));
    private SelenideElement errorOwner = input.findBy(text("Владелец"));
    private SelenideElement errorCode = input.findBy(text("CVC/CVV"));
    public BuyPage() {
        heading.shouldBe(visible);
    }

    public void inputData(DataHelper.CardInfo cardInfo){
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getOwner());
        code.setValue(cardInfo.getCode());
    }

    public void clickGoOn (DataHelper.CardInfo cardInfo){
        inputData(cardInfo);
        button.click();
    }

    public String showNotification(DataHelper.CardInfo cardInfo){
        clickGoOn(cardInfo);
        notification.shouldBe(Condition.visible, Duration.ofSeconds(15));
        return notification.text();
    }

    public String[] getErrors(DataHelper.CardInfo cardInfo){
        clickGoOn(cardInfo);
        String cardNumber = errorCardNumber.$(".input__sub").text();
        String month = errorMonth.$(".input__sub").text();
        String year = errorYear.$(".input__sub").text();
        String owner = errorOwner.$(".input__sub").text();
        String code = errorCode.$(".input__sub").text();
        String [] errors = {cardNumber, month, year, owner, code};
        return errors;
    }
}
