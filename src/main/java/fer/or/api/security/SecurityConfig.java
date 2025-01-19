package fer.or.api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${okta.oauth2.issuer}")
    private String issuer;
    @Value("${okta.oauth2.client-id}")
    private String clientId;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/"
                        , "/openAPI/specification"
                        , "application/json"
                        , "boardGames/delete/**"
                        , "/boardGames/add"
                        , "/designers/**"
                        , "/designers"
                        , "/boardGames/**"
                        , "/boardGames"
                        , "/publishers/**"
                        , "/publishers"
                        , "/franchises/**"
                        , "/franchises"
                        , "/artists/**"
                        , "/artists"
                        , "/boardGames/datatable"
                        , "/boardGames/data.csv"
                        , "/boardGames/data.json").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(withDefaults())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/odjava", "GET"))
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.sendRedirect("/");
                        }));
        return http.build();
    }
}
