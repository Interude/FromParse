//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package fromparse.entry;

import fromparse.Constant.FormConstant;
import fromparse.uitl.FileUilt;
import fromparse.uitl.FormParseUilt;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class Test {
    static FileUilt uilt = new FileUilt();

    public Test() {
    }

    public static void main(String[] agrs) {
        List e;
        Iterator iterator;
        JSONObject next;
        try {
            e = (new FormParseUilt("/Users/xiaochunyuan/Desktop/")).PrintForm_cz("ZHZHXYCLOSE4", "ZHZHXYCLOSEMEDIA4", (String)null);
            iterator = e.iterator();

            while(iterator.hasNext()) {
                next = (JSONObject)iterator.next();
                System.out.println(next);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }
//
//        try {
//            e = FormParseUilt.getInstance("/Users/xiaochunyuan/工作/form1030").PrintForm_pt("ZHZHXY2", "ZHZHXYMEDIA2", (String)null);
//            iterator = e.iterator();
//
//            while(iterator.hasNext()) {
//                next = (JSONObject)iterator.next();
//                System.out.println(next);
//            }
//        } catch (Exception var5) {
//            var5.printStackTrace();
//        }
//
//        try {
//            JSONObject e1 = FormParseUilt.getInstance("/Users/xiaochunyuan/工作/form1030").getMediaSize("ZHZHXY1MEDIA1");
//            System.out.println(e1);
//        } catch (Exception var4) {
//            var4.printStackTrace();
//        }

    }
}
