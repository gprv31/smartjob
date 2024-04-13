package com.example.smartjob.application.configuration.security;

import com.example.smartjob.domain.port.input.GetUserInfoUseCase;
import com.example.smartjob.entity.UserInfo;
import com.example.smartjob.entity.response.UserInfoResponse;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final GetUserInfoUseCase getUserInfoUseCase;
    private final RequestMatcher ignoredPaths = new AntPathRequestMatcher("/swagger-ui", "login");

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
          throws ServletException, IOException {
       final String authHeader = request.getHeader("Authorization");
       final String jwt;
       if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
           filterChain.doFilter(request, response);
           return;
       }
       jwt = authHeader.substring(7);
       Claims claims = jwtService.extractAllClaims(jwt);
       String email = claims.getSubject();
       String password = claims.get("password").toString();
       if (StringUtils.isNotEmpty(email)
               && SecurityContextHolder.getContext().getAuthentication() == null) {
           //UserInfo userInfo = getUserInfoUseCase.getUserInfo(email, password).blockingGet();
           UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                   email, password, Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
           if (jwtService.isTokenValid(jwt, userDetails)) {
               SecurityContext context = SecurityContextHolder.createEmptyContext();
               UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                       userDetails, null, userDetails.getAuthorities());
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               context.setAuthentication(authToken);
               SecurityContextHolder.setContext(context);
           }
       }
        filterChain.doFilter(request, response);
    }
}
