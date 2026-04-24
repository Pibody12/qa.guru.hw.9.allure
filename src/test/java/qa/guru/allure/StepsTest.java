package qa.guru.allure;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.qameta.allure.Allure.attachment;

public class StepsTest extends TesteBase {

    private static String REPOSITORY = "Pibody12/qa_guru_hw3";
    private static String ISSUE = "Тест";
    WebSteps steps =  new WebSteps();

    @Test
    public void testLambdaStep() {
        step("Открываем главную старницу", () -> {
            steps.openMainPage();
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            steps.searchForRepository(REPOSITORY);
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            steps.clickOnRepository(REPOSITORY);
        });
        step("Открываем таб Issue", () -> {
            steps.openIssueTab();
        });
        step("Проверяем наличие Issue с названием " + ISSUE, () -> {
            steps.shouldSeeIssueWithText(ISSUE);
        });
    }

    @Test
    public void testAnnotatedAttachmentsStep() {
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepository(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithText(ISSUE);
    }

    @Test
    public void testLambdaAttachments() {
            step("Открываем главную старницу", () -> {
                open("");
                attachment("Source", webdriver().driver().source());
            });
    }

    @Test
    public void testAnnotatedAttachments() {
        steps.openMainPage();
        steps.takeScreenshot();
    }
}
