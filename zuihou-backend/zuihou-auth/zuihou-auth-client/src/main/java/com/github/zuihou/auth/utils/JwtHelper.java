//package com.github.zuihou.auth.utils;
//
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//import java.time.LocalDateTime;
//
//
//import com.github.zuihou.commons.exception.core.ExceptionCode;
//import com.github.zuihou.commons.utils.StringHelper;
//import com.github.zuihou.exception.BizException;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.StringUtils;
//
///**
// * Created by ace on 2017/9/10.
// *
// * @author tangyh
// * @date 2019-06-20 22:20
// */
//@Slf4j
//public class JwtHelper {
//
//    private static final RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();
//
//
//    /**
//     * 生成用户token
//     *
//     * @param jwtInfo
//     * @param priKeyPath
//     * @param expire
//     * @return
//     * @throws BizException
//     */
//    public static Token generateUserToken(JwtUserInfo jwtInfo, String priKeyPath, int expire) throws BizException {
//        JwtBuilder jwtBuilder = Jwts.builder()
//                //设置主题
//                .setSubject(String.valueOf(jwtInfo.getAId()))
//                .claim(BaseContextConstants.JWT_USER_KEY_ACCOUNT, jwtInfo.getAct())
//                .claim(BaseContextConstants.JWT_USER_KEY_ACCOUNT_NAME, jwtInfo.getAN())
//                .claim(BaseContextConstants.JWT_USER_KEY_LOGIN_FLAG, jwtInfo.getLg())
//                .claim(BaseContextConstants.JWT_USER_KEY_ORGID, jwtInfo.getGId())
//                .claim(BaseContextConstants.JWT_USER_KEY_ORGNAME, jwtInfo.getGN())
//                .claim(BaseContextConstants.JWT_USER_KEY_DEPARTMENTID, jwtInfo.getDId())
//                .claim(BaseContextConstants.JWT_USER_KEY_DEPARTMENTNAME, jwtInfo.getDN())
//                .claim(BaseContextConstants.JWT_USER_KEY_ENTITYID, jwtInfo.getEId())
//                .claim(BaseContextConstants.JWT_USER_KEY_ACCOUNT_TYPE, jwtInfo.getAT())
//                .claim(BaseContextConstants.JWT_USER_KEY_ACCOUNT_LOGIN_VERSION, jwtInfo.getLv());
//        return generateToken(jwtBuilder, priKeyPath, expire);
//    }
//
//    /**
//     * 获取token中的用户信息
//     *
//     * @param token      token
//     * @param pubKeyPath 公钥路径
//     * @return
//     * @throws Exception
//     */
//    public static JwtUserInfo getUserInfoFromToken(String token, String pubKeyPath) throws BizException {
//        Jws<Claims> claimsJws = parserUserToken(token, pubKeyPath);
//        Claims body = claimsJws.getBody();
//        String strUserId = body.getSubject();
//        String account = StringHelper.getObjectValue(body.get(BaseContextConstants.JWT_USER_KEY_ACCOUNT));
//        String name = StringHelper.getObjectValue(body.get(BaseContextConstants.JWT_USER_KEY_ACCOUNT_NAME));
//        String strLoginFlag = StringHelper.getObjectValue(body.get(BaseContextConstants.JWT_USER_KEY_LOGIN_FLAG));
//
//        String orgId = StringHelper.getObjectValue(body.get(BaseContextConstants.JWT_USER_KEY_ORGID));
//        String orgName = StringHelper.getObjectValue(body.get(BaseContextConstants.JWT_USER_KEY_ORGNAME));
//        String departmentId = StringHelper.getObjectValue(body.get(BaseContextConstants.JWT_USER_KEY_DEPARTMENTID));
//        String departmentName = StringHelper.getObjectValue(body.get(BaseContextConstants.JWT_USER_KEY_DEPARTMENTNAME));
//
//        String entityId = StringHelper.getObjectValue(body.get(BaseContextConstants.JWT_USER_KEY_ENTITYID));
//        String accountType = StringHelper.getObjectValue(body.get(BaseContextConstants.JWT_USER_KEY_ACCOUNT_TYPE));
//        String loginVersion = StringHelper.getObjectValue(body.get(BaseContextConstants.JWT_USER_KEY_ACCOUNT_LOGIN_VERSION));
//        Long userId = null;
//        Integer loginFlag = null;
//        try {
//            userId = Long.parseLong(strUserId);
//        } catch (Exception e) {
//            log.info("解析jwt中用户id出错", e);
//        }
//
//        try {
//            loginFlag = Integer.parseInt(strLoginFlag);
//        } catch (Exception e) {
//            log.info("解析jwt中登录标识出错", e);
//        }
//        Integer loginVersionInt = null;
//        try {
//            if (!StringUtils.isEmpty(loginVersion)) {
//                loginVersionInt = Integer.parseInt(loginVersion);
//            }
//        } catch (Exception e) {
//            log.info("解析jwt中登录标识出错", e);
//        }
//        return new JwtUserInfo(userId, account, name, loginFlag, orgId, orgName, departmentId, departmentName, accountType, entityId, loginVersionInt);
//    }
//
//
//    /**
//     * 生成token
//     *
//     * @param builder
//     * @param priKeyPath
//     * @param expire
//     * @return
//     * @throws BizException
//     */
//    protected static Token generateToken(JwtBuilder builder, String priKeyPath, int expire) throws BizException {
//        try {
//            //返回的字符串便是我们的jwt串了
//            String compactJws = builder.setExpiration(DateUtils.localDateTime2Date(LocalDateTime.now().plusSeconds(expire)))
//                    //设置算法（必须）
//                    .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKeyPath))
//                    //这个是全部设置完成后拼成jwt串的方法
//                    .compact();
//            return new Token(compactJws, expire);
//        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
//            log.error("errcode:{}, message:{}", ExceptionCode.JWT_GEN_TOKEN_FAIL.getCode(), e.getMessage());
//            throw new BizException(ExceptionCode.JWT_GEN_TOKEN_FAIL.getCode(), ExceptionCode.JWT_GEN_TOKEN_FAIL.getMsg());
//        }
//    }
//
//
//    /**
//     * 公钥解析token
//     *
//     * @param token
//     * @param pubKeyPath 公钥路径
//     * @return
//     * @throws Exception
//     */
//    private static Jws<Claims> parserToken(String token, String pubKeyPath) throws BizException {
//        try {
//            return Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath)).parseClaimsJws(token);
//        } catch (ExpiredJwtException ex) {
//            //过期
//            throw new BizException(ExceptionCode.JWT_TOKEN_EXPIRED.getCode(), ExceptionCode.JWT_TOKEN_EXPIRED.getMsg());
//        } catch (SignatureException ex) {
//            //签名错误
//            throw new BizException(ExceptionCode.JWT_SIGNATURE.getCode(), ExceptionCode.JWT_SIGNATURE.getMsg());
//        } catch (IllegalArgumentException ex) {
//            //token 为空
//            throw new BizException(ExceptionCode.JWT_ILLEGAL_ARGUMENT.getCode(), ExceptionCode.JWT_ILLEGAL_ARGUMENT.getMsg());
//        } catch (Exception e) {
//            log.error("errcode:{}, message:{}", ExceptionCode.JWT_PARSER_TOKEN_FAIL.getCode(), e.getMessage());
//            throw new BizException(ExceptionCode.JWT_PARSER_TOKEN_FAIL.getCode(), ExceptionCode.JWT_PARSER_TOKEN_FAIL.getMsg());
//        }
//    }
//
//    /**
//     * 解析用户token
//     *
//     * @param token
//     * @param pubKeyPath
//     * @return
//     * @throws BizException
//     */
//    private static Jws<Claims> parserUserToken(String token, String pubKeyPath) throws BizException {
//        try {
//            return Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath)).parseClaimsJws(token);
//        } catch (ExpiredJwtException ex) {
//            //过期
//            throw new BizException(ExceptionCode.JWT_USER_TOKEN_EXPIRED.getCode(), ExceptionCode.JWT_USER_TOKEN_EXPIRED.getMsg());
//        } catch (SignatureException ex) {
//            //签名错误
//            throw new BizException(ExceptionCode.JWT_USER_SIGNATURE.getCode(), ExceptionCode.JWT_USER_SIGNATURE.getMsg());
//        } catch (IllegalArgumentException ex) {
//            //token 为空
//            throw new BizException(ExceptionCode.JWT_USER_ILLEGAL_ARGUMENT.getCode(), ExceptionCode.JWT_USER_ILLEGAL_ARGUMENT.getMsg());
//        } catch (Exception e) {
//            log.error("errcode:{}, message:{}", ExceptionCode.JWT_USER_PARSER_TOKEN_FAIL.getCode(), e.getMessage());
//            throw new BizException(ExceptionCode.JWT_USER_PARSER_TOKEN_FAIL.getCode(), ExceptionCode.JWT_USER_PARSER_TOKEN_FAIL.getMsg());
//        }
//    }
//}
