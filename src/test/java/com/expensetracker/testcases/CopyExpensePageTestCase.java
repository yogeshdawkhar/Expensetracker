package com.expensetracker.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.expensetracker.base.BaseClass;
import com.expensetracker.constants.ErrorMessageContants;
import com.expensetracker.constants.ValidationMessageContants;
import com.expensetracker.pages.AddCategoryPage;
import com.expensetracker.pages.AddExpensePage;
import com.expensetracker.pages.CopyExpensePage;
import com.expensetracker.pages.ListExpensePage;
import com.expensetracker.pages.LoginPage;
import com.expensetracker.pages.RegisterUserPage;
import com.expensetracker.utility.AssertionUtil;
import com.expensetracker.utility.ExtentReportLog;
import com.expensetracker.utility.UtcDate;
import com.expensetracker.utility.UtililtyFunctions;

public class CopyExpensePageTestCase extends BaseClass {
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
	public void verifyPlaceHolderLabelButtonsOnCopyExpensesPage() throws IOException {

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
		listExpensePageObj.copyFrist.click();
		CopyExpensePage copyExpensePageObj = PageFactory.initElements(driver, CopyExpensePage.class);
		AssertionUtil.assertEquals(copyExpensePageObj.addExpenseHeading.getText(),
				ValidationMessageContants.HEADER_ADD_EXPENSE, ErrorMessageContants.HEADER_ADD_EXPENSE_PRESENT,
				ErrorMessageContants.HEADER_ADD_EXPENSE_NOT_PRESENT);
		AssertionUtil.assertEquals(copyExpensePageObj.dateLabel.getText(), ValidationMessageContants.LABEL_DATE,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(copyExpensePageObj.dayLabel.getText(), ValidationMessageContants.LABEL_DAY,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(copyExpensePageObj.dayTextBox.getAttribute(copyExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_DAY, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(copyExpensePageObj.monthLabel.getText(), ValidationMessageContants.LABEL_MONTH,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(copyExpensePageObj.monthTextBox.getAttribute(copyExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_MONTH, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(copyExpensePageObj.yearLabel.getText(), ValidationMessageContants.LABEL_YEAR,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(copyExpensePageObj.yearTextBox.getAttribute(copyExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_YEAR, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(copyExpensePageObj.categoryLabel.getText(), ValidationMessageContants.LABEL_CATEGORY,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(copyExpensePageObj.categoryDropDown.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);

		AssertionUtil.assertEquals(copyExpensePageObj.amountLabel.getText(), ValidationMessageContants.LABEL_AMOUNT,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(copyExpensePageObj.amountTextBox.getAttribute(copyExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_AMOUNT, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(copyExpensePageObj.reasonLabel.getText(), ValidationMessageContants.LABEL_REASON,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(copyExpensePageObj.amountTextBox.getAttribute(copyExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_AMOUNT, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(copyExpensePageObj.resetButton.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(copyExpensePageObj.createExpenseButton.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifySavingCopiedExpenseFunctionality() throws IOException {

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
		listExpensePageObj.copyFrist.click();
		CopyExpensePage copyExpensePageObj = PageFactory.initElements(driver, CopyExpensePage.class);
		UtcDate utc1 = new UtcDate();
		String day1 = utc1.getDate();
		String month1 = utc1.getMonth();
		String year1 = utc1.getYear();
		String amount1 = "10.00";
		String reason1 = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		copyExpensePageObj.enterExpenseDetailsAndClickCreateExpense(day1, month1, year1, category, amount1, reason1);
		addExpensePageObj.clickListExpenseLink();
		AssertionUtil.assertEquals(
				String.valueOf(listExpensePageObj.isFristAddedRecordPresent(
						utililtyFunctionsobj.getDateInListExpenseFormat(day1, month1, year1), category,
						utililtyFunctionsobj.getFormatedAmount(amount1), reason1)),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		loginPageobj.clickLogout();
	}
}
