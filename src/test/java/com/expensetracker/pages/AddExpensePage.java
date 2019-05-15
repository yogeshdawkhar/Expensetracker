package com.expensetracker.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.expensetracker.base.BaseClass;
import com.expensetracker.utility.ExtentReportLog;

public class AddExpensePage extends BaseClass {

	@FindBy(how = How.XPATH, using = "/html/body/div/h1")
	public WebElement addExpenseHeading;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[1]/div[1]/label")
	public WebElement dateLabel;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[1]/div[2]/table/tbody/tr[1]/th[1]/label")
	public WebElement dayLabel;

	@FindBy(how = How.ID, using = "day")
	public WebElement dayTextBox;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[1]/div[2]/table/tbody/tr[1]/th[2]/label")
	public WebElement monthLabel;

	@FindBy(how = How.ID, using = "month")
	public WebElement monthTextBox;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[1]/div[2]/table/tbody/tr[1]/th[3]/label")
	public WebElement yearLabel;

	@FindBy(how = How.ID, using = "year")
	public WebElement yearTextBox;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[2]/div[1]/label")
	public WebElement categoryLabel;

	@FindBy(how = How.ID, using = "category")
	public WebElement categoryDropDown;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[3]/div[1]/label")
	public WebElement amountLabel;

	@FindBy(how = How.ID, using = "amount")
	public WebElement amountTextBox;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/div[4]/div[1]/label")
	public WebElement reasonLabel;

	@FindBy(how = How.ID, using = "reason")
	public WebElement reasonTextBox;

	@FindBy(how = How.ID, using = "reset")
	public WebElement resetButton;

	@FindBy(how = How.ID, using = "submit")
	public WebElement createExpenseButton;
	
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

	public String placeHolder = "placeholder";

	public AddExpensePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enterExpenseDetailsAndClickCreateExpense(String day, String month, String year, String category,
			String amount, String reason)  {
		enterDay(day);
		enterMonth(month);
		enterYear(year);
		selectCategory(category);
		enterAmount(amount);
		enterReason(reason);
		clickCreateExpenseButton();
	}

	public void enterExpenseDetailsAndClickCreateExpenseWithoutCategory(String day, String month, String year,
			 String amount, String reason)  {
		enterDay(day);
		enterMonth(month);
		enterYear(year);
		enterAmount(amount);
		enterReason(reason);
		clickCreateExpenseButton();
	}

	public void enterExpenseDetailsAndClickReset(String day, String month, String year, String category, String amount,
			String reason)  {
		enterDay(day);
		enterMonth(month);
		enterYear(year);
		selectCategory(category);
		enterAmount(amount);
		enterReason(reason);
		clickResetButton();
	}

	public void enterDay(String day)  {
		dayTextBox.click();
		dayTextBox.clear();
		dayTextBox.sendKeys(day);
		ExtentReportLog.testCaseInfo("Entered Day:" + day);
	}

	public void enterMonth(String month)  {
		monthTextBox.click();
		monthTextBox.clear();
		monthTextBox.sendKeys(month);
		ExtentReportLog.testCaseInfo("Entered month:" + month);
	}

	public void enterYear(String year)  {
		yearTextBox.click();
		yearTextBox.clear();
		yearTextBox.sendKeys(year);
		ExtentReportLog.testCaseInfo("Entered year" + year);
	}

	public void clickCategoryDropDown()  {
		categoryDropDown.click();
		ExtentReportLog.testCaseInfo("Category Dropdown clicked.");
	}

	public void selectCategory(String category)  {
		categoryDropDown.click();
		Select drpCategory = new Select(categoryDropDown);
		drpCategory.selectByVisibleText(category);
		ExtentReportLog.testCaseInfo("Selected category from dropdown " + category);
	}

	public void enterAmount(String amount)  {
		amountTextBox.click();
		amountTextBox.clear();
		amountTextBox.sendKeys(amount);
		ExtentReportLog.testCaseInfo("Entered year:" + amount);
	}

	public void enterReason(String reason)  {
		reasonTextBox.click();
		reasonTextBox.clear();
		reasonTextBox.sendKeys(reason);
		ExtentReportLog.testCaseInfo("Entered year:" + reason);
	}

	public void clickResetButton()  {
		resetButton.click();
		ExtentReportLog.testCaseInfo("Reset button clicked.");
	}

	public void clickCreateExpenseButton()  {
		createExpenseButton.click();
		ExtentReportLog.testCaseInfo("Create expense button clicked.");
	}

	public void clickAddExpenseLink()  {
		addExpense.click();
		ExtentReportLog.testCaseInfo("Add expense Link clicked.");
	}

	public void clickListExpenseLink()  {
		listExpenses.click();
		ExtentReportLog.testCaseInfo("List expense Link clicked.");
	}

	public boolean isCategoryPresent(String category)  {
		categoryDropDown.click();
		ExtentReportLog.testCaseInfo("Selected category from dropdown " + category);
		Select drpCategory = new Select(categoryDropDown);
		java.util.List<WebElement> options = drpCategory.getOptions();
		for (WebElement item : options) {
			if (item.getText().equals(category)) {
				ExtentReportLog.testCaseInfo("Category present in dropdown:" + category);
				return true;
			}
		}
		ExtentReportLog.testCaseInfo("Category not present in dropdown:" + category);
		return false;
	}

}
