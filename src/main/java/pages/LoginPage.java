package pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import utils.User;

public class LoginPage extends BasePage {

    private static final By LOGIN_FIELD = byXpath("//input[@id='field_email']");
    private static final By PASSWORD_FIELD = byXpath("//input[@id='field_password']");
    private static final By SUBMIT_BUTTON = byXpath("//*[@class='login-form-actions']/input[@type='submit']");

    public LoginPage() {
        check();
    }

    @Override
    protected void check() {
        $(LOGIN_FIELD).shouldBe(Condition.visible.because("Не отображается поле для ввода логина"));
        $(PASSWORD_FIELD).shouldBe(Condition.visible.because("Не отображается поле для ввода пароля"));
        $(SUBMIT_BUTTON).shouldBe(Condition.visible.because("Не отображается кнопка входа"));
    }

    public MainPage login(User user) {
        $(LOGIN_FIELD)
                .shouldBe(Condition.visible.because("Не отображается поле для ввода логина"))
                .setValue(user.getLogin());
        $(PASSWORD_FIELD)
                .shouldBe(Condition.visible.because("Не отображается поле для ввода пароля"))
                .setValue(user.getPassword());
        $(SUBMIT_BUTTON)
                .shouldBe(Condition.visible.because("Не отображается кнопка входа"))
                .click();
        return new MainPage();
    }
}