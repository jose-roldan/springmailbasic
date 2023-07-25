/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.egomusic.springmailbasic01.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author jose.roldan
 */
@Configuration
public class CorsConfig extends GenericFilterBean implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chaine)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "*");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "false");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        chaine.doFilter(request, response);
    }

    @Bean
    public FilterRegistrationBean crosFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CorsConfig());
        registrationBean.setName("CORS Filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
