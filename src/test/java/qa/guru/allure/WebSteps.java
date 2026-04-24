package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step ("Открываем главную старницу")
    public void openMainPage() {
        open("");
    }

    @Step ("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {

        $(".search-input-container").click();
        $(".QueryBuilder-Input").sendKeys(repo);
        $(".QueryBuilder-Input").submit();
    }

    @Step ("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepository(String repo) {
        $(linkText(repo)).click();
    }

    @Step ("Открываем таб Issue")
    public void openIssueTab() {
        $("#issues-tab").click();
    }

    @Step ("Проверяем наличие Issue с названием {issue}")
    public void shouldSeeIssueWithText(String issue) {
        $(withText(issue)).should(Condition.exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
