package top.tangyh.lamp.msg.strategy.impl.mail;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import top.tangyh.basic.exception.BizException;
import top.tangyh.lamp.msg.entity.DefMsgTemplate;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.entity.ExtendMsgRecipient;
import top.tangyh.lamp.msg.strategy.MsgStrategy;
import top.tangyh.lamp.msg.strategy.domain.MsgParam;
import top.tangyh.lamp.msg.strategy.domain.MsgResult;
import top.tangyh.lamp.msg.strategy.domain.mail.TencentMailProperty;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 腾讯邮件
 *
 * @author zuihou
 * @date 2022/7/10 0010 17:40
 */
@Slf4j
@Service("tencentMailMsgStrategyImpl")
public class TencentMailMsgStrategyImpl implements MsgStrategy {

    @Override
    public MsgResult exec(MsgParam msgParam) {
        ExtendMsg extendMsg = msgParam.getExtendMsg();
        List<ExtendMsgRecipient> recipientList = msgParam.getRecipientList();
        DefMsgTemplate extendMsgTemplate = msgParam.getExtendMsgTemplate();
        Map<String, Object> propertyParams = msgParam.getPropertyParams();

        List<File> tempFileList = new ArrayList<>();
        try {
            TencentMailProperty property = new TencentMailProperty();
            BeanUtil.fillBeanWithMap(propertyParams, property, true);
            if (property.getDebug() != null && property.getDebug()) {
                return MsgResult.builder().result("debug模式无需发送").build();
            }
            MsgResult msgResult = replaceVariable(extendMsg, extendMsgTemplate);
            HtmlEmail email = buildEmail(msgResult, recipientList, property, tempFileList);

            return msgResult.setResult(email.send());
        } catch (Exception e) {
            log.warn("邮件发送失败:", e);
            throw new BizException(e.getMessage());
        } finally {
            for (File file : tempFileList) {
                try {
                    log.info("delete temp file : {}", file.getAbsoluteFile());
                    FileUtils.forceDelete(file);
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    private HtmlEmail buildEmail(MsgResult msgResult, List<ExtendMsgRecipient> recipientList,
                                 TencentMailProperty property, List<File> tempFileList)
            throws EmailException {
        HtmlEmail htmlEmail = new HtmlEmail();

        htmlEmail.setHostName(property.getHostName());
        if (property.getSsl() != null && property.getSsl()) {
            htmlEmail.setSSLOnConnect(true);
            if (StrUtil.isNotEmpty(property.getSmtpPort())) {
                htmlEmail.setSslSmtpPort(property.getSmtpPort());
            }
        } else {
            if (StrUtil.isNotEmpty(property.getSmtpPort())) {
                htmlEmail.setSmtpPort(Integer.parseInt(property.getSmtpPort()));
            }
        }

        htmlEmail.setAuthentication(property.getUsername(), property.getPassword());
        if (property.getDebug() != null) {
            htmlEmail.setDebug(property.getDebug());
        }
        htmlEmail.setCharset(StrUtil.isNotEmpty(property.getCharset()) ? property.getCharset() : "UTF-8");
        if (StrUtil.isNotEmpty(property.getFromName()) && StrUtil.isNotEmpty(property.getFromEmail())) {
            htmlEmail.setFrom(property.getFromEmail(), property.getFromName());
        }

        for (ExtendMsgRecipient recipient : recipientList) {
            if (StrUtil.isNotEmpty(recipient.getExt())) {
                htmlEmail.addTo(recipient.getRecipient(), recipient.getExt());
            } else {
                htmlEmail.addTo(recipient.getRecipient());
            }
        }

        htmlEmail.setSubject(msgResult.getTitle());
        htmlEmail.setHtmlMsg(msgResult.getContent());

        // 附件

        return htmlEmail;
    }
}
