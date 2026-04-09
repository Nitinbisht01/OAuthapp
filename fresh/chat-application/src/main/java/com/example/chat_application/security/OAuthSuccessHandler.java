package com.example.chat_application.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.chat_application.model.User;
import com.example.chat_application.repositories.UserRepository;
import com.example.chat_application.service.impl.JwtService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oAuthUser = (OAuth2User) authentication.getPrincipal();

        // 🔥 detect provider
        String provider = request.getRequestURI().contains("github") 
                ? "GITHUB" 
                : "GOOGLE";

        String email = null;
        String name = null;

        // ================= GOOGLE =================
        if (provider.equals("GOOGLE")) {
            email = oAuthUser.getAttribute("email");
            name = oAuthUser.getAttribute("name");
        }

        // ================= GITHUB =================
        else if (provider.equals("GITHUB")) {
            name = oAuthUser.getAttribute("name");

            if (name == null) {
                name = oAuthUser.getAttribute("login");
            }

            email = oAuthUser.getAttribute("email");

            // 🔥 GitHub may not provide email
            if (email == null) {
                String username = oAuthUser.getAttribute("login");
                email = username + "@github.com";
            }
        }

        // 🔥 check if user exists
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            user = User.builder()
                    .email(email)
                    .name(name)
                    .provider(provider) // 🔥 FIXED
                    .build();

            userRepository.save(user);
        }

        // 🔥 generate JWT
        String token = jwtService.generateToken(user.getEmail());

        // 🔥 redirect to frontend
        try {
			response.sendRedirect("http://localhost:5173/oauth-success?token=" + token);
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("PROVIDER: " + provider);
        System.out.println("EMAIL: " + email);
    }
}