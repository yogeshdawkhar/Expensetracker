package com.expensetracker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.expensetracker.base.BaseClass;
import com.expensetracker.utility.ExtentReportLog;

public class AddCategoryPage extends BaseClass {
	@FindBy(how = How.ID, using = "name")
	public WebElement categoryTextBox;

	@FindBy(how = How.ID, using = "reset")
	public WebElement resetButton;

	@FindBy(how = How.ID, using = "logout")
	public WebElement logoutLink;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[1]/div[1]/label")
	public WebElement nameLabel;

	@FindBy(how = How.XPATH, using = "/html/body/nav/div/div[1]/a")
	public WebElement expenseTracker;

	@FindBy(how = How.ID, using = "go_add_expense")
	public WebElement addExpense;

	@FindBy(how = How.ID, using = "go_list_expenses")
	public WebElement listExpenses;

	@FindBy(how = How.ID, using = "go_list_categories")
	public WebElement listCategories;

	@FindBy(how = How.ID, using = "go_show_statistics")
	public WebElement showStatistics;

	@FindBy(how = How.ID, using = "go_add_category")
	public WebElement addCategoryLink;

	@FindBy(how = How.ID, using = "submit")
	public WebElement createCategoryBtn;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[3]/div")
	public WebElement errorLogin;

	@FindBy(how = How.ID, using = "editaccount")
	public WebElement editAccount;

	@FindBy(how = How.XPATH, using = "/html/body/div/h1")
	public WebElement listCategoriesHeading;

	public String placeHolder = "placeholder";

	public AddCategoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickListCategoriesLink() {
		listCategories.click();
		ExtentReportLog.testCaseInfo("ListCategories clicked.");
	}

	public void clickAddCategoryLink() {
		addCategoryLink.click();
		ExtentReportLog.testCaseInfo("Add Category clicked.");
	}

	public void clickCreateCategoryBtn() {
		createCategoryBtn.click();
		ExtentReportLog.testCaseInfo("Create Category button clicked.");
	}

	public String getListCategoriesHeading() {
		String heading = listCategoriesHeading.getText();
		ExtentReportLog.testCaseInfo("List Categories Heading :" + heading);
		return heading;
	}

	public void enterCategory(String category) {
		categoryTextBox.click();
		categoryTextBox.clear();
		categoryTextBox.sendKeys(category);
		ExtentReportLog.testCaseInfo("Entered Category:" + category);
	}

	public void createCategory(String category) {
		addCategoryLink.click();
		enterCategory(category);
		clickCreateCategoryBtn();
	}

	public void clickResetButton() {
		resetButton.click();
		ExtentReportLog.testCaseInfo("Reset button Clicked.");
	}

	public boolean findCategory(String category) {
		WebElement find = driver.findElement(By.xpath("//*[text()='" + category + "']"));
		return find.isDisplayed();
	}

}
