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
    private SelenideElement cardNumberField = inputTypeText.find(exactText("Номер карты")).$(".input__control");
    private SelenideElement monthField = inputTypeText.find(exactText("Месяц")).$(".input__control");
    private SelenideElement yearField = inputTypeText.find(exactText("Год")).$(".input__control");
    private SelenideElement ownerField = inputTypeText.find(exactText("Владелец")).$(".input__control");
    private SelenideElement codeField = inputTypeText.find(exactText("CVC/CVV")).$(".input__control");
    private SelenideElement buttonGoOn = $$(".button span.button__text").find(exactText("Продолжить"));
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
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        ownerField.setValue(cardInfo.getOwner());
        codeField.setValue(cardInfo.getCode());
    }

    public void clickGoOn (DataHelper.CardInfo cardInfo){
        inputData(cardInfo);
        buttonGoOn.click();
    }

    public void showNotification (DataHelper.CardInfo cardInfo, String expected){
        clickGoOn(cardInfo);
        notification.shouldHave(text(expected));
        notification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void getCardNumberError (String expected){
        errorCardNumber.$(".input__sub").shouldHave(text(expected));
    }

    public void getMonthError (String expected){
        errorMonth.$(".input__sub").shouldHave(text(expected));
    }

    public void getYearError (String expected){
        errorYear.$(".input__sub").shouldHave(text(expected));
    }

    public void getOwnerError (String expected){
        errorOwner.$(".input__sub").shouldHave(text(expected));
    }

    public void getCodeError (String expected){
        errorCode.$(".input__sub").shouldHave(text(expected));
    }

    public String getValueCardNumber(){
        String result = cardNumberField.getValue();
        return result;
    }

    public String getValueMonth(){
        String result = monthField.getValue();
        return result;
    }

    public String getValueYear(){
        String result = yearField.getValue();
        return result;
    }

    public String getValueOwner(){
        String result = ownerField.getValue();
        return result;
    }

    public String getValueCode(){
        String result = codeField.getValue();
        return result;
    }
}
