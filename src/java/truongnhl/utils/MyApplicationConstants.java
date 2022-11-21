/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongnhl.utils;

/**
 *
 * @author 12345
 */
public class MyApplicationConstants {

    public class DispatchFeature {

        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_FULLNAME_CONTROLLER = "searchFullnameController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
        public static final String ADD_ITEM_TO_CART_CONTROLLER = "addItemToCartController";
        public static final String VIEW_CART_PAGE = "viewCartPage";
        public static final String REMOVE_ITEM_CONTROLLER = "removeItemController";
        public static final String LOGOUT_CONTTROLLER = "logout";
        public static final String CREATE_NEW_ACCOUNT_CONTROLLER = "createNewAccountController";
        public static final String PROCESS_REQUEST_CONTROLLER = "processRequestController";
        public static final String CHECK_OUT_CONTROLLER = "checkOut";
        public static final String VIEW_CART_CONTROLLER = "viewCart";
    }

    public class LoginFeature {

        public static final String SEARCH_PAGE = "searchPage";
        public static final String INVALID_PAGE = "invalidPage";

    }

    public class LogoutFeature {

        public static final String LOGIN_PAGE = DispatchFeature.LOGIN_PAGE;
    }

    public class DeleteFeature {

        public static final String ERROR_PAGE = "errorPage";
    }

    public class CreateFeature {

        public static final String LOGIN_PAGE = DispatchFeature.LOGIN_PAGE;
        public static final String ERROR_PAGE = "createError";
    }

    public class AddItemFeature {

        public static final String SHOPPING_PAGE = "shoppingPage";
    }

    public class ProcessRequestFeature {

        public static final String LOGIN_PAGE = DispatchFeature.LOGIN_PAGE;
        public static final String SEARCH_PAGE = LoginFeature.SEARCH_PAGE;
    }

    public class SearchFullnameFeature {

        public static final String SEARCH_PAGE = LoginFeature.SEARCH_PAGE;
        public static final String SEARCH_RESULT_PAGE = LoginFeature.SEARCH_PAGE;
    }

    public class UpdateAccountFeature {

        public static final String ERROR_PAGE = DeleteFeature.ERROR_PAGE;
    }

    public class ShowProductFeature {

        public static final String LOGIN_PAGE = DispatchFeature.LOGIN_PAGE;
        public static final String SHOW_PRODUCT_CONTROLLER = "showProduct";
        public static final String SHOPING_PAGE = AddItemFeature.SHOPPING_PAGE;
    }

    public class CheckoutFeature {

        public static final String CHECK_OUT_PAGE = "checkOutPage";
    }
}
