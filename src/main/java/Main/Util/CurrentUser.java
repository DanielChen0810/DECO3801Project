package Main.Util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {

    public static String getRole() {
        if (CurrentUser.authenticated()) {


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication.getName();
        } else {
            return "user";
        }
    }

    /**
     * If there is a logged in user, then return true
     *
     * @return True is there is someone logged in.
     */
    private static boolean authenticated() {
        return SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
    }
}
