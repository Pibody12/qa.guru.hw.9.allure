package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.qameta.allure.Allure.attachment;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private static String REPOSITORY = "Pibody12/qa_guru_hw3";
    private static String ISSUE = "Тест";

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную старницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".search-input-container").click();
            $(".QueryBuilder-Input").sendKeys(REPOSITORY);
            $(".QueryBuilder-Input").submit();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issue", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с названием " + ISSUE, () -> {
            $(withText(ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedAttachmentsStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps =  new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepository(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithText(ISSUE);
    }

    @Test
    public void testLambdaAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide());

            step("Открываем главную старницу", () -> {
                open("https://github.com");
                attachment("Source", webdriver().driver().source());
            });
    }

    @Test
    public void testAnnotatedAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps =  new WebSteps();

        steps.openMainPage();
        steps.takeScreenshot();

    }
}
