package qa.guru.allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LablesTest {

    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("testovich")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание Issue для авторизованного пользователя")
    public void testStaticLabels() {
    }

    @Test
    public void testDynamicLabels() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Создание Issue для авторизованного пользователя")
        );
        Allure.feature("Issue в репозитории");
        Allure.story("Создание Issue");
        Allure.label("owner" , "testovich");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("testing", "https://testing.github.com");
    }

}
