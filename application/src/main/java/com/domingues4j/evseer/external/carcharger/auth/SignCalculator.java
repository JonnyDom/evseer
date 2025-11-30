package com.domingues4j.evseer.external.carcharger.auth;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;

public class SignCalculator {

    public static String calcSign(String clientId, long timestamp, String nonce, String signStr, String secret) {
        // Concatenate the input strings
        String str = clientId + timestamp + nonce + signStr;

        // Generate HMAC-SHA256 hash using HmacUtils
        byte[] hash = HmacUtils.hmacSha256(secret, str);

        // Encode the hash to Base64 and return in uppercase
        return Base64.encodeBase64String(hash).toUpperCase();
    }
}
