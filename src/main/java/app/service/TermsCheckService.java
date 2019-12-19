package app.service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class TermsCheckService {
    private static final String USER_NAME = "name";
    public static final String ORDER = "order";

    /**
     * Create a new user setting the name and creating the basket for goods as a session attributes
     * @param session the user's {@link HttpSession}
     * @param name user's name
     */
    public static void createUser(final HttpSession session, final String name) {
        session.setAttribute(USER_NAME, name);
        session.setAttribute(ORDER, new HashMap<>());
    }
}
