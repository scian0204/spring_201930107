package com.example.spring_201930107.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable() // 제공하는 로그인 폼 UI 사용 안함
                .csrf().disable() // rest api는 csrf 보안 필요없으므로 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안하므로 비활성화
                .and()

                // 접근권한 설정
                .authorizeRequests()
                // 누구나 허용할 경로
                    .antMatchers(
                            "/sign-api/sign-in", "/sign-api/sign-up", "/sign-api/exception"
                    ).permitAll()
                    .antMatchers(
                            HttpMethod.GET,
                            "/product/*"
                    ).permitAll()
                    .antMatchers(
                            HttpMethod.GET,
                            "/board/*"
                    ).permitAll()

                    // USER권한만 허용할 경로
                    .antMatchers(
                            HttpMethod.POST,
                            "/order"
                    ).hasRole("USER")

                    // ADMIN권한만 허용할 경로
                    .antMatchers(
                            HttpMethod.PUT,
                            "/product"
                    ).hasRole("ADMIN")
                    .antMatchers(
                            HttpMethod.POST,
                            "/product"
                    ).hasRole("ADMIN")
                    .antMatchers(
                            HttpMethod.DELETE,
                            "/product"
                    ).hasRole("ADMIN")
                    .antMatchers(
                            HttpMethod.GET,
                            "/order/*"
                    ).hasRole("ADMIN")
                    .antMatchers(
                            HttpMethod.GET,
                            "/user/list*"
                    ).hasRole("ADMIN")

                    // USER, ADMIN 권한 둘다 허용할 경로
                    .antMatchers(
                            HttpMethod.POST,
                            "/board"
                    ).hasAnyRole("USER", "ADMIN")
                    .antMatchers(
                            HttpMethod.PUT,
                            "/board"
                    ).hasAnyRole("USER", "ADMIN")
                    .antMatchers(
                            HttpMethod.DELETE,
                            "/board"
                    ).hasAnyRole("USER", "ADMIN")

                .and()
                // 오류 핸들링
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()) // 해당 오류 발생 시 해당 핸들러로 처리
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 해당 오류 발생 시 해당 핸들러로 처리
                .and()
                // 1파라미터 필터를 2파라미터 필터 전에 불러옴
                .addFilterBefore(new JwtAuthenicationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers(
                        "/v3/api-docs/**", 
                        "/swagger-ui/**", 
                        "/swagger-ui/index.html", 
                        "/swagger/**"
                ); // swagger 허용
    }
}
