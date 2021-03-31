package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageobject.StudentRegistrationFormPage;

public class StudentRegistrationFormTest {

    StudentRegistrationFormPage steps = new StudentRegistrationFormPage();

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void FillWithRequiredData() {
        steps.openPage();
        steps.fillFormWithRequiredData();
        steps.sendData();
        steps.checkRequiredData();
    }

    @Test
    void FillWithAllData() {
        steps.openPage();
        steps.fillFormWithAllData();
        steps.sendData();
        steps.checkAllData();
    }
}

