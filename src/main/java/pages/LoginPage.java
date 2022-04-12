package pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import utils.User;

public class LoginPage extends BasePage {

    private final String LOGIN_FIELD = "//input[@id='field_email']";
    private final String PASSWORD_FIELD = "//input[@id='field_password']";
    private final String SUBMIT_BUTTON = "//*[@class='login-form-actions']/input[@type='submit']";

    public LoginPage() {
        open("http://ok.ru");
        check();
    }

    public MainPage login(User user) {

        $(byXpath(LOGIN_FIELD)).setValue(user.getLogin());
        $(byXpath(PASSWORD_FIELD)).setValue(user.getPassword());
        $(byXpath(SUBMIT_BUTTON)).click();

        return new MainPage();
    }

    protected void check() {
        $(byXpath(LOGIN_FIELD)).shouldBe(Condition.visible);
        $(byXpath(PASSWORD_FIELD)).shouldBe(Condition.visible);
        $(byXpath(SUBMIT_BUTTON)).shouldBe(Condition.visible);
    }
}