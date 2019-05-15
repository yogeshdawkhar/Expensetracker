package com.expensetracker.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.expensetracker.base.BaseClass;
import com.expensetracker.constants.ErrorMessageContants;
import com.expensetracker.constants.ValidationMessageContants;
import com.expensetracker.pages.AddCategoryPage;
import com.expensetracker.pages.AddExpensePage;
import com.expensetracker.pages.LoginPage;
import com.expensetracker.pages.RegisterUserPage;
import com.expensetracker.pages.ShowStatisticsPage;
import com.expensetracker.utility.AssertionUtil;
import com.expensetracker.utility.ExtentReportLog;
import com.expensetracker.utility.UtcDate;
import com.expensetracker.utility.UtililtyFunctions;

public class ShowStatisticsPageTestCase extends BaseClass {
	String userName;
	String password;
	String category;

	public void createUser(String userName, String password) throws IOException {
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		loginPageobj.clickRegisterNewUser();
		RegisterUserPage registerUserPageObj = PageFactory.initElements(driver, RegisterUserPage.class);
		registerUserPageObj.enterDetailsAndClickRegister(userName, password, password);
	}

	@Test
	public void verifyPlaceHolderLabelButtonsOnStatisticsPage() throws IOException {

		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		category = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		AddExpensePage addExpensePageObj = PageFactory.initElements(driver, AddExpensePage.class);
		addExpensePageObj.clickAddExpenseLink();
		UtcDate utc = new UtcDate();
		String day = utc.getDate();
		String month = utc.getMonth();
		String year = utc.getYear();
		String amount = "10.00";
		String reason = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(day, month, year, category, amount, reason);
		addExpensePageObj.clickListExpenseLink();
		ShowStatisticsPage showExpensePageObj = PageFactory.initElements(driver, ShowStatisticsPage.class);
		showExpensePageObj.clickShowStatistics();
		AssertionUtil.assertEquals(showExpensePageObj.getStatisticsHeadingHeading(),
				ValidationMessageContants.HEADER_STATISTICS, ErrorMessageContants.HEADER_EDIT_EXPENSE_PRESENT,
				ErrorMessageContants.HEADER_EDIT_EXPENSE_NOT_PRESENT);
		AssertionUtil.assertEquals(showExpensePageObj.categoryLabel.getText(), category,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(showExpensePageObj.totalAmount.getText(),
				utililtyFunctionsobj.getFormatedAmount(amount), ErrorMessageContants.LABEL_PRESENT,
				ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(showExpensePageObj.valueLabel.getText(), ValidationMessageContants.LABEL_VALUE,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyAddedRecordOnStatisticsPage() throws IOException {

		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		category = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		AddExpensePage addExpensePageObj = PageFactory.initElements(driver, AddExpensePage.class);
		addExpensePageObj.clickAddExpenseLink();
		UtcDate utc = new UtcDate();
		String day = utc.getDate();
		String month = utc.getMonth();
		String year = utc.getYear();
		String amount = "10.00";
		String reason = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(day, month, year, category, amount, reason);
		addExpensePageObj.clickListExpenseLink();
		ShowStatisticsPage showExpensePageObj = PageFactory.initElements(driver, ShowStatisticsPage.class);
		showExpensePageObj.clickShowStatistics();
		String expense = year + "." + month;
		showExpensePageObj.selectExpense(expense);
		AssertionUtil.assertEquals(showExpensePageObj.totalAmount.getText(),
				utililtyFunctionsobj.getFormatedAmount(amount), ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		AssertionUtil.assertEquals(showExpensePageObj.expenseAmount.getText(),
				utililtyFunctionsobj.getFormatedAmount(amount), ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		loginPageobj.clickLogout();
	}
}
