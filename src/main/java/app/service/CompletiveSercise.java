package app.service;

import app.path.Path;
import app.utils.GoodsUtil;

import java.util.Map;

public class CompletiveSercise {
    private static final String EMPTY_ELEMENT = "--Choose item--";

    public static Path service(final String chosenItem, Map<String, Double> orderMap) {

     if(chosenItem != null & !(chosenItem.equals(EMPTY_ELEMENT))) {
        GoodsUtil.toBasket(chosenItem, orderMap);
    }

       return getRedirectPath(orderMap);
    }

        private static Path getRedirectPath(Map<String, Double> orderMap){
            if (orderMap.size() == 0) {
               return  Path.EMPTY_BASKET_ERROR;
            } else {
               return Path.PRINT_CHECK_PATH;
            }
        }
}
