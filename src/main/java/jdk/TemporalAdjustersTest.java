package jdk;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author: zscome
 * DateTime: 2020-05-09 10:34
 */
public class TemporalAdjustersTest {

    @Test
    public void getCurrentMonday() {
        TemporalAdjuster firstOfWeek = TemporalAdjusters.ofDateAdjuster(inputDate -> {
            System.out.println(inputDate.getDayOfWeek().getValue());
            System.out.println(DayOfWeek.MONDAY.getValue());
            return inputDate.minusDays(inputDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
        });
        LocalDate date = LocalDate.now().with(firstOfWeek);
        System.out.println(LocalDate.now());
        System.out.println(date);
    }

}
