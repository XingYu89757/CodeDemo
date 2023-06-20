package com.iric.util;

import cn.hutool.core.codec.Base64;

import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

public class RSATest {

    public static void main(String[] args) throws Exception {
        test();
        // 使用公钥、私钥对象加解密
        List<Key> keyList = RSAUtils.getRSAKeyObject(1024);
        RSAPublicKey puk = (RSAPublicKey) keyList.get(0);
        RSAPrivateKey prk = (RSAPrivateKey) keyList.get(1);
        String message = "referrer:[http://www.example.com/start.html](http://www.example.com/start.htmluser_agent: Mozilla/4.08 [en] (Win98; I ;Nav)";
        String encryptedMsg = RSAUtils.encryptByPublicKey(message, puk);
        String decryptedMsg = RSAUtils.decryptByPrivateKey(encryptedMsg, prk);
        System.out.println("object key ! message ==  decryptedMsg ? " + message.equals(decryptedMsg));

        // 使用字符串生成公钥、私钥完成加解密
        List<String> keyStringList = RSAUtils.getRSAKeyString(1024);
        String pukString = keyStringList.get(0);
        String prkString = keyStringList.get(1);
        System.out.println("公钥:" + pukString);
        System.out.println("私钥:" + prkString);
        // 生成公钥、私钥
        puk = RSAUtils.getPublicKey(pukString);
        prk = RSAUtils.getPrivateKey(prkString);
        encryptedMsg = RSAUtils.encryptByPublicKey(message, puk);
        decryptedMsg = RSAUtils.decryptByPrivateKey(encryptedMsg, prk);
        System.out.println("string key ! message ==  decryptedMsg ? " + message.equals(decryptedMsg));

    }

    public static String test() throws Exception {
          String PrivateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAg1WnqRcocbYMm3HZbZHobEDhozZUwDJ5z/9Z1K0/KenAq+HtCXK3E0s9gPKvvHmFyb2KDwkgknmv8Q0E9FdVqQIDAQABAkBrRE9OzMpMrYr4fvE+C7jQieGAo4RvAgr5ti6RhPqc6NCRDXVpVAmfXcjikNSqe9nzMn45wFzvDZ2V1mrki0JdAiEAyUAwtKwtaw8KnCs5Go3fAc2I5vjcq+OOq8iGhdZ3zXsCIQCnEEH8/mZ1v3iyIToyOlU5Ayv6fPHhLhrQy6HdM2nWKwIhAKarpqq6vZKgjmTv1wcK39oaYnsHN5qThyD1nZNSHz3pAiA/9+OsgaSt1WDbpAlqjhdvux0tzoW3WTX/WNljn3oY/QIgAnd/JY57+XH3KbYqFoOYR+I3bKO3t2B9T2kWQJELejQ=";
          String PublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAINVp6kXKHG2DJtx2W2R6GxA4aM2VMAyec//WdStPynpwKvh7QlytxNLPYDyr7x5hcm9ig8JIJJ5r/ENBPRXVakCAwEAAQ==";

        String name = "huzhishuang";
        String encode = Base64.encode(name);
        RSAPublicKey publicKey = RSAUtils.getPublicKey(PublicKey);
        String encryptedMsg = RSAUtils.encryptByPublicKey(encode, publicKey);
        System.out.println(encryptedMsg);
        return encryptedMsg;

    }
}