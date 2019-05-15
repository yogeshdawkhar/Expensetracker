package com.expensetracker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.expensetracker.base.BaseClass;
import com.expensetracker.utility.ExtentReportLog;

public class ListCategoriesPage extends BaseClass {

	@FindBy(how = How.ID, using = "reset")
	public WebElement resetButton;

	@FindBy(how = How.ID, using = "logout")
	public WebElement logoutLink;

	@FindBy(how = How.XPATH, using = "/html/body/div/table/tbody/tr/td[2]/a[1]/img")
	public WebElement editCategory;

	@FindBy(how = How.XPATH, using = "/html/body/div/table/tbody/tr/td[2]/a[2]/img")
	public WebElement deleteCategory;

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

	@FindBy(how = How.ID, using = "editaccount")
	public WebElement editAccount;

	@FindBy(how = How.XPATH, using = "/html/body/div/h1")
	public WebElement listCategoriesHeading;

	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr/th[1]")
	public WebElement tableHeaderName;
	@FindBy(how = How.XPATH, using = "/html/body/div/table/thead/tr/th[2]")
	public WebElement tableHeaderModify;

	public String placeHolder = "placeholder";

	public ListCategoriesPage(WebDriver driver) {
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

	public void clickEditCategory() {
		editCategory.click();
		ExtentReportLog.testCaseInfo("Edit categories clicked.");
	}

	public void clickDeleteCategory() {
		deleteCategory.click();
		ExtentReportLog.testCaseInfo("Delete categories clicked.");
	}

	public String getListCategoriesHeading() {
		String heading = listCategoriesHeading.getText();
		ExtentReportLog.testCaseInfo("List Categories Heading :" + heading);
		return heading;
	}

	public boolean findCategory(String category) {
		try {
			driver.findElement(By.xpath("//*[text()='" + category + "']"));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
