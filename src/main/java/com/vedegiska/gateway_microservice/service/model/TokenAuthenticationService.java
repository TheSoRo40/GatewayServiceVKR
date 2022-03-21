package com.vedegiska.gateway_microservice.service.model;

import com.vedegiska.gateway_microservice.repo.UserRepository;
import com.vedegiska.gateway_microservice.service.inter.ITokenAuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
public class TokenAuthenticationService implements ITokenAuthenticationService {
    private final UserRepository userRepository;
    @Value("${jwt.header_key}")
    private String authorizationHeaderKey;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.prefix}")
    private String tokenPrefix;

    @Override
    public void addAuthentication(HttpServletResponse res, String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        res.addHeader(authorizationHeaderKey, (tokenPrefix + " " + jwt));
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(authorizationHeaderKey);
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(tokenPrefix, ""))
                    .getBody()
                    .getSubject();
            if (user != null) {
                Collection<? extends GrantedAuthority> authorities = Objects
                        .requireNonNull(userRepository.findByEmail(user).orElse(null)).getAuthorities();
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
        }
        return null;
    }
}
