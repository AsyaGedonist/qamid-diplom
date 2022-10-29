package data;


import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper(){}

    static Faker faker = new Faker(new Locale("en"));
    static FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"),
            new RandomService());

    public static String generateCode(String code) {
        return faker.number().digits(3);
    }

    public static String generateName(String locale, int length) {
        var str = fakeValuesService.regexify("[a-z.-]{21}").toUpperCase();
        return str.length() < length ? str : str.substring(0, length);
    }

    public static String generateMonthYear(int months, String pattern) {
        return LocalDate.now().plusMonths(months).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo("1111 2222 3333 4444",
                generateMonthYear(60, "MM"), generateMonthYear(60,"yy"),
                generateName("en", 20), generateCode("en"));
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("5555 6666 7777 8888",
                generateMonthYear(60, "MM"), generateMonthYear(60,"yy"),
                generateName("en", 20), generateCode("en"));
    }
    public static CardInfo getEmptyCard() {
        return new CardInfo("", "", "", "", "");
    }

    @Value
    @Setter
    @Getter
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String code;
    }
}
