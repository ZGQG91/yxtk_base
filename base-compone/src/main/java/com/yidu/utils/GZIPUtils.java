package com.yidu.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * Created by Administrator on 2017/5/25.
 */
public class GZIPUtils {
    public static String getBoydParam(HttpServletRequest request){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            InputStream in=request.getInputStream();
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            String str=new String(out.toByteArray(),"UTF-8");
            return str;
        } catch (IOException e) {

        }
        return "";
    }
}
