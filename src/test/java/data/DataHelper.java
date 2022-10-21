package data;


import lombok.Value;

public class DataHelper {
    private DataHelper(){}
    public static CardInfo getApprovedCard() {
        return new CardInfo("1111 2222 3333 4444", "11", "27", "Anastasiia", "123");
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("5555 6666 7777 8888", "11", "27", "Anastasiia", "123");
    }
    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String code;
        public CardInfo(String cardNumber, String month, String year, String owner, String code) {
            this.cardNumber = cardNumber;
            this.month = month;
            this.year = year;
            this.owner = owner;
            this.code = code;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public String getMonth() {
            return month;
        }

        public String getYear() {
            return year;
        }

        public String getOwner() {
            return owner;
        }

        public String getCode() {
            return code;
        }
    }
}
