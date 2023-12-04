package com.example.demo.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.security.utils.Globals;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


    String username=request.getParameter("username");
    String password=request.getParameter("password");
    System.out.println(username +"in attemptAuthentication");

         UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(authenticationToken);
     }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication");
        User user=(User) authResult.getPrincipal();
        System.out.println(user);
        Algorithm algorithm=Algorithm.HMAC256(Globals.SECRET);
        String jwtAccessToken= JWT.create().
                withSubject(user.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis()+60*1000*5)).
                withIssuer(request.getRequestURL().toString()).
                withClaim("Roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).sign(algorithm);

        response.addHeader("Authorization", "Bearer " + jwtAccessToken);
        System.out.println(user.getPassword()+"successfulAuthentication" +jwtAccessToken);

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
