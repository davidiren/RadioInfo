import model.ProgramLoader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Testing {
    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("innan: "+ldt);
        LocalDateTime efter = ldt.minusHours(12);
        System.out.println("efter: "+efter);
        LocalDateTime plus = ldt.plusHours(12);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formattedDate = efter.format(dtf);
        String hello = plus.format(dtf);
        System.out.println("After formatting: " + formattedDate);
        System.out.println("After formatting: " + hello);

    }
}
