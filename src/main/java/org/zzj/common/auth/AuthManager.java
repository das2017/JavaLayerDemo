package org.zzj.common.auth;

import lombok.extern.slf4j.Slf4j;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import java.util.UUID;

@Slf4j
public class AuthManager {
    //keyId
    final static String keyId = "4a34e50da1134fb2886feed34d0b64f2";
    //公钥（publicKey）
    final static String publicKey = "{\"kty\":\"RSA\",\"kid\":\"4a34e50da1134fb2886feed34d0b64f2\",\"alg\":\"ES256\",\"n\":\"hSurBVhvBFsrum-WIfDpm5BQNvHgJzmtypZh4tLRlIwVtGZjI4TC8yVhnhTrtzZ6XVAbGeWWsJuoiqBZL6uNgdBIa8D20Msi-xo1ogH_stG28A4wvEVXsmLNzNXNc40kPsY_LV1eJUYkMa-u5lJrbpVmC04w7z6XBEL5yuKHZEHtw5uNIt53-WUEs5Ps5odeme80jDlEY5QngBB_u8yaXFJVc5KfouY_DIsPiTOHAeKUZOI0A-mEYBEIzIe9f0B6fiKQZDa7Crnda2IUUUHCVZ5d68L82ke6FRs1iuJ6aET4p2Y10PYPCQp2_WwRWS5eCDLmMRBlsvlyfPFq8LeQ4Q\",\"e\":\"AQAB\"}";
    //私钥
    final static String privateKey = "{\"kty\":\"RSA\",\"kid\":\"4a34e50da1134fb2886feed34d0b64f2\",\"alg\":\"ES256\",\"n\":\"hSurBVhvBFsrum-WIfDpm5BQNvHgJzmtypZh4tLRlIwVtGZjI4TC8yVhnhTrtzZ6XVAbGeWWsJuoiqBZL6uNgdBIa8D20Msi-xo1ogH_stG28A4wvEVXsmLNzNXNc40kPsY_LV1eJUYkMa-u5lJrbpVmC04w7z6XBEL5yuKHZEHtw5uNIt53-WUEs5Ps5odeme80jDlEY5QngBB_u8yaXFJVc5KfouY_DIsPiTOHAeKUZOI0A-mEYBEIzIe9f0B6fiKQZDa7Crnda2IUUUHCVZ5d68L82ke6FRs1iuJ6aET4p2Y10PYPCQp2_WwRWS5eCDLmMRBlsvlyfPFq8LeQ4Q\",\"e\":\"AQAB\",\"d\":\"YIo1otxnLstBOhimx9g0IhhObmaOTyFbQeAe-iZG1N9w5KTM_fTCty3ERKt57gnKT1dBLlboFQPi_QwdW5eMMC1kMulihB6fL1Xix52XVnT9n61J21KRrro9416jYD9TpUZmimaDV5Yvqu1nZ4UcaMBZh5yZkPbY17gOCeyKpjytWs5C3_eOlPff9tBOMR6-Ra_BvWxRVBR-En3Nn3Kv64i5K991dfMJ3ywA4e6Y6EA_ygTCEkQzqAAz6rvCmcQYw3xrsGYepUgxfg4zzC4xUvZvPVdoO9b7isdycmn6p3xk1Dtd4M42-Uy_xCNcYfWMw60BK_h_htnGan_LWuxggQ\",\"p\":\"3CeN2KRhRQnq9iYYxr8w8ApfS2y4WsMiExgGSyXn-Z36Lz8EFsp2v1w4gKGNBc9QknsTTF5-KCrpLm1oIJ_6w1Jd4nW7KkwuCBn3fAhpTMlO8hjhR8A3Pr3P9vvzq-1lSC5BMWiK4c7VCJNcoAapM3c44nMIoKEVJUYSm5TL5Ik\",\"q\":\"mtp2niRcgqXQ010J1m5Oy2OHStmz5J0KiVxC-fFf595QtaPCB3P7whqzI3Y1BZAbDeVB7IL_SALUUAl-ZbK3wGBiaU0f3y4iW5VVKSzIeuMYnCMGO1yak9ZxT9ACyqK1oxXQnRoKFHnYs_kVE3jPYaDn4ji5gGICZ6Mke2TjY5k\",\"dp\":\"LRudoKntlCu4DL85jF9YM5asd9PK4i3hKXBeub8GjMqlnFCZEeJh-kt2-EQhlPMpWxoElj02NBy2smCQWT7uLl8OBXyB4OTC-b34S12d1bHZjxkx73We3CggGEb_Yla9zdBMY0c8TZAHbj5jWBaFTJo8-YkAV4kpv_1fiqkuLaE\",\"dq\":\"SmuDZi0DaKZX3bxw5eTNaWHDu200z0HJtonohkZCWSS2lRTGQt6yt24teXVtteazmdH8BLZQCUOS1YQ6gpWJSaMIqKKwFooNaQYzjz4-exB421DZxd2TTEla24iUTt4zfNUXFOlWvtBwHM4wGtOMltbFmiq4u8JedYAqIEM0JME\",\"qi\":\"V66cJSeQNmhpl7MlZjatdEgMSlAZz0lXgfk8uztrscBY_o1yKvbhBOtZXMaOCWG2_YILDnx3CC9IaMj6NZtBznifRXuIBy23sm5fwvgvj18LyGb5wpsN2Ax0dAIYxVAbLz2EiJgdREYtnIGPxaA6GEFbweUuGhy6FISaSOdjbEo\"}";

    final static String UID = "UID";

    public static void generateKeyPair() throws Exception {
        String keyId = UUID.randomUUID().toString().replaceAll("-", "");
        RsaJsonWebKey jwk = RsaJwkGenerator.generateJwk(2048);
        jwk.setKeyId(keyId);
        jwk.setAlgorithm(AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256);
        String publicKey = jwk.toJson(RsaJsonWebKey.OutputControlLevel.PUBLIC_ONLY);
        String privateKey = jwk.toJson(RsaJsonWebKey.OutputControlLevel.INCLUDE_PRIVATE);
        System.out.println(keyId);
        System.out.println(publicKey);
        System.out.println(privateKey);
    }

    public static String generateToken(String uid) {
        //参考文档：https://help.aliyun.com/document_detail/48019.html
        //通过 keyId, Claims, privateKey 与使用的数字签名算法 (RSA SHA256)生成 JWS(Json Web Signature)
        try {
            RsaJsonWebKey rsaJsonWebKey = new RsaJsonWebKey(JsonUtil.parseJson(privateKey));

            JsonWebSignature jws = new JsonWebSignature();
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
            jws.setKeyIdHeaderValue(keyId);
            jws.setKey(rsaJsonWebKey.getPrivateKey());

            JwtClaims claims = new JwtClaims();
            claims.setGeneratedJwtId();
            claims.setIssuedAtToNow();
            //expire time 过期时间为5天
            claims.setExpirationTimeMinutesInTheFuture(60 * 24 * 5);
            claims.setNotBeforeMinutesInThePast(1);
            claims.setIssuer("opendid_connect");
            claims.setAudience("opendid");
            //添加自定义参数
            claims.setClaim(UID, uid);
            jws.setPayload(claims.toJson());

            return jws.getCompactSerialization();
        } catch (Exception e) {
            log.error("generateToken错误：{}", e);
            return null;
        }
    }

    public static String getUid(String token) {
        try {
            RsaJsonWebKey rsaJsonWebKey = new RsaJsonWebKey(JsonUtil.parseJson(privateKey));

            JwtConsumer consumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime()
                    .setMaxFutureValidityInMinutes(60 * 24 * 5)
                    .setAllowedClockSkewInSeconds(60)
                    .setExpectedIssuer("opendid_connect")
                    .setExpectedAudience("opendid")
                    .setVerificationKey(rsaJsonWebKey.getPublicKey())
                    .build();
            //验证签名
            JwtClaims claims = consumer.processToClaims(token);
            return claims.getStringClaimValue(UID);
        } catch (Exception e)   {
            log.error("getUid错误：{}", e);
            return null;
        }
    }

    public static boolean verify(String token) {
        String uid = getUid(token);
        if (uid != null && uid != "") {
            //已验证签名，可以不再验证数据库。
            return true;
        }
        return false;
    }

    public static void main (String[]args) throws Exception {
        //generateKeyPair();
        System.out.println(privateKey);

        String token = AuthManager.generateToken("zhq");
        System.out.println(token);

        String uid = AuthManager.getUid(token);
        System.out.println(uid);
    }
}
