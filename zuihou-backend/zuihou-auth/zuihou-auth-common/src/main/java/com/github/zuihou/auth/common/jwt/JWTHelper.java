package com.github.zuihou.auth.common.jwt;


import com.github.zuihou.commons.context.CommonConstants;
import com.github.zuihou.commons.exception.core.ExceptionCode;
import com.github.zuihou.commons.utils.StringHelper;
import com.github.zuihou.exception.BizException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by ace on 2017/9/10.
 */
public class JWTHelper {

    private static final Logger log = LoggerFactory.getLogger(JWTHelper.class);
    private static final RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    /**
     * 密钥加密token
     *
     * @param jwtInfo    jwt 帐号信息
     * @param priKeyPath 私钥地址
     * @param expire     过期时间
     * @return
     * @throws Exception
     */
    public static TokenVo generateToken(IJWTInfo jwtInfo, String priKeyPath, int expire) throws BizException {
        try {
            String compactJws =
                    //返回的字符串便是我们的jwt串了
                    Jwts.builder()
                            //设置主题
                            .setSubject(jwtInfo.getUserName())
                            .claim(CommonConstants.JWT_KEY_ADMIN_ID, jwtInfo.getAdminId())
                            .claim(CommonConstants.JWT_KEY_NAME, jwtInfo.getName())
                            .claim(CommonConstants.JWT_KEY_APP_ID, jwtInfo.getAppId())
                            .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                            //设置算法（必须）
                            .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKeyPath))
                            //这个是全部设置完成后拼成jwt串的方法
                            .compact();
            return new TokenVo(compactJws, expire);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("message:", e);
            throw new BizException(ExceptionCode.JWT_GEN_TOKEN_FAIL.getCode(), ExceptionCode.JWT_GEN_TOKEN_FAIL.getMsg());
        }
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @param pubKeyPath 公钥路径
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, String pubKeyPath) throws BizException {
        try {
            return Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath)).parseClaimsJws(token);
        } catch (ExpiredJwtException ex) {
            log.error("ExpiredJwtException:", ex);
            //过期
            throw new BizException(ExceptionCode.JWT_TOKEN_EXPIRED.getCode(), ExceptionCode.JWT_TOKEN_EXPIRED.getMsg());
        } catch (SignatureException ex) {
            log.error("SignatureException:", ex);
            //签名错误
            throw new BizException(ExceptionCode.JWT_SIGNATURE.getCode(), ExceptionCode.JWT_SIGNATURE.getMsg());
        } catch (IllegalArgumentException ex) {
            log.error("IllegalArgumentException:", ex);
            //token 为空
            throw new BizException(ExceptionCode.JWT_ILLEGAL_ARGUMENT.getCode(), ExceptionCode.JWT_ILLEGAL_ARGUMENT.getMsg());
        } catch (Exception e) {
            log.error("message:", e);
            throw new BizException(ExceptionCode.JWT_PARSER_TOKEN_FAIL.getCode(), ExceptionCode.JWT_PARSER_TOKEN_FAIL.getMsg());
        }
    }

    /**
     * 获取token中的用户信息
     * get("userId") -> userId
     * get("name") -> userName
     * getSubject() -> uniqueName
     *
     * @param token      token
     * @param pubKeyPath 公钥路径
     * @return
     * @throws Exception
     */
    public static IJWTInfo getInfoFromToken(String token, String pubKeyPath) throws BizException {
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();
        String adminId = StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_ADMIN_ID));
        Long aId = -1L;
        try {
            aId = adminId == null || adminId.isEmpty() ? -1L : Long.valueOf(adminId);
        } catch (Exception e) {
            log.error("message:", e);
        }
        return new JWTInfo(body.getSubject(),
                aId,
                StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_NAME)),
                StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_APP_ID))
        );
    }

}
