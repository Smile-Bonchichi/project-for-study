package kg.it.academy.OnlineAuction.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    final DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT t.login, t.password, t.is_active FROM users t WHERE t.login = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.login, r.name_role " +
                                "FROM users_roles ur " +
                                "INNER JOIN users u " +
                                "   on ur.user_id = u.id " +
                                "INNER JOIN roles r " +
                                "   on ur.role_id = r.id " +
                                "WHERE u.login = ? AND u.is_active = 1"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "/api/user/get-all-user").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/api/item/get-all-item").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/role").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/role").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/role/*").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/category").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/category").permitAll()

                .antMatchers(HttpMethod.POST, "/api/user/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/*").hasRole("USER")

                .antMatchers(HttpMethod.POST, "/api/item").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/item/*").hasRole("USER")

                .antMatchers(HttpMethod.GET, "/api/history/*").permitAll()

                .antMatchers(HttpMethod.POST, "/api/auction/*").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/auction/*").permitAll()

                .antMatchers(HttpMethod.POST, "/api/place-bet").hasRole("USER")

                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
