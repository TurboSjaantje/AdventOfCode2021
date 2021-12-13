import java.time.chrono.MinguoChronology;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

        long value = 0;
        increase(value);
        System.out.println(value);
    }

    public static long increase(long value) {
        value++;
        return value;
    }
}

