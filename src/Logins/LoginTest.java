
package Logins;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michael
 */
public class LoginTest {

    public static void main(String[] args) {
//        LoginManager lm = new LoginManager();
//        lm.getLogins().put("test1", "1234");
//        lm.getLogins().put("test2", "0000");
//        lm.getLogins().put("test3", "4321");
//        lm.getLogins().put("test4", "69");
//        lm.saveLogins();
//        for (Map.Entry<String, String> entry: lm.getLogins().entrySet())
//        {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
        LoginLoop l = new LoginLoop();
        l.start();
    }
}
