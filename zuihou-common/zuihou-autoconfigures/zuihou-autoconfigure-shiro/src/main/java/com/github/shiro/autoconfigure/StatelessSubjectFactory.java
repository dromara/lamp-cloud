package com.github.shiro.autoconfigure;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * Created by whf on 3/20/16.
 */
public class StatelessSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        System.out.println("-------createSubject-----");
        // 不创建session
        //context.setSessionCreationEnabled(false);

        return super.createSubject(context);
    }
}
