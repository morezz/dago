package dago.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * CommonConfigBean
 *
 * @author <a href="mailto:jzchen@coremail.cn">jzchen</a>
 */
public final class CommonConfigBean extends PropertyPlaceholderConfigurer {

    private static Properties ctxProperties;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxProperties = props;
    }

    public static String getContextProperty(String key) {
        return ctxProperties.getProperty(key);
    }

    public static Integer getContextPropertyInt(String key) {
        return Integer.valueOf(ctxProperties.getProperty(key));
    }

    public static String getStringProperty(String key, String defaultValue) {
        return ctxProperties.getProperty(key, defaultValue);
    }

    public static Integer getIntProperty(String key, int defaultValue) {
        return Integer.valueOf(ctxProperties.getProperty(key, String.valueOf(defaultValue)));
    }

}
