package constants;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Jovana Micic on 18-Nov-16.
 */
public class UserConstants {
    private static final Calendar expireDate;

    static {
        expireDate = GregorianCalendar.getInstance();
        expireDate.clear();
        expireDate.set(2016, 10, 5, 11, 6, 3);
        expireDate.set(Calendar.MILLISECOND, 0);
    }


    public static final String DB_EMAIL = "test@gmail.com";
    public static final int DB_ID = 1;
    public static final String DB_FIRST_NAME = "John";
    public static final String DB_LAST_NAME = "Testing";
    public static final String DB_PROFILE_PHOTO_PATH = "img/defaultUserPhoto.png";
    public static final String DB_TOKEN = "1a34070b-972c-4156-8f0b-d73598e484f9";
    public static final Timestamp DB_TOKEN_EXPIRE_DATE = new Timestamp(expireDate.getTimeInMillis());
}