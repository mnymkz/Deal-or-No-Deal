
package Controller;

import Main.Model;
import View.View;

/**
 * main controller class
 * 
 * @author Michael
 */
public class Controller {

    private View view;
    private Model model; //TODO implement model 
    private HomeController homeController;
    private SignUpController signUpController;
    private LoginController loginController;
    private BankerController bankerController;
    private GameOverController gameOverController;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.homeController = new HomeController(view.getHomePanel(), view);
        this.signUpController = new SignUpController(view.getSignUpPanel(), view);
        this.loginController = new LoginController(view.getLoginPanel(), view);
        this.bankerController = new BankerController(view.getBankerPanel(), view);
        this.gameOverController = new GameOverController(view.getGameOverPanel(), view);
    }
}
