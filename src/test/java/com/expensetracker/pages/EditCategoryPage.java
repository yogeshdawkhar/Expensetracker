package com.expensetracker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.expensetracker.base.BaseClass;
import com.expensetracker.utility.ExtentReportLog;

public class EditCategoryPage extends BaseClass {
	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[1]/div[1]/label")
	public WebElement nameLabel;

	@FindBy(how = How.ID, using = "name")
	public WebElement categoryNameTextBox;

	@FindBy(how = How.ID, using = "reset")
	public WebElement resetButton;

	@FindBy(how = How.ID, using = "logout")
	public WebElement logoutLink;

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
	public WebElement saveCategoryBtn;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[3]/div")
	public WebElement errorLogin;

	@FindBy(how = How.ID, using = "editaccount")
	public WebElement editAccount;

	@FindBy(how = How.XPATH, using = "/html/body/div/h1")
	public WebElement editCategoriesHeading;

	public String placeHolder = "placeholder";

	public EditCategoryPage(WebDriver driver) {
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

	public void clickSaveCategoryBtn() {
		saveCategoryBtn.click();
		ExtentReportLog.testCaseInfo("Save Category button clicked.");
	}

	public String getEditCategoriesHeading() {
		String heading = editCategoriesHeading.getText();
		ExtentReportLog.testCaseInfo("Edit Category Heading :" + heading);
		return heading;
	}

	public void enterCategory(String category) {
		categoryNameTextBox.click();
		categoryNameTextBox.clear();
		categoryNameTextBox.sendKeys(category);
		ExtentReportLog.testCaseInfo("Entered Category:" + category);
	}

	public void clearCategoryTextBox() {
		categoryNameTextBox.clear();
		ExtentReportLog.testCaseInfo("Cleared Category textbox.");
	}

	public void enterCategoryName(String category) {
		categoryNameTextBox.clear();
		categoryNameTextBox.sendKeys(category);
		ExtentReportLog.testCaseInfo("Category entered :" + category);
	}

	public void clickReset() {
		resetButton.click();
		ExtentReportLog.testCaseInfo("Reset button clicked.");
	}

	public void createCategory(String category) {
		clickAddCategoryLink();
		enterCategory(category);
		clickSaveCategoryBtn();
	}

	public boolean findCategory(String category) {
		WebElement find = driver.findElement(By.xpath("//*[text()='" + category + "']"));
		return find.isDisplayed();
	}

}
