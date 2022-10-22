package data;


import lombok.Value;
import org.junit.jupiter.api.BeforeEach;

public class DataHelper {
    private DataHelper(){}
    public static CardInfo getApprovedCard() {
        return new CardInfo("1111 2222 3333 4444", "11", "27", "Anastasiia", "123");
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("5555 6666 7777 8888", "11", "27", "Anastasiia", "123");
    }
    public static CardInfo getEmptyCard() {
        return new CardInfo("", "", "", "", "");
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String code;
    }
}
