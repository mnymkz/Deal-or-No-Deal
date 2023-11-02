
package Controller;

import Model.Model;
import View.View;

/**
 * parent controller class
 * 
 * @author Michael
 */
public class Controller {

    private View view;
    private Model model;
    private HomeController homeController;
    private SignUpController signUpController;
    private LoginController loginController;
    private GameController gameController;
    private BankerController bankerController;
    private GameOverController gameOverController;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.homeController = new HomeController(view.getHomePanel(), view, model);
        this.signUpController = new SignUpController(view.getSignUpPanel(), view, model);
        this.loginController = new LoginController(view.getLoginPanel(), view, model);
        this.bankerController = new BankerController(view.getBankerPanel(), view, model);
        this.bankerController = new BankerController(view.getBankerPanel(), view, model);
        this.gameOverController = new GameOverController(view.getGameOverPanel(), view, model);
    }
}
