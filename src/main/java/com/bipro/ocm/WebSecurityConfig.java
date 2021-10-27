package com.bipro.ocm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Value("${spring.queries.users-query}")
//    private String usersQuery;
//
//    @Value("${spring.queries.roles-query}")
//    private String rolesQuery;
    //

//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username,password, enabled from users where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, role from user_roles where username=?")
//        .rolePrefix("ROLE_");
//    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select u.username, r.name from users u " +
                                "inner join users_roles_join urj on(u.id=urj.users_id) " +
                                "inner join user_roles r on(urj.roles_id=r.id) where u.username = ?")
                .rolePrefix("ROLE_");

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email,password, enabled from users where email=?")
                .authoritiesByUsernameQuery(
                        "select u.email, r.name from users u " +
                                "inner join users_roles_join urj on(u.id=urj.users_id) " +
                                "inner join user_roles r on(urj.roles_id=r.id) where u.email = ?")
                .rolePrefix("ROLE_");
    }




//     /////////////     inMemoryAuthentication
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("java").password("java").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("ADMIN");
//
//    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/hello");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/welcome").hasAnyRole("USER", "ADMIN")
                .antMatchers("/view").hasAnyRole( "ADMIN")
                .antMatchers("/addNewEmployee").hasAnyRole("ADMIN")
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").permitAll()
//                .and().logout().permitAll();
                .antMatchers("/static/**", "/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .successHandler(successHandler)
                .loginPage("/login").permitAll().and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and().exceptionHandling();
//                .accessDeniedPage("/403");

//        http.csrf().disable();
    }


//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jpa");
//        dataSource.setUsername( "tutorialuser" );
//        dataSource.setPassword( "tutorialmy5ql" );
//        return dataSource;
//    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}