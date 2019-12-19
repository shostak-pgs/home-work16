package app.servlets;

import app.path.Path;
import app.service.CompletiveSercise;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static app.servlets.TermsCheckServlet.ORDER;

public class CompletiveServlet extends HttpServlet {
    private static final String GOOD = "good";

    /**
     * Handles {@link HttpServlet} POST Method. Called if the submit button was clicked.
     * If a product has been selected before, it adds it to the basket and redirects to the check print servlet.
     * If no products have been selected, a warning page is displayed.
     * @param request  the {@link HttpServletRequest} may contain the last selected item
     * @param response the {@link HttpServletResponse}
     * @throws IOException thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {

        Map<String, Double> orderMap = (HashMap<String, Double>) request.getSession().getAttribute(ORDER);
        String chosenItem = request.getParameter(GOOD);

        Path pathForRedirect = CompletiveSercise.service(chosenItem, orderMap);
        forwardTo(request, response, pathForRedirect);

    }

    /**
     * Redirect request by the transferred path
     * @param request  the {@link HttpServletRequest} contains user name and map for containing order
     * @param response the {@link HttpServletResponse}
     * @param path the path for redirection
     * @throws IOException      thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    private void forwardTo(final HttpServletRequest request, final HttpServletResponse response, Path path) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path.getPath());
        requestDispatcher.forward(request, response);
    }

}
