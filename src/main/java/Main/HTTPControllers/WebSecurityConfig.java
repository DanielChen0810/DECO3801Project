package Main.HTTPControllers;

import Main.Entities.Charity;
import Main.Managers.CharityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Login security configs
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CharityManager charityManager;

    /**
     * Configures the in-memory authentication
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        for (Charity charity : charityManager.getCharities().values()) {
            auth.inMemoryAuthentication()
                    .withUser(charity.getUser()).password(passwordEncoder()
                    .encode(charity.getPassword()))
                    .roles(charity.getName());
        }
    }

    /**
     * Adds the charity roles, and then sets up the admin page security configs
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] roles = new String[charityManager.getCharities().size()];
        int i = 0;
        for (Charity charity : charityManager.getCharities().values()) {
            roles[i++] = charity.getName();
        }
        http
                .authorizeRequests()
                .antMatchers("/admin/*", "/admin").hasAnyRole(roles)
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();


    }

    /**
     * Password encoder
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
