import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/5.
 */
public class fd {

    public static void main(String str[]){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(d));
    }
}
