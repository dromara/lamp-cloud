import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import cn.hutool.core.io.FileUtil;

/**
 * 公钥私钥生成 工具类
 *
 * @author zuihou
 * @date 2019/07/31
 */
public class RsaKeyHelperTest {
    /***
     * 生成自己的 秘钥/公钥 对
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //自定义 随机密码,  请修改这里
        String password = "zuihou!@#$%^&*()_+";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        FileUtil.writeBytes(publicKeyBytes, "/Users/zuihou/githubspace/zuihou-admin-cloud/zuihou-commons/zuihou-jwt-starter/src/main/resources/pub.key");
        FileUtil.writeBytes(privateKeyBytes, "/Users/zuihou/githubspace/zuihou-admin-cloud/zuihou-commons/zuihou-jwt-starter/src/main/resources/pri.key");
    }
}
