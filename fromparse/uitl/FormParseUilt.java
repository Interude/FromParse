//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package fromparse.uitl;

import fromparse.FromBean.SpecialBean;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FormParseUilt extends FileUilt {
    String FormPath;
    private static FormParseUilt fromParseUilt;

    private FormParseUilt() {}

    public FormParseUilt(String formPath) {
        this.FormPath = formPath;
    }

    public static FormParseUilt getInstance(String formPath) {
        fromParseUilt = new FormParseUilt(formPath);
        return fromParseUilt;
    }

    public JSONObject FormList() throws Exception {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        int count = 0;
        File targePath = new File(this.FormPath);
        if(targePath.isDirectory() && targePath.exists()) {
            File[] files = targePath.listFiles();
            File[] var9 = files;
            int var8 = files.length;

            for(int var7 = 0; var7 < var8; ++var7) {
                File f = var9[var7];
                if(super.getFileSuffix(f).equals("form")) {
                    System.out.println(f.getName());
                    ++count;
                    List list = super.getTargeKey("XFSFORM", f);
                    Iterator iterator = list.iterator();

                    while(iterator.hasNext()) {
                        array.put(iterator.next());
                    }
                }
            }

            object.put("count", count);
            object.put("name", array);
            return object;
        } else {
            throw new Exception("无效路径");
        }
    }

    public JSONObject QueryForm(JSONObject object) throws Exception {
        try {
            File e = new File(this.FormPath, (String)super.getFileNameByKey(object.getString("formName"), this.FormPath).get(0));
            if(e.exists() && e.isFile()) {
                JSONObject map = super.getForMsg(e);
                JSONArray array = new JSONArray();
                List list = super.getTargeKey("XFSFIELD", e);

                for(int i = 0; i < list.size(); ++i) {
                    array.put(list.get(i));
                }

                map.put("fieldcount", array.length());
                map.put("fieldname", array);
                return map;
            } else {
                throw new Exception("无效的文件");
            }
        } catch (JSONException var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public JSONObject QueryField(JSONObject object) {
        try {
            File e = new File(this.FormPath, (String)super.getFileNameByKey(object.getString("formName"), this.FormPath).get(0));
            if(e.exists() && e.isFile() && super.getFileSuffix(e).equals("form")) {
                JSONObject map = super.getFieldMsg(super.getLineNume(e, object.getString("fieldName"), "XFSFIELD"), e);
                return map;
            } else {
                throw new Exception("无效的文件:" + e.getAbsolutePath());
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public JSONObject MediaList() throws Exception {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        int count = 0;
        File targePath = new File(this.FormPath);
        if(targePath.isDirectory() && targePath.exists()) {
            File[] files = targePath.listFiles();
            File[] var9 = files;
            int var8 = files.length;

            for(int var7 = 0; var7 < var8; ++var7) {
                File f = var9[var7];
                if(super.getFileSuffix(f).equals("form")) {
                    ++count;
                    List list = super.getTargeKey("XFSMEDIA", f);
                    Iterator iterator = list.iterator();

                    while(iterator.hasNext()) {
                        array.put(iterator.next());
                    }
                }
            }

            object.put("count", count);
            object.put("name", array);
            return object;
        } else {
            throw new Exception("无效路径");
        }
    }

    public JSONObject QueryMedia(JSONObject object) throws Exception {
        try {
            List e = super.getFileNameByKey(object.getString("mediaName"), this.FormPath);
            if(e == null) {
                throw new Exception("media不存在!");
            } else {
                String mediaName = (String)e.get(0);
                File file = new File(this.FormPath, mediaName);
                if(file.exists() && file.isFile() && super.getFileSuffix(file).equals("form")) {
                    JSONObject map = super.getMediaMsg(super.getLineNume(file, object.getString("mediaName"), "XFSMEDIA"), file);
                    return map;
                } else {
                    throw new Exception("无效的文件:" + file.getAbsolutePath());
                }
            }
        } catch (JSONException var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public List<JSONObject> PrintForm_cz(String formName, String mediaName, String Fields) throws Exception {
        ArrayList fieldMsg = new ArrayList();
        ArrayList printfMsg = new ArrayList();
        new JSONObject();
        File f = null;

        try {
            f = new File(this.FormPath, (String)super.getFileNameByKey(formName, this.FormPath).get(0));
           // Fields = "NewField1=\\0XYBH=1234\\0NewField2=\\0DYSJ=20171017\\0NewField3=\\0NewField4=\\0CopyOfJF=\\0JF=中国工商银行\\0CopyOfYF=\\0YF=长城\\0NewField5=\\0NewField6=\\0NewField7=\\0CopyOfJFJC=\\0JFJC=工行\\0NewField8=\\0ZFZH=6222080200022960000\\0ZHHM=工商银行\\0NewField9=\\0CopyFDDWFZR=\\0FDDWFZR=工商银行\\0CopyFDZJNX=\\0FDZJNX=身份证\\0FDZJHM=441523199903086000\\0FDZJYXRQ=20170907\\0CopyFDZJCQYXQBZ=\\0FDZJCQYXQBZ=20年\\0CopyFDDH=\\0FDDH=13423537004\\0CopyFDZJYXRQ=\\0CopyJBR=\\0JBR=小李\\0CopyJBRZJLX=\\0JBRZJLX=二代证\\0CopyJBRZJHM=\\0JBRZJHM=441523199902096000\\0CopyJBRZJCQYXBZ=\\0JBRZJCQYXBZ=终身\\0CopyJBRDH=\\0JBRDH=13423532754\\0CopyJBRZJYXRQ=\\0JBRZJYXRQ=20100909\\0CopyLXR=\\0LXR=小王\\0CopyLXRZJLX=\\0LXRZJLX=学生证\\0CopyLXRZJHM=\\0LXRZJHM=13124640111\\0CopyLXRDH=\\0LXRDH=13828948888\\0CopyZJHM=\\0CopyZHHM=\\0CopZFZH=\\0CopyCKRXX=\\0CKRXX=大白\\0CoCKRXXZJLX=\\0CKRXXZJLX=驾驶证\\0CoCCKRXXZJHM=\\0CCKRXXZJHM=12345678\\0CKRXXZJLXZJCQYXBZ=\\0CKRXXZJCQYXBZ=临时\\0CopyCCKRXXDH=\\0CCKRXXDH=13422228888\\0CopyCCKRXXZJYXRQ=\\0CCKRXXZJYXRQ=20200909\\0NewField10=\\0NewField11=\\0\\0";
       //  Fields = "CopyDQH=地区号：\\0CopyGZRQ=工作日期：\\0CopyZH=账号：\\0NewField1=综合协议所包括的五项内容：\\0NewField2=人民币单位银行结算账户管理服务\\0NewField3=对公账户回单自助打印服务\\0NewField4=银企对账服务\\0NewField5=单位客户工银信使服务\\0NewField6=财智账户卡业务\\0NewField7=以下为双方签章区域：\\0NewField8=甲方：（公章）　　　　                                          乙方：（签章）\\0NewField9=甲方：（预留印鉴）\\0NewField10=甲方法定代表人或负责人：（签字或盖章）　　　                    乙方初审柜员：（签章）\\0ZJLX1=甲方授权代理人：（签字或盖章）　　　　　　　　　                乙方复审柜员：（签章）\\0ZJHM1=      年　　月　　日　　　　　　　　　　　　　　　　　　            年　　月　　日\\0CopyOfZJHM1=                                          第　4　页,  共　4　页\\0DQH=200\\0CopyZDBH=终端编号：\\0ZDBH=898900\\0CopyCSGYH=初审柜员号：\\0CSGYH=123000\\0CopyFSGYH=复审柜员号：\\0FSGYH=456000\\0GZRQ=20171007\\0CopyGZSJ=工作时间：\\0GZSJ=两天\\0ZH=622208026789000\\0CopyXYBH=协议编号：\\0XYBH=789\\0BH=10\\0CopyBH=编号\\0\\0\n";
            System.out.println("----" + f.getName() + "------");
        } catch (Exception var33) {
            throw new Exception("当前目录下没有Form文件或文件名不正确");
        }

        List specialField = null;
        File ff = new File(this.FormPath + "/config.xml");
        if(ff.exists()) {
            specialField = SpecialHandler.getInstance(ff).Filter();
        }

        Fields = Fields.replace("\0", "&");
        Fields = Fields.replace("$", "&");
        String[] array = Fields.split("&");

        for(int list = 0; list < array.length; ++list) {
            String ss = array[list].substring(array[list].length() - 1, array[list].length());
            if(ss.equals("=")) {
                String allFieldName = array[list].substring(0, array[list].length() - 1);
                array[list] = allFieldName;
            }
        }

        ArrayList var34 = new ArrayList();
        String[][] var35 = new String[array.length][2];
        ArrayList var36 = new ArrayList();
        String fieldName = null;

        int field_Msg;
        for(field_Msg = 0; field_Msg < array.length; ++field_Msg) {
            if(array[field_Msg].trim().indexOf("=") != -1) {
                fieldName = array[field_Msg].substring(0, array[field_Msg].indexOf("="));
            } else {
                fieldName = array[field_Msg];
            }

            var36.add(fieldName);
        }

        for(field_Msg = 0; field_Msg < var35.length; ++field_Msg) {
            if(array[field_Msg].indexOf("=") == -1) {
                var34.add(array[field_Msg]);
            } else {
                var35[field_Msg] = array[field_Msg].split("=");
            }
        }

        JSONObject mediajson;
        for(field_Msg = 0; field_Msg < var35.length; ++field_Msg) {
            if(var35[field_Msg][0] != null) {
                if(super.getLineNume(f, var35[field_Msg][0], "XFSFIELD") == 0) {
                    System.out.println(var35[field_Msg][0] + "+++++++++");
                }

                mediajson = this.getFieldMsg(super.getLineNume(f, var35[field_Msg][0], "XFSFIELD"), f);

                try {
                    mediajson.put("INITIALVALUE", var35[field_Msg][1]);
                    fieldMsg.add(mediajson);
                } catch (JSONException var32) {
                    var32.printStackTrace();
                }
            }
        }

        for(field_Msg = 0; field_Msg < var34.size(); ++field_Msg) {
            mediajson = this.getFieldMsg(super.getLineNume(f, (String)var34.get(field_Msg), "XFSFIELD"), f);
            fieldMsg.add(mediajson);
        }

        ArrayList var38 = new ArrayList();

        for(int var37 = 0; var37 < var36.size(); ++var37) {
            for(int formjson = 0; formjson < fieldMsg.size(); ++formjson) {
                if(((String)var36.get(var37)).equals(((JSONObject)fieldMsg.get(formjson)).optString("name"))) {
                    var38.add((JSONObject)fieldMsg.get(formjson));
                    fieldMsg.remove(formjson);
                    break;
                }
            }
        }

        mediajson = this.getMediaMsg(mediaName, this.FormPath);
        JSONObject var39 = this.getForMsg(f);
        JSONObject obj = new JSONObject();
        obj.put("formName", formName);
        List allFields = this.getAllFieldName(obj);
        System.out.println(allFields.size() + "=========");
        new ArrayList();

        int printfobj;
        for(printfobj = 0; printfobj < var38.size(); ++printfobj) {
            for(int mediaMsg = 0; mediaMsg < allFields.size(); ++mediaMsg) {
                if(((JSONObject)var38.get(printfobj)).getString("name").equals(allFields.get(mediaMsg))) {
                    allFields.remove(mediaMsg);
                }
            }
        }

        List no_ValueFieldName = allFields;

        JSONObject var40;
        for(printfobj = 0; printfobj < no_ValueFieldName.size(); ++printfobj) {
            var40 = this.getFieldMsg(super.getLineNume(f, (String)no_ValueFieldName.get(printfobj), "XFSFIELD"), f);
            var38.add(var40);
        }

        for(printfobj = 0; printfobj < var38.size(); ++printfobj) {
            if(!((JSONObject)var38.get(printfobj)).has("INITIALVALUE")) {
                ((JSONObject)var38.get(printfobj)).put("INITIALVALUE", "");
            }
        }

        var40 = this.getMediaSize(mediaName);
        JSONObject formMsg = this.getFormSize(formName);
        int i;
        if(specialField != null) {
            for(i = 0; i < specialField.size(); ++i) {
                if(((SpecialBean)specialField.get(i)).getFormname().equals(formName)) {
                    for(int objj = 0; objj < var38.size(); ++objj) {
                        if(((JSONObject)var38.get(objj)).opt("name").equals(((SpecialBean)specialField.get(i)).getFieldName())) {
                            String fieldnames = ((JSONObject)var38.get(objj)).optString("name");
                            String value = ((JSONObject)var38.get(objj)).optString("INITIALVALUE");
                            JSONObject objects = super.getFieldMsg(this.getLineNume(f, fieldnames, "XFSFIELD"), f);
                            String defaultValue = objects.optString("INITIALVALUE");
                            String location = ((SpecialBean)specialField.get(i)).getLocation();
                            String finalValue = null;
                            if(!value.equals(defaultValue)) {
                                if(location.equals("0")) {
                                    (new StringBuilder(String.valueOf(value))).append(defaultValue).toString();
                                }

                                if(location.equals("-1")) {
                                    finalValue = defaultValue + value;
                                } else {
                                    String str = defaultValue.substring(0, Integer.parseInt(location));
                                    finalValue = str + value + defaultValue.substring(Integer.parseInt(location), defaultValue.length());
                                }
                            } else {
                                finalValue = defaultValue;
                            }

                            ((JSONObject)var38.get(objj)).put("INITIALVALUE", finalValue);
                        }
                    }
                }
            }
        }

        for(i = 0; i < var38.size(); ++i) {
            JSONObject var41 = new JSONObject();
            JSONObject var42 = (JSONObject)var38.get(i);
            float var10000 = (float)super.FiedlX(var39, mediajson, var42);
            var10000 = (float)super.FiedlY(var39, mediajson, var42);
            var41.put("X", Integer.parseInt(var42.optString("positionX").trim()));
            var41.put("Y", Integer.parseInt(var42.optString("positionY").trim()));
            if(var42.has("INITIALVALUE")) {
                var41.put("INITIALVALUE", var42.optString("INITIALVALUE").replace("#", " "));
            }

            if(var42.has("BARCODE")) {
                var41.put("BARCODE", var42.optString("BARCODE").trim());
            }

            if(var42.has("STYLE")) {
                var41.put("STYLE", var42.optString("STYLE").trim());
            }

            if(var42.has("FONT")) {
                var41.put("FONT", var42.optString("FONT").trim());
            }

            if(var42.has("TYPE")) {
                var41.put("TYPE", var42.optString("TYPE").trim());
            }

            if(var39.has("POINTSIZE")) {
                var41.put("POINTSIZE", var39.optString("POINTSIZE").trim());
            }

            if(var42.has("OVERFLOW")) {
                var41.put("OVERFLOW", var42.optString("OVERFLOW").trim());
            }

            if(var42.has("name")) {
                var41.put("name", var42.optString("name"));
            }

            if(var42.has("HORIZONTAL")) {
                var41.put("HORIZONTAL", var42.optString("HORIZONTAL").trim());
            }

            if(var42.has("width")) {
                var41.put("width", var42.optString("width"));
            }

            if(var42.has("height")) {
                var41.put("height", var42.optString("height"));
            }

            var41.put("mediaWidth", var40.optString("mediaWidth"));
            var41.put("mediaHeight", var40.optString("mediaHeight"));
            var41.put("formWidth", formMsg.optString("formWidth"));
            var41.put("formHeight", formMsg.optString("formHeight"));
            printfMsg.add(var41);
        }

        return printfMsg;
    }

    public List<JSONObject> PrintForm_pt(String formName, String mediaName, String Fields) throws Exception {
        ArrayList fieldMsg = new ArrayList();
        ArrayList printfMsg = new ArrayList();
        new JSONObject();
        File f = null;

        try {
            f = new File(this.FormPath, (String)super.getFileNameByKey(formName, this.FormPath).get(0));
            System.out.println("----" + f.getName() + "------");
        } catch (Exception var19) {
            throw new Exception("当前目录下没有Form文件或文件名不正确");
        }

        System.err.println(Fields);
        String[] array = Fields.split("&");

        for(int list = 0; list < array.length; ++list) {
            String ss = array[list].substring(array[list].length() - 1, array[list].length());
            if(ss.equals("=")) {
                String allFieldName = array[list].substring(0, array[list].length() - 1);
                array[list] = allFieldName;
            }
        }

        ArrayList var20 = new ArrayList();
        String[][] var21 = new String[array.length][2];
        ArrayList var22 = new ArrayList();
        String fieldName = null;

        int field_Msg;
        for(field_Msg = 0; field_Msg < array.length; ++field_Msg) {
            if(array[field_Msg].trim().indexOf("=") != -1) {
                fieldName = array[field_Msg].substring(0, array[field_Msg].indexOf("="));
            } else {
                fieldName = array[field_Msg];
            }

            var22.add(fieldName);
        }

        for(field_Msg = 0; field_Msg < var21.length; ++field_Msg) {
            if(array[field_Msg].indexOf("=") == -1) {
                var20.add(array[field_Msg]);
            } else {
                var21[field_Msg] = array[field_Msg].split("=");
            }
        }

        JSONObject mediajson;
        for(field_Msg = 0; field_Msg < var21.length; ++field_Msg) {
            if(var21[field_Msg][0] != null) {
                mediajson = this.getFieldMsg(super.getLineNume(f, var21[field_Msg][0], "XFSFIELD"), f);

                try {
                    mediajson.put("INITIALVALUE", var21[field_Msg][1]);
                    fieldMsg.add(mediajson);
                } catch (JSONException var18) {
                    var18.printStackTrace();
                }
            }
        }

        for(field_Msg = 0; field_Msg < var20.size(); ++field_Msg) {
            mediajson = this.getFieldMsg(super.getLineNume(f, (String)var20.get(field_Msg), "XFSFIELD"), f);
            fieldMsg.add(mediajson);
        }

        ArrayList var24 = new ArrayList();

        for(int var23 = 0; var23 < var22.size(); ++var23) {
            for(int formjson = 0; formjson < fieldMsg.size(); ++formjson) {
                if(((String)var22.get(var23)).equals(((JSONObject)fieldMsg.get(formjson)).optString("name"))) {
                    var24.add((JSONObject)fieldMsg.get(formjson));
                    fieldMsg.remove(formjson);
                    break;
                }
            }
        }

        mediajson = this.getMediaMsg(mediaName, this.FormPath);
        JSONObject var25 = this.getForMsg(f);

        for(int i = 0; i < var24.size(); ++i) {
            JSONObject printfobj = new JSONObject();
            JSONObject objj = (JSONObject)var24.get(i);
            float var10000 = (float)super.FiedlX(var25, mediajson, objj);
            var10000 = (float)super.FiedlY(var25, mediajson, objj);
            printfobj.put("X", Integer.parseInt(objj.optString("positionX")));
            printfobj.put("Y", Integer.parseInt(objj.optString("positionY")));
            if(objj.has("INITIALVALUE")) {
                printfobj.put("INITIALVALUE", objj.optString("INITIALVALUE").replace("#", " "));
            }

            if(objj.has("BARCODE")) {
                printfobj.put("BARCODE", objj.optString("BARCODE").trim());
            }

            if(objj.has("STYLE")) {
                printfobj.put("STYLE", objj.optString("STYLE").trim());
            }

            if(objj.has("FONT")) {
                printfobj.put("FONT", objj.optString("FONT").trim());
            }

            if(objj.has("TYPE")) {
                printfobj.put("TYPE", objj.optString("TYPE").trim());
            }

            if(var25.has("POINTSIZE")) {
                printfobj.put("POINTSIZE", var25.optString("POINTSIZE").trim());
            }

            if(objj.has("OVERFLOW")) {
                printfobj.put("OVERFLOW", objj.optString("OVERFLOW").trim());
            }

            if(objj.has("name")) {
                printfobj.put("name", objj.optString("name"));
            }

            if(objj.has("HORIZONTAL")) {
                printfobj.put("HORIZONTAL", objj.optString("HORIZONTAL").trim());
            }

            printfMsg.add(printfobj);
        }

        return printfMsg;
    }

    private List<String> getAllFieldName(JSONObject obj) {
        ArrayList list = new ArrayList();

        try {
            JSONObject e = this.QueryForm(obj);
            JSONArray array = e.getJSONArray("fieldname");

            for(int count = 0; count < array.length(); ++count) {
                list.add(array.getString(count));
            }

            return list;
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public JSONObject getFormSize(String formName) throws Exception {
        JSONObject object_Msg = new JSONObject();
        object_Msg.put("formName", formName);
        JSONObject result = new JSONObject();
        JSONObject formMsg = this.QueryForm(object_Msg);
        result.put("formWidth", formMsg.optString("formWidth"));
        result.put("formHeight", formMsg.optString("formHeight"));
        return result;
    }

    public JSONObject getMediaSize(String mediaName) throws Exception {
        JSONObject object_Msg = new JSONObject();
        JSONObject result = new JSONObject();
        object_Msg.put("mediaName", mediaName);
        JSONObject MediaMsg = this.QueryMedia(object_Msg);
        result.put("mediaWidth", MediaMsg.optString("sizewidth"));
        result.put("mediaHeight", MediaMsg.optString("sizeheight"));
        return result;
    }
}
