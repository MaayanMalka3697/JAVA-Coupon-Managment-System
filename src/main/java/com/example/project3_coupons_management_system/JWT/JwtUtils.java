package com.example.project3_coupons_management_system.JWT;

import com.example.project3_coupons_management_system.Entityes.ClientType;
import jakarta.servlet.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpMethod;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.Filter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtils implements Filter {
    private String SECRET_KEY = "maayan&barsecretasdsavbnmhtjkasy";
    private String newToken;

    private Claims extractAllClaims(String token) {
        return null;//Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).parseClaimsJws(token).getBody();
    }

    public String generateToken(AuthenticationRequest userDetails, ClientType clientType) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getEmail(), clientType);
    }

    private String createToken(Map<String, Object> claims, String subject , ClientType clientType) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuer(clientType.name()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    private boolean validateToken(String jwt) throws ServletException {
        //This line will throw an exception if it is not a signed JWS (as expected)
        try {
            Claims claims = extractAllClaims(jwt);
            newToken = generateToken(new AuthenticationRequest(claims.getSubject(), null), ClientType.valueOf(claims.getIssuer()));
            removeToken(jwt);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    private void removeToken(String jwt) {
        extractAllClaims(jwt).setExpiration(new Date(System.currentTimeMillis()));
    }

    //Manage all requests and responses
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest)req;
        final HttpServletResponse response = (HttpServletResponse)res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Credentials,Access-Control-Expose-Headers,Access-Control-Expose-Headers,Access-Control-Allow-Origin,Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, access-control-allow-methods,Access-Control-Request-Method, Access-Control-Request-Headers,Authorization");
        //Check if there is a options call before the actual call
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod()) ){
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        else {
            final String authorization =  request.getHeader("Authorization");
            //Check if the request is not login
            if (!request.getRequestURI().contains("login")){
                //Check if token startsWith Bearer and != null
                if(authorization == null || !authorization.startsWith("Bearer ") || !check(request.getRequestURI(), authorization.substring(7))) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                //Check if the token is valid and updated, if not then return status 401
                boolean bool  = validateToken(authorization.substring(7));
                if(!bool) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                } else {
                    response.setHeader("Authorization",newToken);
                    chain.doFilter(req, response);
                }
            } else {
                chain.doFilter(req, response);
            }
        }
    }

    //Check if URI request contains the same client type that the token has
    private boolean check(String requestURI, String jwt) {
        boolean bool = false;
        try {
            Claims claims = extractAllClaims(jwt);
            String issuer =  claims.getIssuer().toLowerCase();
            if(issuer.equals("administrator"))
                issuer = "admin";
            bool = requestURI.contains(issuer);
        } catch (Exception e) {
            return false;
        }
        return bool;
    }



}
