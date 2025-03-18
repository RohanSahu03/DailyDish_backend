package com.rk.dailydish.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rk.dailydish.exceptions.InternalServerException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private CustomeUserDetailService userDetailService;
//	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();

		 if (requestURI.equals("/api/auth/register") || requestURI.equals("/api/auth/login")) {
		        filterChain.doFilter(request, response);
		        return;
		    }
	    
        String requestHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		
		if (requestHeader != null && requestHeader.startsWith("Bearer")) {
			// looking good
				token = requestHeader.substring(7);
			try {
					username = this.jwtHelper.getUsernameFromToken(token);
				} 
			catch (IllegalArgumentException e) 
			{
//                logger.info("Illegal Argument while fetching the username !!");
				throw new InternalServerException("Illegal Argument while fetching the username !!");
			} 
			catch (ExpiredJwtException e) 
			{
              throw new InternalServerException("Authentication token is expired..!!");
			} 
			catch (MalformedJwtException e) 
			{
				System.out.println("Exception caught: " + e.getMessage());
				  throw new InternalServerException("Some changed has done in token !! Invalid Token");
			} 
			catch (Exception e) 
			{
				throw new InternalServerException("Something went wrong !!");

			}

		} 
		else 
		{
            throw new InternalServerException("Invalid Header Value!!");

		}

        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            //fetch user detail from username
        	
            UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
            Boolean validateToken =  false;
            try {
                validateToken = this.jwtHelper.validateToken(token, userDetails);
            	} 
            catch (Exception e) 
            {
                throw new InternalServerException("Token validation failed !!");

            }
            if (validateToken) {

                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } 
            else 
            {
                throw new InternalServerException("Token validation failed !!");
            }


        }

        filterChain.doFilter(request, response);

	}

}
