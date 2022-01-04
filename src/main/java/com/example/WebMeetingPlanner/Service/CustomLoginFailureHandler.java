package com.example.WebMeetingPlanner.Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Repository.UserRepository;
import com.example.WebMeetingPlanner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String email = request.getParameter("email");
        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (user.isEnabled() && user.isAccountNonLocked()) {
                if (user.getFailedAttempt() < UserService.MAX_FAILED_ATTEMPTS - 1) {
                    userService.increaseFailedAttempts(user);
                    exception = new BadCredentialsException("\"Bad credentials! Your account will be locked.\"\n") ;
                    System.out.println("failed to login " + exception.getMessage());
                } else {
                    System.out.println("failed to login" + exception.getMessage());
                    userService.lock(user);
                    exception = new LockedException("\"Your account has been locked due to 3 failed attempts.\"\n" +
                            " + \" It will be unlocked after 24 hours.\"") ;
//                    exception = new LockedException();
                }
            }
//            else if (!user.isAccountNonLocked()) {
//                if (userService.unlockWhenTimeExpired(user)) {
//                    exception = new LockedException("Your account has been unlocked. Please try to login again.");
//                }
//            }

        }

        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}

//                    userService.increaseFailedAttempts(user);
//                }
//                else {
//                    userService.lock(user);
//                    exception = new LockedException("Your account has been locked due to 3 failed attempts."
//                            + " It will be unlocked after 24 hours.");
//                }
//            } else if (!user.isAccountNonLocked()) {
//                if (userService.unlockWhenTimeExpired(user)) {
//                    exception = new LockedException("Your account has been unlocked. Please try to login again.");
//                }
//            }

//            }
//        }
//        super.setDefaultFailureUrl("/login?error");
//        super.onAuthenticationFailure(request, response, exception);
//    }

//}