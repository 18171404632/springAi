package com.newkey.ai.utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Security;

/**
 * Desc: sm4加密算法工具类
 * <p>
 * Function: sm4加密、解密与加密结果验证可逆算法
 *
 * @author
 * @version 2023/11/16

 * @since 2023/11/16
 */
public class Sm4Util {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ENCODING = "UTF-8";
    public static final String ALGORITHM_NAME = "SM4";
    /**
     * 加密算法/分组加密模式/分组填充方式
     * 定义分组加密模式使用：PKCS7Padding
     **/
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS7Padding";

    /**
     * 加密算法/分组加密模式/分组填充方式
     * 定义分组加密模式使用：PKCS5Padding
     **/
    public static final String ALGORITHM_NAME_CBC_PADDING = "SM4/CBC/PKCS5Padding";

    /**
     * Desc: 生成ECB暗号
     * <p>
     * Condition: ECB模式（电子密码本模式：Electronic codebook）
     * <p>
     * ExeFlow:
     * <p>
     * Notice:
     *
     * @param algorithmName 定义分组加密模式使用
     * @param mode          加密/解密
     * @param key           密钥
     * @return void
     * @throws Exception 异常
     * @refApp uegDubboServiceCaller
     */
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    /**
     * Desc: sm4加密(手动)
     * <p>
     * Condition: 加密模式：ECB（密文长度不固定，会随着被加密字符串长度的变化而变化）
     * <p>
     * ExeFlow:
     * <p>
     * Notice:
     *
     * @param hexKey   16进制密钥（忽略大小写）
     * @param paramStr 待加密字符串
     * @return java.lang.String 返回16进制的加密字符串
     * @throws Exception 异常
     */
    public static String encryptEcb(String appKey, String paramStr) throws Exception {
        byte[] bytes = Base64.encodeBase64(encrypt_Ecb_Padding(appKey.getBytes(StandardCharsets.UTF_8),
                paramStr.getBytes(StandardCharsets.UTF_8)));
        return new String(bytes);
    }

    /**
     * Desc: 加密模式之Ecb
     * <p>
     * Condition:
     * <p>
     * ExeFlow:
     * <p>
     * Notice:
     *
     * @param key  密钥
     * @param data 待加密数据
     * @return byte[] 加密后数据
     * @throws Exception 异常
     */
    public static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * Desc: sm4解密
     * <p>
     * Condition: 解密模式：采用ECB
     * <p>
     * ExeFlow:
     * <p>
     * Notice:
     *
     * @param hexKey     16进制密钥
     * @param cipherText 16进制的加密字符串（忽略大小写）
     * @return java.lang.String 解密后的字符串
     * @throws Exception 异常
     */
    public static String decryptEcb(String hexKey, String cipherText) throws Exception {
        // 用于接收解密后的字符串
        String decryptStr = "";
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // hexString-->byte[]
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] srcData = decrypt_Ecb_Padding(keyData, cipherData);
        // byte[]-->String
        decryptStr = new String(srcData, ENCODING);
        return decryptStr;
    }

    /**
     * Desc: 解密
     * <p>
     * Condition:
     * <p>
     * ExeFlow:
     * <p>
     * Notice:
     *
     * @param key        密钥
     * @param cipherText 待解密数据
     * @return byte[] 解密后数据
     * @throws Exception 异常
     */
    public static byte[] decrypt_Ecb_Padding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }



    public static void main(String[] args) throws Exception {
        String encKey = "4dc90a9ded16427f";
//        String data = "{\"mchntId\":\"000017\",\"mchntMbrId\":\"J04022600000000\",\"refNo\":\"42025501691N\",\"merOrderId\":\"33G1202406200600571208785288\",\"transTime\":\"20241009105211\",\"backUrl\":\"http://********/notify\",\"accountsChild\":[{\"acctNo\":\"J04022600000267\",\"acctNm\":\"李婉婷\",\"userCAmt\":\"1000\",\"otherAmt\":\"0\",\"splitAmount\":\"1000\",\"transSsn\":\"J040226000000002024100911123695783496546\",\"laasSsn\":\"172844355693141805389009526846013199\"}]}";
//        System.out.println("原始数据: \n" + data);
//
//        String encodeData = new String(Base64.encodeBase64(encrypt_Ecb_Padding(encKey.getBytes(StandardCharsets.UTF_8), data.getBytes(StandardCharsets.UTF_8))));
//        System.out.println("加密后数据: \n" + encodeData);
        String encodeData = "HEf27qa6lbeYleR17g8On1F5Bs5OGKWzQUzzfYmcIhQLsXNqgFB3HmptDmWe1wdwlXiIvv0v+q7ix91Q/bly7T9POgR5lThN+rgm2I8hIApgzBb9CvcYRj/eRl6ACyhweCkvHqp4+pi9MHz8SL2UlAqmRsJlwrn6NOy5G2kLG49zgJjt+vLkJrxv22qP9w2sYJC07uXEmqTu5F0/B8NDeVLZ30MZ9ZhvTZgiVhoWg3lRjOpIEufR30WdpZm65EgJVZmxWCH2dK6EEk+QUt0fj4ary6JIGTQUWELFsWGGrnqhZahNBHxr1Ya9YtTfvF4WUht1rh3OL2fiT92amhUxO90tqeN2q5sbiBprHPbnVPF3Q8suvzBTx4TMRlkS+FdihSgRfOaPAR8ZGO6hz8T+9fvRc+mUyFj93naGyY9OUQ+GYp2Mj1T0Mhqm8NEOp2b5";
        String decodedData = new String(Sm4Util.decrypt_Ecb_Padding(encKey.getBytes(StandardCharsets.UTF_8), Base64.decodeBase64(encodeData)));
        System.out.println("解密后数据: \n" + decodedData);
    }
}
