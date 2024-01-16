package com.ims.Authentication;

import com.ims.Authentication.model.UserProfile;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/SweetShop")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody UserProfile userProfile) {
        return userService.register(userProfile);
    }

    @GetMapping(value = { "/users" })
    public List<UserProfile> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(value = { "/login" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(HttpServletResponse response, HttpSession session,
        @RequestBody UserProfile userProfile) {
        return userService.login(response, session, userProfile);
    }

    @GetMapping("/check-login")
    public boolean checkLogin(HttpSession session) {
        // Check if the user's id is set in the session
        Long userId = (Long) session.getAttribute("userId");
        System.out.println(userId != null);
        return userId != null;
    }


    @GetMapping("/get-user-id-cookie")
    public long getUserIdCookie(HttpServletRequest request) {
        // Retrieve the user's id from the cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> userIdCookie = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals("userId"))
                    .findFirst();

            if (userIdCookie.isPresent()) {
                return Long.parseLong(userIdCookie.get().getValue());
            }
        }
        return -1;
    }
}
