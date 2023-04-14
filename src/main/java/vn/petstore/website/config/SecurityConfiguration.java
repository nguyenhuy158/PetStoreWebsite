package vn.petstore.website.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;
import vn.petstore.website.emun.Role;
import vn.petstore.website.services.CustomUserDetailService;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(
                        "/",
                        "/sign-up/**",
                        "/search/**",
                        "/img/**",
                        "/css/**",
                        "/js/**")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/admin/**").hasAnyAuthority(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .failureUrl("/login?fail")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/home")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }
}
