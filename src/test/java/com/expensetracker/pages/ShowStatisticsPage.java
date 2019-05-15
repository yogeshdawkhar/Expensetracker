package com.expensetracker.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.expensetracker.base.BaseClass;
import com.expensetracker.utility.ExtentReportLog;

public class ShowStatisticsPage extends BaseClass {

	@FindBy(how = How.ID, using = "logout")
	public WebElement logoutLink;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/table/tbody/tr[2]/td[2]")
	public WebElement expenseAmount;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/table/tbody/tr[3]/td[2]")
	public WebElement totalAmount;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/table/tbody/tr[2]/td[1]")
	public WebElement categoryLabel;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/table/tbody/tr[1]/th[2]")
	public WebElement valueLabel;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[1]/form/select")
	public WebElement yearDropdown;

	@FindBy(how = How.XPATH, using = "//*[@id=\\\"myChart\\\"]/svg/g[2]/text")
	public WebElement categoryNameOnChart;

	@FindBy(how = How.ID, using = "go_add_expense")
	public WebElement addExpense;

	@FindBy(how = How.ID, using = "go_list_expenses")
	public WebElement listExpenses;

	@FindBy(how = How.ID, using = "go_list_categories")
	public WebElement listCategories;

	@FindBy(how = How.ID, using = "go_show_statistics")
	public WebElement showStatistics;

	@FindBy(how = How.XPATH, using = "/html/body/div/h3")
	public WebElement testStatisticsHeading;

	public String placeHolder = "placeholder";

	public ShowStatisticsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getStatisticsHeadingHeading() {
		String heading = testStatisticsHeading.getText();
		ExtentReportLog.testCaseInfo("Statistics Heading :" + heading);
		return heading;
	}

	public boolean findCategoryNameOnChart() {
		return categoryNameOnChart.isDisplayed();
	}

	public void selectExpense(String expense) {
		yearDropdown.click();
		Select drpCategory = new Select(yearDropdown);
		drpCategory.selectByVisibleText(expense);
		ExtentReportLog.testCaseInfo("Selected Expense from dropdown " + expense);
	}

	public void clickShowStatistics() {
		showStatistics.click();
		ExtentReportLog.testCaseInfo("Show statistics clicked.");
	}

}
