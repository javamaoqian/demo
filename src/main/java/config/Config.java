package config;

import beans.Bean;
import beans.LinuxBean;
import condition.WindowsCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: mq
 * @Date: 2018/9/14 11:59
 */
@Configuration
public class Config {
    @Conditional(WindowsCondition.class)
    @org.springframework.context.annotation.Bean
    public Bean getLinuxBean() {
        return new LinuxBean();
    }
}
