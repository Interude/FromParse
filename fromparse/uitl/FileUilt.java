//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package fromparse.uitl;

import fromparse.Constant.FormConstant;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class FileUilt {
    public FileUilt() {
    }

    public String getFileSuffix(File file) {
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;
    }

    public List getTargeKey(String Key, File file) throws Exception {
        if(!file.exists()) {
            throw new Exception("文件不存在");
        } else {
            ArrayList vaule = new ArrayList();
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bf = new BufferedReader(isr);
            String line = "";

            while((line = bf.readLine()) != null) {
                line = line.replace(" ", "");
                line = line.trim();
                line = line.replace("\t", "");
                if(line.indexOf(Key) != -1) {
                    String result = line.substring(Key.length(), line.length());
                    result = result.replace("\"", "");
                    if(result.indexOf("//") != -1) {
                        result = result.substring(0, result.indexOf("//"));
                    }

                    vaule.add(result);
                }
            }

            return vaule;
        }
    }

    public List<String> getFileNameByKey(String KeyName, String FORMDIR) throws Exception {
        File filedir = new File(FORMDIR);
        ArrayList list = new ArrayList();
        if(filedir.exists() && filedir.isDirectory()) {
            File[] files = filedir.listFiles();
            File[] var9 = files;
            int var8 = files.length;

            for(int var7 = 0; var7 < var8; ++var7) {
                File ff = var9[var7];
                if(ff.isFile() && this.getFileSuffix(ff).equals("form")) {
                    FileInputStream fis = new FileInputStream(ff);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bf = new BufferedReader(isr);
                    String line = "";

                    while((line = bf.readLine()) != null) {
                        if(line.indexOf("//") != -1) {
                            line = line.substring(0, line.indexOf("//"));
                        }

                        line = line.replace(" ", "");
                        line = line.trim();
                        line = line.replace("\t", "");
                        if(line.indexOf(KeyName) != -1) {
                            list.add(ff.getName());
                            return list;
                        }
                    }
                }
            }

            return null;
        } else {
            throw new Exception("无效的路径");
        }
    }

    public JSONObject getForMsg(File file) {
        JSONObject map = new JSONObject();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            InputStreamReader e = new InputStreamReader(fis);
            BufferedReader bf = new BufferedReader(e);
            String line = "";

            while(true) {
                while(true) {
                    do {
                        if((line = bf.readLine()) == null) {
                            return null;
                        }

                       // line = line.replace(" ", "");
                        line = line.trim();
                        line = line.replace("\t", "");
                        if(line.indexOf("XFSFIELD") != -1) {
                            return map;
                        }
                    } while(line.indexOf("BEGIN") != -1);

                    if(line.indexOf("XFSFORM") != -1) {
                        line = line.substring("XFSFORM".length(), line.length());
                        line = line.replace("\"", "");
                        map.put("name", line);
                    } else {
                        String[] s;
                        if(line.indexOf("SIZE") != -1 && line.indexOf("POINTSIZE") == -1) {
                            line = line.substring("SIZE".length(), line.length());
                            line = line.replace("\"", "");
                            s = line.split(",");
                            map.put("width", s[0]);
                            map.put("height", s[1]);
                        } else if(line.indexOf("ALIGNMENT") != -1) {
                            line = line.substring("ALIGNMENT".length(), line.length());
                            line = line.replace("\"", "");
                            s = line.split(",");
                            map.put("ALIGNMENT", s[0]);
                            map.put("offsetx", s[1]);
                            map.put("offsety", s[2]);
                        } else if(line.indexOf("UNIT") != -1) {
                            line = line.substring("UNIT".length(), line.length());
                            line = line.replace("\"", "");
                            s = line.split(",");
                            map.put("base", s[0]);
                            map.put("unitx", s[1]);
                            map.put("unitxy", s[2]);
                        } else if(line.indexOf("LANGUAGE") != -1) {
                            line = line.substring("LANGUAGE".length(), line.length());
                            line = line.replace("\"", "");
                            map.put("languageid", line);
                        } else if(line.indexOf("POINTSIZE") != -1) {
                            line = line.substring("POINTSIZE".length(), line.length());
                            line = line.replace("\"", "");
                            map.put("POINTSIZE", line);
                        }
                    }
                }
            }
        } catch (FileNotFoundException var8) {
            var8.printStackTrace();
        } catch (IOException var9) {
            var9.printStackTrace();
        } catch (JSONException var10) {
            var10.printStackTrace();
        }

        return null;
    }

    public int getLineNume(File file, String targeName, String tag) {
        try {
            FileInputStream e = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(e, "GB2312");
            BufferedReader bf = new BufferedReader(isr);
            String line = "";
            int i = 0;

            while((line = bf.readLine()) != null) {
                ++i;
                line = line.replace(" ", "");
                line = line.trim();
                line = line.replace("\t", "");
                line = line.replace("\"", "");
                if(line.length() > tag.length()) {
                    String lines = line.substring(tag.length(), line.length());
                    if(line.indexOf(targeName) != -1 && line.indexOf(tag) != -1 && lines.equals(targeName)) {
                        return i;
                    }
                }
            }
        } catch (FileNotFoundException var10) {
            var10.printStackTrace();
        } catch (IOException var11) {
            var11.printStackTrace();
        }

        return 0;
    }

    public JSONObject getFieldMsg(int lineNum, File file) throws Exception {
        JSONObject object = new JSONObject();
        if(String.valueOf(lineNum).equals("0")) {
            throw new Exception("无效的FieldName");
        } else {
            FileInputStream fis = null;

            try {
                fis = new FileInputStream(file);
                InputStreamReader e = new InputStreamReader(fis, "GB2312");
                BufferedReader bf = new BufferedReader(e);
                int i = 0;
                String line = "";

                while((line = bf.readLine()) != null) {
                    ++i;
                  // line = line.replace(" ", "");
                    line = line.trim();
                    line = line.replace("\t", "");
                    if(line.indexOf("//") != -1) {
                        line = line.substring(0, line.indexOf("//"));
                    }

                    if(i >= lineNum) {
                        String[] data;
                        if(line.indexOf("XFSFIELD") != -1) {
                            line = line.substring("XFSFIELD".length(), line.length());
                            line = line.replace("\"", "");
                            object.put("name", line);
                        } else if(line.indexOf("POSITION") != -1) {
                            line = line.substring("POSITION".length(), line.length());
                            line = line.replace("\"", "");
                            data = line.split(",");
                            object.put("positionX", data[0]);
                            object.put("positionY", data[1]);
                        } else if(line.indexOf("HORIZONTAL") != -1) {
                            line = line.substring("HORIZONTAL".length(), line.length());
                            line = line.replace("\"", "");
                            object.put("HORIZONTAL", line);
                        } else if(line.indexOf("OVERFLOW") != -1) {
                            line = line.substring("OVERFLOW".length(), line.length());
                            line = line.replace("\"", "");
                            object.put("OVERFLOW", line);
                        } else if(line.indexOf("INITIALVALUE") != -1) {
                            line = line.substring("INITIALVALUE".length(), line.length());
                            line = line.replace("\"", "").trim();
                            object.put("INITIALVALUE", line);
                        } else if(line.indexOf("SIZE") != -1) {
                            line = line.substring("SIZE".length(), line.length());
                            line = line.replace("\"", "");
                            data = line.split(",");
                            object.put("width", data[0]);
                            object.put("height", data[1]);
                        } else if(line.indexOf("STYLE") != -1) {
                            if(line != null) {
                                line = line.substring("STYLE".length(), line.length());
                                object.put("STYLE", line);
                            }
                        } else if(line.indexOf("TYPE") != -1 && line != null) {
                            line = line.substring("TYPE".length(), line.length());
                            object.put("TYPE", line);
                        }

                        if(line.indexOf("FONT") != -1) {
                            line = line.substring("FONT".length(), line.length());
                            line = line.replace("\"", "");
                            object.put("FONT", line);
                        }

                        if(line.indexOf("BARCODE") != -1) {
                            line = line.substring("BARCODE".length(), line.length());
                            object.put("BARCODE", line);
                        }

                        if(line.indexOf("OVERFLOW") != -1) {
                            line = line.substring("OVERFLOW".length(), line.length());
                            object.put("OVERFLOW", line);
                        }

                        if(line.indexOf("INDEX") != -1) {
                            line.trim();
                            line = line.substring("INDEX".length(), line.length());
                            data = line.split(",");
                            object.put("index_X", data[1]);
                            object.put("index_Y", data[2]);
                        }

                        if(line.indexOf("END") != -1) {
                            return object;
                        }
                    }
                }
            } catch (FileNotFoundException var10) {
                var10.printStackTrace();
            } catch (IOException var11) {
                var11.printStackTrace();
            } catch (JSONException var12) {
                var12.printStackTrace();
            }

            return object;
        }
    }

    public JSONObject getMediaMsg(int LineNumb, File file) {
        JSONObject map = new JSONObject();

        try {
            FileInputStream e = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(e, "GB2312");
            BufferedReader bf = new BufferedReader(isr);
            int count = 0;
            String line = "";

            while((line = bf.readLine()) != null) {
                ++count;
                line = line.replace(" ", "");
                line = line.trim();
                line = line.replace("\t", "");
                if(line.indexOf("//") != -1) {
                    line = line.substring(0, line.indexOf("//"));
                }

                if(count > LineNumb) {
                    String[] s;
                    if(line.indexOf("UNIT") != -1) {
                        line = line.substring("UNIT".length(), line.length());
                        s = line.split(",");
                        map.put("base", s[0]);
                        map.put("unitx", s[1]);
                        map.put("unity", s[2]);
                    }

                    if(line.indexOf("SIZE") != -1) {
                        line = line.substring("SIZE".length(), line.length());
                        s = line.split(",");
                        map.put("sizewidth", s[0]);
                        map.put("sizeheight", s[1]);
                    }

                    if(line.indexOf("PRINTAREA") != -1) {
                        line = line.substring("PRINTAREA".length(), line.length());
                        s = line.split(",");
                        map.put("X", s[0]);
                        map.put("Y", s[1]);
                    }

                    if(line.indexOf("END") != -1) {
                        return map;
                    }
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return map;
    }

    public List<String> getAllName(String XFSname, String FORMDIR) {
        ArrayList list = new ArrayList();

        try {
            List e = this.getFileNameByKey(XFSname, FORMDIR);
            File[] files = new File[e.size()];

            for(int i = 0; i < files.length; ++i) {
                files[i] = new File(FORMDIR, (String)e.get(i));
                FileInputStream fis = new FileInputStream(files[i]);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bf = new BufferedReader(isr);
                String line = "";

                while((line = bf.readLine()) != null) {
                    line = this.LineHandle(line);
                    if(line.indexOf(XFSname) != -1) {
                        line = line.substring(XFSname.length(), line.length());
                        line = line.replace("\"", "");
                        System.out.println(line);
                        list.add(line);
                    }
                }
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        }

        return list;
    }

    private String LineHandle(String line) {
        line = line.replace(" ", "");
        line = line.trim();
        if(line.indexOf("//") != -1) {
            line = line.substring(0, line.indexOf("//"));
        }

        return line;
    }

    public JSONObject getMediaMsg(String medianame, String FORMDIR) throws Exception {
        new JSONObject();
        File file = new File(FORMDIR, (String)this.getFileNameByKey(medianame, FORMDIR).get(0));
        JSONObject object = this.getMediaMsg(this.getLineNume(file, medianame, "MEDIA"), file);
        return object;
    }

    public int FiedlX(JSONObject FormMsg, JSONObject MediaMsg, JSONObject FieldMsg) throws EOFException, JSONException, UnsupportedEncodingException {
        int X = 0;
        if(FormMsg != null && MediaMsg != null && FieldMsg != null) {
            if(!MediaMsg.has("X")) {
                MediaMsg.put("X", "0");
            }

            if(FormMsg.optString("ALIGNMENT").equals("")) {
                FormMsg.put("ALIGNMENT", "TOPLEFT");
                FormMsg.put("offsetx", "0");
                FormMsg.put("offsety", "0");
            }

            String INITIALVALUE;
            int len;
            if(FormMsg.optString("ALIGNMENT").equals("TOPLEFT")) {
                if(FieldMsg.optString("HORIZONTAL").trim().equals("LEFT")) {
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(FormMsg.optString("offsetx")) + Integer.parseInt(FieldMsg.optString("positionX"));
                    return X;
                }

                if(FieldMsg.optString("HORIZONTAL").equals("RIGHT")) {
                    INITIALVALUE = FieldMsg.optString("INITIALVALUE");
                    len = INITIALVALUE.length();
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(FormMsg.optString("offsetx")) + Integer.parseInt(FieldMsg.optString("positionX")) + Integer.parseInt(FieldMsg.optString("width")) - len;
                    if(X < 0) {
                        X = 0;
                        System.err.println(FieldMsg.opt("name") + "右对齐溢出");
                    }

                    return X;
                }

                if(FieldMsg.optString("HORIZONTAL").equals("CENTER")) {
                    INITIALVALUE = FieldMsg.optString("INITIALVALUE");
                    len = INITIALVALUE.length();
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(FormMsg.optString("offsetx")) + Integer.parseInt(FieldMsg.optString("positionX")) + (Integer.parseInt(FieldMsg.optString("width")) - len) / 2;
                    if(X < 0) {
                        X = 0;
                        System.err.println(FieldMsg.opt("name") + "居中溢出");
                    }

                    return X;
                }
            }

            if(FormMsg.optString("ALIGNMENT").equals("TOPRIGHT")) {
                if(FieldMsg.optString("HORIZONTAL").equals("LEFT")) {
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(MediaMsg.optString("sizewidth")) - Integer.parseInt(FormMsg.optString("width")) - Integer.parseInt(FormMsg.optString("offsetx")) + Integer.parseInt(FieldMsg.optString("positionX"));
                    if(X < 0) {
                        X = 0;
                    }
                }

                if(FieldMsg.optString("HORIZONTAL").equals("RIGHT")) {
                    INITIALVALUE = FieldMsg.optString("INITIALVALUE");
                    len = INITIALVALUE.length();
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(MediaMsg.optString("sizewidth")) - Integer.parseInt(FormMsg.optString("width")) - Integer.parseInt(FormMsg.optString("offsetx")) + Integer.parseInt(FieldMsg.optString("positionX")) + Integer.parseInt(FieldMsg.optString("width")) - len;
                    if(X < 0) {
                        X = 0;
                    }
                }

                if(FieldMsg.optString("HORIZONTAL").equals("CENTER")) {
                    INITIALVALUE = FieldMsg.optString("INITIALVALUE");
                    len = INITIALVALUE.length();
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(MediaMsg.optString("sizewidth")) - Integer.parseInt(FormMsg.optString("width")) - Integer.parseInt(FormMsg.optString("offsetx")) + Integer.parseInt(FieldMsg.optString("positionX")) + (Integer.parseInt(FieldMsg.optString("width")) + len) / 2;
                    if(X < 0) {
                        X = 0;
                    }
                }
            }

            if(FormMsg.optString("ALIGNMENT").equals("BOTTOMLEFT")) {
                if(FieldMsg.optString("HORIZONTAL").equals("LEFT")) {
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(FormMsg.optString("offsetx")) + Integer.parseInt(FieldMsg.optString("positionX"));
                    if(X < 0) {
                        X = 0;
                    }
                }

                if(FieldMsg.optString("HORIZONTAL").equals("RIGHT")) {
                    INITIALVALUE = FieldMsg.optString("INITIALVALUE");
                    len = INITIALVALUE.length();
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(FormMsg.optString("offsetx")) + Integer.parseInt(FieldMsg.optString("positionX")) + Integer.parseInt(FieldMsg.optString("width")) - len;
                    if(X < 0) {
                        X = 0;
                    }
                }

                if(FieldMsg.optString("HORIZONTAL").equals("CENTER")) {
                    INITIALVALUE = FieldMsg.optString("INITIALVALUE");
                    len = INITIALVALUE.length();
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(FormMsg.optString("offsetx")) + Integer.parseInt(FieldMsg.optString("positionX")) + (Integer.parseInt(FieldMsg.optString("width")) - len) / 2;
                    if(X < 0) {
                        X = 0;
                    }
                }
            }

            if(FormMsg.optString("ALIGNMENT").equals(FormConstant.BOTTOMRIGHT)) {
                if(FieldMsg.optString("HORIZONTAL").equals("LEFT")) {
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(MediaMsg.optString("sizewidth")) - Integer.parseInt(FormMsg.optString("width")) - Integer.parseInt("offsetx") + Integer.parseInt("positionX");
                    if(X < 0) {
                        X = 0;
                    }
                }

                if(FieldMsg.optString("HORIZONTAL").equals("RIGHT")) {
                    INITIALVALUE = FieldMsg.optString("INITIALVALUE");
                    len = INITIALVALUE.length();
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(MediaMsg.optString("sizewidth")) - Integer.parseInt(FormMsg.optString("width")) - Integer.parseInt("offsetx") + Integer.parseInt("positionX") + Integer.parseInt(FieldMsg.optString("width")) - len;
                    if(X < 0) {
                        X = 0;
                    }
                }

                if(FieldMsg.optString("HORIZONTAL").equals("CENTER")) {
                    INITIALVALUE = FieldMsg.optString("INITIALVALUE");
                    len = INITIALVALUE.length();
                    X = Integer.parseInt(MediaMsg.optString("X")) + Integer.parseInt(MediaMsg.optString("sizewidth")) - Integer.parseInt(FormMsg.optString("width")) - Integer.parseInt("offsetx") + Integer.parseInt("positionX") + (Integer.parseInt(FieldMsg.optString("width")) - len) / 2;
                    if(X < 0) {
                        X = 0;
                    }
                }
            }

            return X;
        } else {
            throw new EOFException("计算FiedlX坐标是出现错误");
        }
    }

    public int FiedlY(JSONObject FormMsg, JSONObject MediaMsg, JSONObject FieldMsg) throws EOFException, JSONException, UnsupportedEncodingException {
        boolean Y = false;
        if(FormMsg != null && MediaMsg != null && FieldMsg != null) {
            if(!MediaMsg.has("Y")) {
                MediaMsg.put("Y", "0");
            }

            int Y1 = Integer.parseInt(MediaMsg.optString("Y").trim()) + Integer.parseInt(FormMsg.optString("offsety").trim());
            Y1 = Integer.parseInt(MediaMsg.optString("Y").trim()) + Integer.parseInt(FormMsg.optString("offsety").trim()) + Integer.parseInt(FieldMsg.optString("positionY").trim());
            if(FormMsg.optString("ALIGNMENT").equals("TOPLEFT")) {
                return Y1;
            } else if(FormMsg.optString("ALIGNMENT").equals("TOPRIGHT")) {
                return Y1;
            } else {
                if(FormMsg.optString("ALIGNMENT").equals("BOTTOMLEFT")) {
                    Y1 = Integer.parseInt(MediaMsg.optString("Y")) + Integer.parseInt(MediaMsg.optString("sizeheight")) - Integer.parseInt(FormMsg.optString("height")) - Integer.parseInt(FormMsg.optString("offsety")) + Integer.parseInt(FieldMsg.optString("positionY"));
                }

                if(FormMsg.optString("ALIGNMENT").equals(FormConstant.BOTTOMRIGHT)) {
                    Y1 = Integer.parseInt(MediaMsg.optString("Y")) + Integer.parseInt(MediaMsg.optString("sizeheight")) - Integer.parseInt(FormMsg.optString("height")) - Integer.parseInt(FormMsg.optString("offsety")) + Integer.parseInt(FieldMsg.optString("positionY"));
                } else {
                    Y1 = 0;
                }

                return Y1;
            }
        } else {
            throw new EOFException("计算FiedlY坐标是出现错误");
        }
    }

    public int useFiedlX() {
        return 0;
    }

    public int useFiedlY() {
        return 0;
    }
}
