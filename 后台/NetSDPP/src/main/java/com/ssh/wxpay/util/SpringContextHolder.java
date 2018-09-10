package com.ssh.wxpay.util;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Hyman on 2016/4/25.
 */
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    /**
     * ȡ�ô洢�ھ�̬�����е�ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * �Ӿ�̬����applicationContext��ȡ��Bean, �Զ�ת��Ϊ����ֵ���������.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * �Ӿ�̬����applicationContext��ȡ��Bean, �Զ�ת��Ϊ����ֵ���������.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * ���SpringContextHolder�е�ApplicationContextΪNull.
     */
    public static void clearHolder() {
        logger.debug("���SpringContextHolder�е�ApplicationContext:"
                + applicationContext);
        applicationContext = null;
    }

    /**
     * ʵ��ApplicationContextAware�ӿ�, ע��Context����̬������.
     */
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {

    }

    /**
     * ʵ��DisposableBean�ӿ�, ��Context�ر�ʱ����̬����.
     */
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    /**
     * ���ApplicationContext��Ϊ��.
     */
    private static void assertContextInjected() {
        Validate.validState(applicationContext == null, "applicationContextΪ�գ�����applicationContext.xml����SpringApplicationContext");
    }

}

