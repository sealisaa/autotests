import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    String loginXPath = "//input[@id='field_email']";
    String passwordXPath = "//input[@id='field_password']";
    String buttonXPath = "//*[@class='login-form-actions']/input[@type='submit']";

    MainPage login(User user) {
        $(By.xpath(loginXPath)).setValue(user.getLogin());
        $(By.xpath(passwordXPath)).setValue(user.getPassword());
        $(By.xpath(buttonXPath)).click();
        return new MainPage();
    }
}