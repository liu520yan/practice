package com.liuyan.practice.config;

import com.liuyan.practice.exception.CaptchaException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码
 * Created by liuyan on 2017/12/7.
 */

public class SecurityConfig extends UsernamePasswordAuthenticationFilter {
    public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "j_captcha";
    public static final String SESSION_GENERATED_CAPTCHA_KEY = "SESSION_GENERATED_CAPTCHA_KEY";

    private String captchaParameter = SPRING_SECURITY_FORM_CAPTCHA_KEY;

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        String genCode = this.obtainGeneratedCaptcha(request);
        String inputCode = this.obtainCaptcha(request);
        if (genCode == null)
            throw new CaptchaException(this.messages.getMessage("LoginAuthentication.captchaInvalid"));
        if (!genCode.equalsIgnoreCase(inputCode)) {
            throw new CaptchaException(this.messages.getMessage("LoginAuthentication.captchaNotEquals"));
        }

        return super.attemptAuthentication(request, response);
    }

    protected String obtainCaptcha(HttpServletRequest request) {
        return request.getParameter(this.captchaParameter);
    }

    protected String obtainGeneratedCaptcha(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(SESSION_GENERATED_CAPTCHA_KEY);
    }
}
