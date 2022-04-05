package ru.vyacheslavkozlov.firstrunday.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.vyacheslavkozlov.firstrunday.config.security.JwtConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    public WebSecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/runday").permitAll()
                .antMatchers("/runday/login").permitAll()
                .antMatchers("/runday/registration").permitAll()
                .antMatchers("/**/favicon.ico").permitAll()
//                .antMatchers("/runday/registration").permitAll()
//                .antMatchers(HttpMethod.GET, "/runday/admin").hasAuthority(Permission.ACCOUNT_READ.getPermission())
//                .antMatchers(HttpMethod.POST, "/runday/admin").hasAuthority(Permission.ACCOUNT_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE, "/runday/admin").hasAuthority(Permission.ACCOUNT_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET, "/runday/runner").hasAuthority(Permission.ACCOUNT_READ.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtConfigurer);
//                .formLogin()
//                .loginPage("/runday/auth/login").permitAll()
//                .defaultSuccessUrl("/runday/auth/success")
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/runday/auth/logout", "POST"))
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/runday/auth/login");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}