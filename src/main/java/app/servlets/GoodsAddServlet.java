package app.servlets;

import app.path.Path;
import app.service.GoodsAddService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static app.servlets.TermsCheckServlet.ORDER;

public class GoodsAddServlet extends HttpServlet {
    private static final String GOOD = "good";

    /**
     * Handles {@link HttpServlet} POST Method.
     * If the item has not been selected returns to the selection page. If selected - adds it to
     * the order and returns to the selection page
     *
     * @param request  the {@link HttpServletRequest} contains selected item as a parameter
     * @param response the {@link HttpServletResponse}
     * @throws IOException      thrown when occur exception in getting Writer object
     * @throws ServletException thrown when occur exception in redirecting
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        String chosenItem = request.getParameter(GOOD);
        Map<String, Double> orderMap = (HashMap<String, Double>) request.getSession().getAttribute(ORDER);

        Path pathForRedirect = GoodsAddService.service(chosenItem, orderMap);
        forwardTo(request, response, pathForRedirect);

    }

    /**
     * Redirect request by the transferred path
     *
     * @param request  the {@link HttpServletRequest} contains user name and map for containing order
     * @param response the {@link HttpServletResponse}
     * @param path     the path for redirection
     * @throws IOException      thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    private void forwardTo(final HttpServletRequest request, final HttpServletResponse response, Path path) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path.getPath());
        requestDispatcher.forward(request, response);
    }

}


