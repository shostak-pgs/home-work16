package app.servlets;

import app.path.Path;
import app.service.TermsCheckService;
import app.utils.GoodsUtil;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class TermsCheckServlet extends HttpServlet {
    private static final String USER_NAME = "name";
    private static final String TERM = "term";
    public static final String ALL_GOODS_LIST = "allGoodsList";
    public static final String ORDER = "order";

    private Map<String, Double> goodsMap;

    /**
     * Called by the servlet container to indicate to a servlet that the servlet is being placed
     * into service. It receives ServletConfig object from the servlet container for getting parameters.
     * @param config the <code>ServletConfig</code> object that contains configuration
     * information for this servlet
     * @throws ServletException if an exception occurs interrupts the servlet's normal operation
     */
    @Override
    public void init(final ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        goodsMap = GoodsUtil.buildGoodsMap(servletContext);
        servletContext.setAttribute(ALL_GOODS_LIST, goodsMap);
        super.init(config);
    }

    /**
     * Handles {@link HttpServlet} POST Method. Checks if user's terms have been accepted.
     * If yes, calls user creation, otherwise redirect to the error page
     * @param request  the {@link HttpServletRequest} contains user name as a parameter. User name
     * transferred from the start(default) HTML page
     * @param response the {@link HttpServletResponse}
     * @throws IOException      thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {

        if (request.getParameter(TERM) == null) {
            forwardTo(request, response, Path.TERMS_ERROR);
        } else {
            TermsCheckService.createUser(request.getSession(true), request.getParameter(USER_NAME));
            forwardTo(request, response, Path.FIRST_ADD);
        }
    }

    /**
     * Handles {@link HttpServlet} GET Method. Redirect user if no goods were chosen after authentication
     * and order submitting
     * @param request  the {@link HttpServletRequest} contains user's name and basket as a parameters. User name
     * transferred from the start(default) HTML page
     * @param response the {@link HttpServletResponse}
     * @throws IOException      thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        forwardTo(request, response, Path.FIRST_ADD);
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
