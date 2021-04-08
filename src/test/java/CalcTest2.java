import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CalcTest2 {
    private WebDriver driver =null;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mts\\IdeaProjects\\HomeTask2\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.calc.ru/kalkulyator-kalorii.html");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        WebElement expectedAge = driver.findElement(By.id("age"));
        expectedAge.sendKeys("26");
        //write expected age
        WebElement expectedWeight = driver.findElement(By.id("weight"));
        expectedWeight.sendKeys("88");
        //write expected weight
        WebElement expectedHeight = driver.findElement(By.id("sm"));
        expectedHeight.sendKeys("183");
        //write expected height
    }

    @Test
    public void isAgeInBox1 () {
        WebElement actualAge = driver.findElement(By.xpath("//*[@id=\"age\"]"));
        //find field with actual age
        String age = actualAge.getAttribute("value");
        //get value from age field
        Assert.assertEquals(age, "26");
        //is answer true
        System.out.println("1) " + age);
        //print answer
    }

    @Test
    public void isFemRadioButtonSelected2 () {
        WebElement femRadioButton = driver.findElement(By.xpath("//label[@for=('sexFem')]/span"));
        //find fem checkBox
        femRadioButton.click();
        //check fem radio button
        WebElement maleRadioButtonAfterSelectedFemRadioButton = driver.findElement(By.id("sexMale"));
        WebElement femRadioButtonAfterSelectedFemRadioButton = driver.findElement(By.id("sexFem"));
        //find male and fem RB after selected fem radio button
        String maleRB = maleRadioButtonAfterSelectedFemRadioButton.getAttribute("checked");
        String femRB = femRadioButtonAfterSelectedFemRadioButton.getAttribute("checked");
        //get attribute "checked" from male and fem radio button after selected fem fem radio button
        Assert.assertEquals(maleRB, null);
        Assert.assertEquals(femRB, "true");
        System.out.println("2.1) Male - " + maleRB);
        System.out.println("2.2) Female - " + femRB);
    }

    @Test
    public void changingOfActivityLevel3 (){
        WebElement activityLevel = driver.findElement(By.id("activity"));
        activityLevel.click();
        //get activity list
        WebElement usualActivity = driver.findElement(By.xpath("//option[@value=\"1.0\"]"));
        usualActivity.click();
        //choose usual activity
        String expectedActivityLevel = usualActivity.getText();
        System.out.println("3) " + expectedActivityLevel);
        Assert.assertEquals(expectedActivityLevel,"Основной обмен");

    }

    @Test
    public void chooseTheSecondMethodAndCheckingResultWithFirstMethod4 () {
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        //click submit button
        WebElement findResultOfTheFirstMethod = driver.findElement(By.xpath("//td[contains(text(), '2129 ккал/день')]"));
        String expectedResultOfTheFirstMethod = findResultOfTheFirstMethod.getText();
        //get result of the first method
        System.out.println("4.1) " + expectedResultOfTheFirstMethod);
        WebElement chooseTheSecondMethod = driver.findElement(By.xpath("//label[@for=\"optHB\"]"));
        chooseTheSecondMethod.click();
        //choose the second method
        WebElement activityLevel = driver.findElement(By.id("activity"));
        activityLevel.click();
        WebElement usualActivity = driver.findElement(By.xpath("//option[@value=\"1.0\"]"));
        usualActivity.click();
        //choose usual activity again
        WebElement submitButtonTheSecondTime = driver.findElement(By.id("submit"));
        submitButtonTheSecondTime.click();
        //click submit button again
        WebElement findResultOfTheSecondMethod = driver.findElement(By.xpath("//td[contains(text(), '2016 ккал/день')]"));
        String expectedResultOfTheSecondMethod = findResultOfTheSecondMethod.getText();
        //get result of the second method
        System.out.println("4.2) " + expectedResultOfTheSecondMethod);
        Assert.assertTrue(expectedResultOfTheFirstMethod != expectedResultOfTheSecondMethod);
    }

    @Test
    public void writeIrregularWeight5 () {
        WebElement weightField = driver.findElement(By.id("weight"));
        weightField.clear();
        weightField.sendKeys("AA");
        //write irregular weight
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        //click submit button
        WebElement error = driver.findElement(By.xpath("//span[@id=\"error\"]"));
        String errorText = error.getAttribute("textContent");
        //get error text
        System.out.println("5) " + errorText);
        Assert.assertEquals(errorText, "Поле \"Вес\" должно быть числовым");
        //
        WebElement weight = driver.findElement(By.id("weight"));
        weight.clear();
        weight.sendKeys("88");
        //can't add code about clearing irregular weight and writing true weight to @AfterClass
    }

    @AfterTest
    public void quit() {
        driver.quit();
        //quit
    }
}
