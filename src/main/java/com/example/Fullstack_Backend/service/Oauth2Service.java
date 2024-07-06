package com.example.Fullstack_Backend.service;
import com.example.Fullstack_Backend.ENUM.UserRoles;
import com.example.Fullstack_Backend.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Oauth2Service {
    @Autowired
    private UserService userService;

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.auth.uri}")
    private String authUri;

    @Value("${google.token.uri}")
    private String tokenUri;

    @Value("${google.redirect.uri}")
    private String redirectUri;
    private void createUserFromGoogleAuth(String idTokenString, Payload payload) {
        User user = new User();
        user.setEmail( payload.getEmail());
        user.setFullName(payload.get("name").toString());
        user.setProfilePictureUrl(payload.get("picture").toString());
        user.setGoogleId(payload.getSubject());
        user.setUserRole(UserRoles.USER);


        userService.createUser(user);


    }



    public String exchangeCodeForToken(String code) throws Exception {
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                new JacksonFactory(),
                tokenUri,
                clientId,
                clientSecret,
                code,
                redirectUri
        ).execute();

        // Get ID Token from Token Response
        String idTokenString = tokenResponse.getIdToken();

        // Validate and parse ID Token
        GoogleIdToken idToken = GoogleIdToken.parse(new JacksonFactory(), idTokenString);

        // Extract payload from ID Token
        Payload payload = idToken.getPayload();
        createUserFromGoogleAuth(idTokenString, payload);
        // Here you can perform additional validation and logic if needed

        return idTokenString;
}

}
