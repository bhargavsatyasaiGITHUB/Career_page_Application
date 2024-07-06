package com.example.Fullstack_Backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Fullstack_Backend.service.Oauth2Service;
@RestController
public class OAuth2Controller {
    @Autowired
  private Oauth2Service oauth2Service;

    @GetMapping("/api/auth/callback/google")
    public String googleCallback(@RequestParam String code) {
        try {
            // Exchange the authorization code for an access token and ID token
            String idTokenResponse = oauth2Service.exchangeCodeForToken(code);

            // Use the token or return a response as needed
            return "Token received: " + idTokenResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return "Token exchange error: " + e.getMessage();
        }
    }

}
