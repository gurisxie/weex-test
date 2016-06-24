package sword.ios.app.utils;

/**
 * Created by admin on 16/3/22.
 */
public class SleepUtils {
    public static void sleep(long millisecond) {
        try {
            Thread.sleep(millisecond);
        }catch (Exception e){}
    }
}
