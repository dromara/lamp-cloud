package com.github.shiro.autoconfigure;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * Created by whf on 3/20/16.
 *
 * @author zuihou
 * @date 2019-07-23 11:59
 */
public class StatelessSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        //context.setSessionCreationEnabled(false);// 不创建session
        return super.createSubject(context);
    }
}
