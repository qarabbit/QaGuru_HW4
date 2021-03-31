package pageobject;

import com.github.javafaker.Faker;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormPage {

    Faker faker = new Faker();

    String site = "https://demoqa.com/automation-practice-form";
    String userEmail = faker.internet().emailAddress();
    String address = faker.address().city();

    String firstName = "Ekaterina";
    String lastName = "Sherova";
    String userNumber = "0000000000";
    String subject = "History";

    public void openPage() {
        open(site);
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
    }

    public void fillFormWithRequiredData() {
        //Ручным тестированием было выявлено, что обязательны для
        //заполнения поля Имя, Фамилия, Пол и Номер телефона
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $(byText("Female")).click();
        $("#userNumber").setValue(userNumber);
    }

    public void fillFormWithAllData() {
        fillFormWithRequiredData();
        $("#userEmail").setValue(userEmail);
        //Ввод предмета изучения
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue(subject).pressEnter();

        //Выбор хобби, картинки, адреса
        $(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("rabbit.jpeg");
        $("#currentAddress").setValue(address);

        //Выбор даты рождения с помощью виджета
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOptionByValue("1996");
        $(".react-datepicker__day--001").click();

        //Выбор штата и города через дропдаун
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
    }

    public void sendData() {
        $("#submit").click();
    }

    public void checkRequiredData() {
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(byText(firstName + " " + lastName)).shouldBe(visible);
        $(byText("Female")).shouldBe(visible);
        $(".modal-content").shouldHave(text("0000000000"));
    }

    public void checkAllData() {
        checkRequiredData();
        $(".modal-content").shouldHave(text(userEmail),
                text("01 November,1996"), text(subject), text("Reading"),
                text(address), text("NCR Delhi"), text("rabbit.jpeg"));
    }
}

