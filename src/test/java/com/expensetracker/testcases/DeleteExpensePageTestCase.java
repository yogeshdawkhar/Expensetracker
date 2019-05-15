
package com.expensetracker.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.expensetracker.base.BaseClass;
import com.expensetracker.constants.ErrorMessageContants;
import com.expensetracker.constants.ValidationMessageContants;
import com.expensetracker.pages.AddCategoryPage;
import com.expensetracker.pages.AddExpensePage;
import com.expensetracker.pages.ListExpensePage;
import com.expensetracker.pages.LoginPage;
import com.expensetracker.pages.RegisterUserPage;
import com.expensetracker.utility.AssertionUtil;
import com.expensetracker.utility.ExtentReportLog;
import com.expensetracker.utility.UtcDate;
import com.expensetracker.utility.UtililtyFunctions;

public class DeleteExpensePageTestCase extends BaseClass {
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
	public void verifyDeleteCancleIconFunctionalityOnListExpensesTable() throws IOException {
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
		ListExpensePage listExpensePageObj = PageFactory.initElements(driver, ListExpensePage.class);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getAlertTextAndCancelAction(listExpensePageObj.deleteFrist),
				ValidationMessageContants.ERROR_DELETE_POPUP + reason + " ?",
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);

		if (utililtyFunctionsobj.getTableRowCount() > 0) {
			AssertionUtil.assertEquals(
					String.valueOf(listExpensePageObj.isFristAddedRecordPresent(
							utililtyFunctionsobj.getDateInListExpenseFormat(day, month, year), category,
							utililtyFunctionsobj.getFormatedAmount(amount), reason)),
					ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
					ErrorMessageContants.ELEMENT_NOT_PRESENT);
		} else {
			AssertionUtil.assertFail(ErrorMessageContants.ELEMENT_PRESENT);
		}
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyDeleteIconFunctionalityOnListExpensesTable() throws IOException {
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
		ListExpensePage listExpensePageObj = PageFactory.initElements(driver, ListExpensePage.class);
		AssertionUtil.assertEquals(utililtyFunctionsobj.clickButtonGetAlertText(listExpensePageObj.deleteFrist),
				ValidationMessageContants.ERROR_DELETE_POPUP + reason + " ?",
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);

		if (utililtyFunctionsobj.getTableRowCount() > 0) {
			AssertionUtil.assertEquals(
					String.valueOf(listExpensePageObj.isFristAddedRecordPresent(
							utililtyFunctionsobj.getDateInListExpenseFormat(day, month, year), category,
							utililtyFunctionsobj.getFormatedAmount(amount), reason)),
					ValidationMessageContants.BFALSE, ErrorMessageContants.ELEMENT_PRESENT,
					ErrorMessageContants.ELEMENT_NOT_PRESENT);
		} else {
			AssertionUtil.assertPass(ErrorMessageContants.ELEMENT_NOT_PRESENT);
		}
		loginPageobj.clickLogout();
	}
}