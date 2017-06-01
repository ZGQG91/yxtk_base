package com.yidu.utils;

import org.apache.log4j.Logger;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016/10/19.
 */
public class Tools {
    private static Logger logger = Logger.getLogger(ModuleTools.class);
    public static void outParamInfo(String srvname,String method,PageDataInter pageData){
        Iterator entries = pageData.entrySet().iterator();
        logger.info("cout then param info:{srvname:"+srvname+"},{method:"+method+"}");
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            logger.info(name+":"+valueObj.toString());
        }
    }
    public static String createRandomId(){
        UUID uuid=UUID.randomUUID();
        Date date=new Date();
        long time=date.getTime();
        String uuidResult=uuid.toString().replace("-","");
        String rc_id=uuidResult+time;
        return rc_id;
    }

    /**
     * AES加密
     *
     * @param content
     *            要加密的内容
     * @param password
     *            加密使用的密钥
     * @return 加密后的字节数组
     */
    public static byte[] encrypt(String content, String password) {
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }

        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // kgen.init(128, new SecureRandom(password.getBytes()));
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content
     *            待解密内容
     * @param password
     *            解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // kgen.init(128, new SecureRandom(password.getBytes()));
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 将二进制转换成16进制 加密
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

//    public static void main(String[] args) {
//
//        /*
//         * 加密
//         */
//        System.out.println("使用AES对称加密，请输入加密的规则");
//        String encodeRules="aesSec";
//        System.out.println("请输入要加密的内容:");
//        String content = "shareUserId=1004470&activeId=1";
//        System.out.println("根据输入的规则"+encodeRules+"加密后的密文是:"+AESEncode(encodeRules, content));
//
//        /*
//         * 解密
//         */
//        System.out.println("使用AES对称解密，请输入加密的规则：(须与加密相同)");
//        encodeRules="aesSec";
//        System.out.println("请输入要解密的内容（密文）:");
//        content = AESEncode(encodeRules, content);
//        System.out.println("根据输入的规则"+encodeRules+"解密后的明文是:"+AESDncode(encodeRules,content));
//    }
}
