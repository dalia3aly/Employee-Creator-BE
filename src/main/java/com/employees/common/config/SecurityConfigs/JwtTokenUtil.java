// package com.employees.common.config.SecurityConfigs;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import javax.crypto.SecretKey;
// import java.nio.charset.StandardCharsets;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// @Component
// public class JwtTokenUtil {

//     @Value("${jwt.secret}")
//     private String secret;

//     @Value("${jwt.expiration}")
//     private Long expiration;

//     private SecretKey getSigningKey() {
//         byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
//         return Keys.hmacShaKeyFor(keyBytes);
//     }

//     public String generateToken(Authentication authentication) {
//         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//         Map<String, Object> claims = new HashMap<>();
//         return createToken(claims, userDetails.getUsername());
//     }

//     private String createToken(Map<String, Object> claims, String subject) {
//         Date now = new Date();
//         Date expirationDate = new Date(now.getTime() + expiration * 1000);

//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(subject)
//                 .setIssuedAt(now)
//                 .setExpiration(expirationDate)
//                 .signWith(getSigningKey())
//                 .compact();
//     }

//     public Boolean validateToken(String token, UserDetails userDetails) {
//         final String username = getUsernameFromToken(token);
//         return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//     }

//     public String getUsernameFromToken(String token) {
//         return getClaimFromToken(token, Claims::getSubject);
//     }

//     public Date getExpirationDateFromToken(String token) {
//         return getClaimFromToken(token, Claims::getExpiration);
//     }

//     public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = getAllClaimsFromToken(token);
//         return claimsResolver.apply(claims);
//     }

//     private Claims getAllClaimsFromToken(String token) {
//         return Jwts.parser()
//                 .verifyWith(generateSigningKey())
//                 .build()
//                 .parseSignedClaims(token)
//                 .getPayload();
//     }

//     private Boolean isTokenExpired(String token) {
//         final Date expiration = getExpirationDateFromToken(token);
//         return expiration.before(new Date());
//     }
// }
