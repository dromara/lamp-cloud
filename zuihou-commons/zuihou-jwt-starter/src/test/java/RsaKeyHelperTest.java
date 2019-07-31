import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import cn.hutool.core.io.FileUtil;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/07/31
 */
public class RsaKeyHelperTest {


    /***
     * 自己 秘钥/公钥 对
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //自定义 随机密码
        String password = "zuihou!@#$%^&*()_+";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        FileUtil.writeBytes(publicKeyBytes, "/Users/tangyh/githubspace/zuihou-admin-cloud/zuihou-commons/zuihou-jwt-starter/src/main/resources/pub.key");
        FileUtil.writeBytes(privateKeyBytes, "/Users/tangyh/githubspace/zuihou-admin-cloud/zuihou-commons/zuihou-jwt-starter/src/main/resources/pri.key");
    }
}
