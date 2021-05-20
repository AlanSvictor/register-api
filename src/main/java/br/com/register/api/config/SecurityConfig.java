package br.com.register.api.config;

import br.com.register.api.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import static br.com.register.api.config.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().configurationSource(httpServletRequest -> new CorsConfiguration().applyPermitDefaultValues())//libera o acesso para aplicações fora do mesmo dominio
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, SIGN_UP_URL).permitAll()
                .antMatchers("/playground/**").permitAll()
//                .antMatchers("/graphql/**").permitAll()
//                .antMatchers("/actuator/**").permitAll()
//                .antMatchers("/playground").permitAll()
//                .antMatchers("/graphql").permitAll()
//                .antMatchers("/actuator").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));

//        http.authorizeRequests()
////                .anyRequest().authenticated() Todas as url são autenticadas
////                .antMatchers("/*/students/**").hasRole("USER") /Significa que toda url com /alguma coisa/students/ devera ter a role USER
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // para uso em memoria
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("alan").password("devdojo").roles("USER")
//                .and()
//                .withUser("sergio").password("devdojo").roles("USER", "ADMIN");
//    }
}
