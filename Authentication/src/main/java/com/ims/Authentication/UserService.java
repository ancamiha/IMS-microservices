package com.ims.Authentication;

import com.ims.Authentication.model.UserProfile;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserProfileRepository userProfileRepository;

    public String register(UserProfile userProfile) {
        try {
            userProfileRepository.save(userProfile);
        } catch (Exception e) {
            e.printStackTrace();
            return "User already exists!";
        }
        return "User registered successfully!";
    }

    public List<UserProfile> getUsers() {
        return this.userProfileRepository.findAll();
    }

    public ResponseEntity<String> login(HttpServletResponse response, HttpSession session, UserProfile userProfile) {
        UserProfile foundUser = userProfileRepository.findByEmailAndPassword(userProfile.getEmail(),
                userProfile.getPassword());
        if (foundUser != null) {
            Cookie userIdCookie = new Cookie("userId", Long.toString(foundUser.getId()));
            userIdCookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(userIdCookie);
            session.setAttribute("userId", foundUser.getId());
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
