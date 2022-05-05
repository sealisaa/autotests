package pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

import utils.User;

public class LoginPage extends BasePage {

    private static final String LOGIN_FIELD = "//input[@id='field_email']";
    private static final String PASSWORD_FIELD = "//input[@id='field_password']";
    private static final String SUBMIT_BUTTON = "//*[@class='login-form-actions']/input[@type='submit']";

    public LoginPage() {
        check();
    }

    public MainPage login(User user) {
        $(byXpath(LOGIN_FIELD)).setValue(user.getLogin());
        $(byXpath(PASSWORD_FIELD)).setValue(user.getPassword());
        $(byXpath(SUBMIT_BUTTON)).click();
        return new MainPage();
    }

    protected void check() {
        $(byXpath(LOGIN_FIELD)).shouldBe(Condition.visible.because("Не отображается поле для ввода логина"));
        $(byXpath(PASSWORD_FIELD)).shouldBe(Condition.visible.because("Не отображается поле для ввода пароля"));
        $(byXpath(SUBMIT_BUTTON)).shouldBe(Condition.visible.because("Не отображается кнопка входа"));
    }
}