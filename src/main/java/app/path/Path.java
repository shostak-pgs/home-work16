package app.path;

/**
 * This {@link Enum} encapsulate paths used in application
 */
public enum Path {
    FIRST_ADD("WEB-INF/view/firstAdd.jsp"),
    ADD("WEB-INF/view/add.jsp"),
    PRINT_CHECK_PATH("/printCheckServlet"),
    TERMS_ERROR("WEB-INF/view/errors/termsError.jsp"),
    EMPTY_BASKET_ERROR("WEB-INF/view/errors/emptyBasketError.jsp");

    private final String path;

    Path(String path) {
        this.path=path;
    }

    public String getPath() {
        return path;
    }
}
