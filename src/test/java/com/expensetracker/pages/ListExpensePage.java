package com.expensetracker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.expensetracker.base.BaseClass;
import com.expensetracker.utility.ExtentReportLog;

public class ListExpensePage extends BaseClass {

	@FindBy(how = How.XPATH, using = "/html/body/div/table/tbody/tr[1]/td[4]")
	public WebElement reasonData;

	@FindBy(how = How.XPATH, using = "/html/body/div/table/tbody/tr[1]/td[3]")
	public WebElement amountData;

	@FindBy(how = How.XPATH, using = "/html/body/div/table/tbody/tr[1]/td[2]")
	public WebElement categoryData;

	@FindBy(how = How.XPATH, using = "/html/body/div/table/tbody/tr[1]/td[1]")
	public WebElement dateData;
	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr[1]/th/img[1]")
	public WebElement navigateBackInactiveLessthan10Rec;
	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr[1]/th/img")
	public WebElement navigateBackInactiveMorethan10Rec;
	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr[1]/th/a/img")
	public WebElement navigateBackActive;

	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr[1]/th/img[2]")
	public WebElement navigateForwardInactiveLessthan10Rec;

	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr[1]/th/a/img")
	public WebElement navigateForwardActive;

	@FindBy(how = How.XPATH, using = "/html/body/div/table/tbody/tr[1]/td[5]/a[1]/img")
	public WebElement editFrist;
	@FindBy(how = How.XPATH, using = "/html/body/div/table/tbody/tr[1]/td[5]/a[2]/img")
	public WebElement copyFrist;
	@FindBy(how = How.XPATH, using = "/html/body/div/table/tbody/tr[1]/td[5]/a[3]/img")
	public WebElement deleteFrist;
	@FindBy(how = How.ID, using = "logout")
	public WebElement logoutLink;

	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr[2]/th[1]")
	public WebElement dateLabel;
	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr[2]/th[2]")
	public WebElement categoryLabel;
	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr[2]/th[3]")
	public WebElement amountLabel;
	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr[2]/th[4]")
	public WebElement reasonLabel;
	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr[2]/th[5]")
	public WebElement modifyLabel;
	@FindBy(how = How.XPATH, using = "/html/body/nav/div/div[1]/a")
	public WebElement expenseTracker;

	@FindBy(how = How.XPATH, using = "/html/body/div/table")
	public WebElement table;

	@FindBy(how = How.XPATH, using = "/html/body/div/table/tbody/tr")
	public WebElement tableRow;

	@FindBy(how = How.ID, using = "go_add_expense")
	public WebElement addExpense;

	@FindBy(how = How.ID, using = "go_list_expenses")
	public WebElement listExpenses;

	@FindBy(how = How.ID, using = "go_list_categories")
	public WebElement listCategories;

	@FindBy(how = How.ID, using = "go_show_statistics")
	public WebElement showStatistics;

	@FindBy(how = How.ID, using = "editaccount")
	public WebElement editAccount;

	@FindBy(how = How.XPATH, using = "/html/body/div/h1")
	public WebElement listExpensesHeading;

	public String placeHolder = "placeholder";

	public ListExpensePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickListCategoriesLink() {
		listCategories.click();
		ExtentReportLog.testCaseInfo("ListCategories clicked.");
	}

	public void clickEditExpense() {
		editFrist.click();
		ExtentReportLog.testCaseInfo("Edit clicked.");
	}

	public void clickDeleteExpense() {
		deleteFrist.click();
		ExtentReportLog.testCaseInfo("Delete Expense clicked.");
	}

	public String getListExpenseHeading() {
		String heading = listExpensesHeading.getText();
		ExtentReportLog.testCaseInfo("List Expense Heading :" + heading);
		return heading;
	}

	public boolean findRecord(String category) {
		WebElement find = driver.findElement(By.xpath("//*[text()='" + category + "']"));
		return find.isDisplayed();
	}

	public String getAltValue(WebElement ele) {
		return ele.getAttribute("alt");
	}

	public boolean isFristAddedRecordPresent(String date, String category, String amount, String reason) {
		boolean date1 = dateData.getText().equalsIgnoreCase(date);
		boolean cat1 = categoryData.getText().equalsIgnoreCase(category);
		boolean amt1 = amountData.getText().equalsIgnoreCase(amount);
		boolean res1 = reasonData.getText().equalsIgnoreCase(reason);
		if ((date1 == true) && (cat1 == true) && (amt1 == true) && (res1 == true)) {
			return true;
		} else {
			return false;
		}
	}

}
