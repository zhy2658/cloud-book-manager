package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "interceptorconfig.path") // 配置文件的前缀
public class InterceptorPathBean
{
    /*
     * 需要拦截的路径
     */
    private List<String> openPaths;

    /*
     * 需要拦截的路径
     */
    private List<String> userPaths;


    /*
     * 需要拦截的路径
     */
    private List<String> adminPaths;
}
