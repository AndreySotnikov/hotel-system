package project.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import project.service.login.EncryptPassword;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by andrey on 23.03.15.
 */
@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    EncryptPassword encryptPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().contentTypeOptions().and()
                .authorizeRequests()
                .antMatchers("/", "/home", "/register","/resources/assets/**"
                        ,"/resources/templates/part").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/loginuser").defaultSuccessUrl("/transfer",false).failureUrl("/fail")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout").logoutSuccessUrl("/home")
                .permitAll()
                .and()
                .httpBasic();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/assets/bootstrap/**");
//        web.ignoring().antMatchers("https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js");
//        //web.ignoring().antMatchers("/resources/assets/templates/part/**");
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                try {
                    return encryptPassword.encrypt(charSequence.toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(encode(charSequence));
            }
        }).dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select us.username,r.role from user_roles as r, users as us where user_id=users_userid and us.username=?");
                        //"select username, role from user_roles where username=?");


//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
    }
}
