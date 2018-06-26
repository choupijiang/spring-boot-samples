package io.ostenant.springboot.sample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ostenant.springboot.sample.entities.User;
import io.ostenant.springboot.sample.exception.IllegalPropertiesException;
import io.ostenant.springboot.sample.exception.NullOrEmptyException;
import io.ostenant.springboot.sample.exception.SessionNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @PostMapping("user")
    public ResponseEntity<?> save(HttpServletRequest request, HttpSession session) throws Exception {
        String sessionId = (String) session.getAttribute("sessionId");
        if (StringUtils.isBlank(sessionId)) {
            throw new SessionNotFoundException();
        }

        String userPlainText = request.getParameter("user");
        if (StringUtils.isBlank(userPlainText) || StringUtils.equalsIgnoreCase("{}", userPlainText)) {
            throw new NullOrEmptyException();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userPlainText, User.class);

        if (StringUtils.isBlank(user.getUsername())) {
            throw new IllegalPropertiesException("username");
        }

        if (StringUtils.isBlank(user.getAccountName())) {
            throw new IllegalPropertiesException("accountName");
        }

        return ResponseEntity.ok("Successful");
    }
}
