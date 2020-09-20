package com.sharemoment.ws.configuration;

import com.sharemoment.ws.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //Bu şekilde de yapılabilir ancak inline yaratmak yerine(new AuthenticationEntryPoint(){..)
        //kendi classımızı vererek, classda da AuthenticationEntryPoint ten implement ederek yaparız.
//        http.httpBasic().authenticationEntryPoint(new AuthenticationEntryPoint() {
//            @Override
//            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//                response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
//            }
//        }); //Böyle çok daha kısa oldu
        http.httpBasic().authenticationEntryPoint(new AuthEntryPoint());

        http
                //Springe buraya gelecek Requestlerde Authentication kontrolü yap diyoruz.
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth").authenticated()
                .and()
                .authorizeRequests().anyRequest().permitAll();

        //Session yönetme
        //Stateless dediğimizde Security ile ilgili Session üretimi yapmamaya başlıyor.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Bu ana kadar spring gelen requestteki credentialsları çözmeye çalışıyor ancak, user nerden alacak
        //neye göre alacak vs bilmiyor. Configure etmemiz gerekli.
        //Springe eğer bir user bulmaya çalışıyorsan bizim userAuthService i kullan diyoruz.
        //UserAuthService'i UserDetailsService(Spring) den implement ettik
        //ve burada implement ettiğimiz loadUserByUsername metodunu kullanacak.Bu metotu da biz conf ediyoruz.
        auth.userDetailsService(userAuthService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
