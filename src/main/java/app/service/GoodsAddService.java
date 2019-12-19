package app.service;

import app.path.Path;
import app.utils.GoodsUtil;
import java.util.Map;

public class GoodsAddService {
    private static final String EMPTY_ELEMENT = "--Choose item--";

    public static Path service(final String chosenItem, Map<String, Double> orderMap) {

        if (chosenItem == null | (chosenItem.equals(EMPTY_ELEMENT))) {
            return pathForEmpty(orderMap);
        } else {
            GoodsUtil.toBasket(chosenItem, orderMap);
            return Path.ADD;
        }
    }

    private static Path pathForEmpty(Map<String, Double> orderMap) {

        if (orderMap.size() == 0) {
            return Path.FIRST_ADD;
        } else {
            return Path.ADD;
        }
    }

}
