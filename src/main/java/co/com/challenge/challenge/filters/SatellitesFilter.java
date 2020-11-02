package co.com.challenge.challenge.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class SatellitesFilter extends OncePerRequestFilter {

  private static final String HEADER = "Authorization";
  private static final String PREFIX = "Bearer ";
  private static final String SECRET = "luifrangm2020**";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {
      if (existToken(request)) {
        Claims claims = validateToken(request);
        if (claims.get("authorities") != null) {
          loadClaims(claims);
        } else {
          SecurityContextHolder.clearContext();
        }
      } else {
        SecurityContextHolder.clearContext();
      }
      filterChain.doFilter(request, response);
    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }
  }

  private Claims validateToken(HttpServletRequest request) {
    String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
    return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
  }

  private void loadClaims(Claims claims) {

    List<String> authorities = (List) claims.get("authorities");

    UsernamePasswordAuthenticationToken auth =
        new UsernamePasswordAuthenticationToken(
            claims.getSubject(),
            null,
            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  private boolean existToken(HttpServletRequest request) {
    return request.getHeader(HEADER) != null ? Boolean.TRUE : Boolean.FALSE;
  }
}
