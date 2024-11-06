package kg.mega.delivery_service.config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.mega.delivery_service.model.entity.User;
import kg.mega.delivery_service.response.TokenResponse;
import kg.mega.delivery_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Component
@Configuration
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;

    private final PasswordEncoder encoder;

    @Value("qwerty")
    String secret;

    @Value("300000")
    Long duration;

    @Autowired
    public AuthenticationFilter(@Lazy AuthenticationManager authenticationManager, UserService userService, PasswordEncoder encoder, UserService userService1, PasswordEncoder encoder1) {
        this.userService = userService1;
        this.encoder = encoder1;
        super.setAuthenticationManager(authenticationManager);
        this.setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty()) {
            log.warn("Username header is empty");
        }
        if (password == null || password.trim().isEmpty()) {
            log.warn("Password header is empty");
        }
        log.info("User {} authentication", username);

        var token = new UsernamePasswordAuthenticationToken(username, password);
        return super.getAuthenticationManager().authenticate(token);
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC256(secret);

        String generatedAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date())
                .withNotBefore(new Date())
                .withIssuer(request.getRequestURL().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .withClaim("authorities", Collections.singletonList(user.getRole()))
                .withClaim("type_of_token", "access")
                .sign(algorithm);

        String refreshAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date())
                .withNotBefore(new Date(System.currentTimeMillis() + duration))
                .withIssuer(request.getRequestURL().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + duration * 2))
                .withClaim("authorities", Collections.singletonList(user.getRole()))
                .withClaim("type_of_token", "refresh")
                .sign(algorithm);

        TokenResponse tokenResponse = TokenResponse
                .builder()
                .accessToken(generatedAccessToken)
                .refreshToken(refreshAccessToken)
                .build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper()
                .writeValue(response.getOutputStream(), tokenResponse);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        if (failed instanceof BadCredentialsException) {
            //userService.checkAttempts(request.getHeader("password"), request.getHeader("username"));
        }
    }
}

