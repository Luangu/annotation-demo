package com.demo.tools;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密解密工具
 */
class AESUtils {

    private static final String CIPER_MODE = "AES/ECB/NoPadding";

    /**
     * AES加密
     *
     * @param data 将要加密的内容
     * @param key 密钥
     * @return 已经加密的内容
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        // 不足16字节，补齐内容为差值
        int len = 16 - data.length % 16;
        for (int i = 0; i < len; i++) {
            byte[] bytes = {(byte) len};
            data = ArrayUtils.concat(data, bytes);
        }
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance(CIPER_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[] {};
    }

    /**
     * AES解密
     *
     * @param data 将要解密的内容
     * @param key 密钥
     * @return 已经解密的内容
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        data = ArrayUtils.noPadding(data, -1);
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance(CIPER_MODE);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decryptData = cipher.doFinal(data);
            int len = 2 + ByteUtils.byteToInt(decryptData[4]) + 3;
            return ArrayUtils.noPadding(decryptData, len);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[] {};
    }

    public static void main(String[] args) {
        String key = "MK2X82eL6jkKbzvlJU1CMR6rcKO+SBhmbPOmFD/2Mxw=";
        String key2 = "MK2X82eL6jkKbzvlJU1CMR6rcKO+SBhmbPOmFD/2Mxw=";
        String s =
                "7Dx6JcWOyYbHcq5sDnXduzJp+DGZzt3uHFOqyOpmTeL5olEy6gXO95zRMWAYwBb0Mu4XqhB0lPGzUPZZBFRWH/GSJvDPxQEhV8Oka/RTqAKf2SBl23al1sFcZl1jVYQrL1WHDFKr3kq0PXdLjYDvM8sFj/9DSNJifDis4EJPpuiGbbim0m2ZCzd7rD+6Qqj0+vRJiZsQkC/DSte4VfjJnzPn1OM9cVv/IaoMTXc7W/Fam5NL6Oab5mrcWrIfB24hh2KrlfEs4+iKHMXwYWeew4Xpb1Q8tsqcaRoiP1IGqiZYa2KhvmRKWTzvLvMJ08QiHwez2d/iySlYQfj8P7ARR/P1N40UNbMJOofiA6u4oPNHwaFuIwYDiTLTwFwUlAVXt+5Ol8fkS2DsDUcD3v90THUpOtsqiD+2wsf/mBUXixgSw2+mTnuv5Ak7PUJBL/P7Ccgr/vOZUv+WjW5l01hsPA3krWyjQiWQlCJf5h4nmMuthFt0hOtMbYw73MTdPnAfPWwWdv6dtX9mMTi6n+N5epXZaMs36/D5hU6Nyt31txLnEV/iYzOpWqpEtSspyw7gjixrKsK2Je3u0GQ8jrwruOcRX+JjM6laqkS1KynLDuDk1YCQH3NMnSDAeTG7Pc6g4xCUiLMsyEYomXaIrZcm4w==";
        String s2 = "7Dx6JcWOyYbHcq5sDnXdu3aNPth+toiae16LdJycBQeK4XW3Ts7eSO7ra97am8peZYswChPqCDZHa9T9t8p1X+FOnJsu6lLlscv5GoNddBC3aT4UQiB/oMZfMgHcYU1Aregd5g72qxPPnX9WTP62/IFUPa4DYSecAZ5rNasOwEFFtxKIo2JmtxaluLQPFgeN9w9TQFhk+eYXpLsylEoi9Ia7J0RNpchA+T6YJo4QPJ5FuHi/tOZTqAwD9yrXse4/BZW7srnRJF0DBYvqWJt2sB7Y0/IWSmoORgqNGI9vDKazbc7va3ZzARkPHw0lC66DkmgvQ4fAvxK78E6PgBf0WXl9enMO66Xg3PDaTA4ho1VVFjt2nDS1wV5jct/sc1CR8vjv6cGRlL/DL4iVg9Eir4FUPa4DYSecAZ5rNasOwEGw6bQ8FnvgcIQk/Q/YIv7GLLYuCVdOgpg4sHbY0CKC3Xx8Sy4FfxT2gOjioTcwXkX6qLZx8J+0wA+jWWjQqreJoGzqP52JEtG7snlm5c1NWDJtI7VWb+ueNN1ummW0s/ydXtPDACMBg1Own4kDXkboy/wI/RZnHNokn02JekyZEEDh6CVYdUKV/d6O4AH5OOi9RnYDqEreKIiD4Z6afDLVm4Hybx8/jFn1jNP+tjw7A3MGc2ZftwlZ3DjE6hQJvuQaFuyMHvYMCSoTtVQKTQlJtwCJH6u/MHbC6Ce1gSqKguY8x9qHUts8C9CJt7jZDByyT1B7EYTPK3g5sz4JMbnC96wRKbtxOpo5BrX/nw83bPcuDHt1fqIbG8j+7tlsl+/wOkoWJZPdmaMmZgeqqpjsmX54Uom4lsggMHZgsEVlS+yt80446Tl+7TIN0i9TMHUKT9pegQhPyQ5HpsbE+oC4foo49rTJvCcHjcOxwiwpmnUpOtsqiD+2wsf/mBUXixgSw2+mTnuv5Ak7PUJBL/P7Ccgr/vOZUv+WjW5l01hsPA3krWyjQiWQlCJf5h4nmMtLNeEkDY9yKrQzwmcUL7ShVrLfKQRghVPrMAPiULAM/P3LR3fcYw40wzQA88F9Bbo9bBZ2/p21f2YxOLqf43l6vV766FGop7Gom0wuavFOQ0vPp7DP+DnIxRfV519d97L8hdPVT7Hm+x0vLACnXGvtlLYU8IKJ9Z1fZxsur/gtYzBSs0j2hi+huVJeD2DPVKGYyrCsLne+hpePB7lXoYMBNUZfFybCzdFtOXJkSR8F8XUpOtsqiD+2wsf/mBUXixgSw2+mTnuv5Ak7PUJBL/P7vt0hGwoxb0jJyaqhmhnvJw==";
        // byte[] decrypt = decrypt(Base64.decodeBase64(s), Base64.decodeBase64(key));
        byte[] decrypt = decrypt(Base64.decodeBase64(s2), Base64.decodeBase64(key2));
        String data = new String(decrypt);
        System.out.println(data);
    }
}
