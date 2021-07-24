package com.demo.tools;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
//import java.util.Base64;
//import org.apache.commons.codec.binary.Base64;

/**
 * @Description: DES加密工具
 * @Author: shijianan@adpanshi.com
 * @Date: 2020/4/29
 */
public class DESUtil {

    /**
     * 偏移变量，固定占8位字节
     */
    private static final String IV_PARAMETER = "3kk4i47h";
    /**
     * 加密密钥
     */
    private static final String SECRET_KEY = "3kk4i47h";
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DES";
    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    /**
     * 生成key
     */
    private static SecretKey generateKey() throws Exception {
        DESKeySpec dks = new DESKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(dks);
    }


    /**
     * DES加密字符串
     * 
     * @param data 待加密字符串
     * @return 加密后内容
     */
    public static String encrypt(String data) {
        if (data == null)
            return null;
        try {
            SecretKey secretKey = generateKey();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] bytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // JDK1.8及以上可直接使用Base64，JDK1.7及以下可以使用BASE64Encoder
            // Android平台可以使用android.util.Base64
            return new String(Base64.getEncoder().encode(bytes));

        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    /**
     * DES解密字符串
     * 
     * @param data 待解密字符串
     * @return 解密后内容
     */
    public static String decrypt(String data) {
        if (data == null)
            return null;
        try {
            SecretKey secretKey = generateKey();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8))),
                    StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    /**
     * DES加密文件
     * 
     * @param srcFile 待加密的文件
     * @param destFile 加密后存放的文件路径
     * @return 加密后的文件路径
     */
    public static String encryptFile(String srcFile, String destFile) {
        try {
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(), iv);
            InputStream is = new FileInputStream(srcFile);
            OutputStream out = new FileOutputStream(destFile);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
            cis.close();
            is.close();
            out.close();
            return destFile;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * DES解密文件
     * 
     * @param srcFile 已加密的文件
     * @param destFile 解密后存放的文件路径
     * @return 解密后的文件路径
     */
    public static String decryptFile(String srcFile, String destFile) {
        try {
            File file = new File(destFile);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(), iv);
            InputStream is = new FileInputStream(srcFile);
            OutputStream out = new FileOutputStream(destFile);
            CipherOutputStream cos = new CipherOutputStream(out, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = is.read(buffer)) >= 0) {
                cos.write(buffer, 0, r);
            }
            cos.close();
            is.close();
            out.close();
            return destFile;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String data =
                "{\"action\":\"users_oauth\",\"api_code\":200,\"api_msg\":null,\"is_reg\":\"0\",\"userId\":29916,\"nickName\":\"史佳男\",\"usernumber\":\"99999999\",\"token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidXNlci1jbGllbnQiXSwiand0LWV4dCI6IkpXVCDmianlsZXkv6Hmga8iLCJzY29wZSI6WyJzZWxlY3QiXSwiZXhwIjoxNTg4MTg5MTYzLCJhdXRob3JpdGllcyI6WyJvYXV0aDIiXSwianRpIjoiMjczODQ4M2EtN2UwMy00MTNmLWE0NjctYjFlYjE0ZTYzYjM3IiwiY2xpZW50X2lkIjoiY2xpZW50MSJ9.uQJArgaElyHJaVbXKQ2aIgzJ4K1I93IVllWlS2hgcUo\",\"user\":{\"anchor_rank_id\":\"1\",\"avatar\":\"\",\"balance\":\"0\",\"balance2\":null,\"gamemoney\":\"0\",\"birthday\":\"\",\"age\":\"\",\"constellation\":\"\",\"devices\":[],\"emotion\":\"\",\"exp\":0,\"exp_show\":0,\"gender\":\"1\",\"haoma\":\"1\",\"hometown_city\":\"\",\"hometown_province\":\"\",\"id\":\"29916\",\"im_sig\":\"\",\"im_uid\":\"\",\"is_follow\":\"0\",\"job\":\"\",\"live_banner\":\"/apis/avatar.php?uid=29916\",\"nickname\":\"史佳男\",\"oauths\":[],\"rank_id\":\"1\",\"shower_rank_id\":\"1\",\"reg_time\":\"-998705\",\"summary\":null,\"switchs\":{\"push_on\":\"1\"},\"ticket\":\"0\",\"total_send_gift\":\"0\",\"total_ticket\":\"0\",\"unique_id\":\"1\",\"viplevel\":\"0\",\"vip_util\":null,\"update_avatar_time\":\"0\",\"is_no_play\":\"0\",\"isblock\":\"0\",\"guizhu\":\"0\",\"guizhu_vailddate\":\"0\",\"yinshen\":\"null\",\"onetoone_open\":\"0\",\"onetoone_money\":\"0\",\"onetoone_voice_money\":\"0\",\"shengao\":\"\",\"character\":\"\",\"hobby\":\"\",\"physique\":null,\"role\":null,\"tizhong\":\"\",\"like_physique\":null,\"like_character\":null,\"want_to\":null,\"onlinetime\":\"-991936\",\"shoufei_type\":null,\"shoufei_price\":null,\"shoufei_time_price\":null,\"radio_intro\":\"\",\"radio_time\":\"0\",\"video_name\":\"\",\"video_cover\":\"\",\"hello_text\":\"\",\"oto_status\":\"0\",\"photo_num\":0,\"video_num\":0,\"onetoone_success_percent\":\"100%\",\"video_address_suffix\":\"\",\"push_video_add\":\"rtmp://rtmppush.34541.cn/live/1?vhost=httppull.34541.cn\",\"push_video_add2\":\"rtmp://rtmppush.34541.cn/live/1?vhost=httppull.34541.cn\",\"beibei_verify\":1,\"fans_num\":0,\"follow_num\":0,\"haoyou_num\":0,\"person_verify\":1,\"push_video_w\":0,\"im_tip\":\"\",\"msg_isread\":\"0\",\"market\":\"0\",\"tuijianrentip\":\"0\",\"tuijianren\":\"\",\"jf_XNB\":\"1\"}}\n";
        String encrypt = "MK2X82eL6jkKbzvlJU1CMR6rcKO+SBhmbPOmFD/2Mxw=";
        // System.out.println(encrypt(data));
        System.out.println(decrypt(encrypt));
    }
}
