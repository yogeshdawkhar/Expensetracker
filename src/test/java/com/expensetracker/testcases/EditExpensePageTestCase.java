package com.expensetracker.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.expensetracker.base.BaseClass;
import com.expensetracker.constants.ErrorMessageContants;
import com.expensetracker.constants.ValidationMessageContants;
import com.expensetracker.pages.AddCategoryPage;
import com.expensetracker.pages.AddExpensePage;
import com.expensetracker.pages.EditExpensePage;
import com.expensetracker.pages.ListExpensePage;
import com.expensetracker.pages.LoginPage;
import com.expensetracker.pages.RegisterUserPage;
import com.expensetracker.utility.AssertionUtil;
import com.expensetracker.utility.ExtentReportLog;
import com.expensetracker.utility.UtcDate;
import com.expensetracker.utility.UtililtyFunctions;

public class EditExpensePageTestCase extends BaseClass {
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
	public void verifyPlaceHolderLabelButtonsOnEditExpensesPage() throws IOException {

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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		AssertionUtil.assertEquals(editExpensePageObj.getEditExpenseHeading(),
				ValidationMessageContants.HEADER_EDIT_EXPENSES, ErrorMessageContants.HEADER_EDIT_EXPENSE_PRESENT,
				ErrorMessageContants.HEADER_EDIT_EXPENSE_NOT_PRESENT);
		AssertionUtil.assertEquals(editExpensePageObj.dateLabel.getText(), ValidationMessageContants.LABEL_DATE,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(editExpensePageObj.dayLabel.getText(), ValidationMessageContants.LABEL_DAY,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(editExpensePageObj.dayTextBox.getAttribute(editExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_DAY, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(editExpensePageObj.monthLabel.getText(), ValidationMessageContants.LABEL_MONTH,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(editExpensePageObj.monthTextBox.getAttribute(editExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_MONTH, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(editExpensePageObj.yearLabel.getText(), ValidationMessageContants.LABEL_YEAR,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(editExpensePageObj.yearTextBox.getAttribute(editExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_YEAR, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(editExpensePageObj.categoryLabel.getText(), ValidationMessageContants.LABEL_CATEGORY,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(editExpensePageObj.categoryDropDown.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);

		AssertionUtil.assertEquals(editExpensePageObj.amountLabel.getText(), ValidationMessageContants.LABEL_AMOUNT,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(editExpensePageObj.amountTextBox.getAttribute(editExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_AMOUNT, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(editExpensePageObj.reasonLabel.getText(), ValidationMessageContants.LABEL_REASON,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(editExpensePageObj.amountTextBox.getAttribute(editExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_AMOUNT, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(editExpensePageObj.resetButton.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(editExpensePageObj.saveExpenseButton.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifySaveExpenseFunctionalityonEditExpensesPage() throws IOException {

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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		UtcDate utc1 = new UtcDate();
		String day1 = utc1.getDate();
		String month1 = utc1.getMonth();
		String year1 = utc1.getYear();
		String amount1 = "10.00";
		String reason1 = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense(day1, month1, year1, category, amount1, reason1);

		AssertionUtil.assertEquals(
				String.valueOf(listExpensePageObj.isFristAddedRecordPresent(
						utililtyFunctionsobj.getDateInListExpenseFormat(day1, month1, year1), category,
						utililtyFunctionsobj.getFormatedAmount(amount1), reason1)),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyDayFieldValidationForNumberlessThan1AddExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense("0", utc.getMonth(), utc.getYear(), category, "10.00",
				"shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.dayTextBox),
				ValidationMessageContants.ERROR_DAY_GREATER_THAN_OR_EQUAL_TO_1,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyDayFieldValidationForNumberGreaterThan31onAddExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense("32", utc.getMonth(), utc.getYear(), category,
				"10.00", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.dayTextBox),
				ValidationMessageContants.ERROR_DAY_LESS_THAN_OR_EQUAL_TO_31,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyDayFieldValidationForEmptyDateonAddExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense("", utc.getMonth(), utc.getYear(), category, "10.00",
				"shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.dayTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD, ErrorMessageContants.REQUIRED_ERROR_PRESENT,
				ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyMonthFieldValidationForNumberlessThan1AddExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense(utc.getDate(), "0", utc.getYear(), category, "10.00",
				"shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.monthTextBox),
				ValidationMessageContants.ERROR_MONTH_GREATER_THAN_OR_EQUAL_TO_1,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyMonthFieldValidationForNumberGreaterThan12onAddExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense(utc.getDate(), "13", utc.getYear(), category, "10.00",
				"shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.monthTextBox),
				ValidationMessageContants.ERROR_MONTH_LESS_THAN_OR_EQUAL_TO_12,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyMonthFieldValidationForEmptyDateonAddExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense(utc.getDate(), "", utc.getYear(), category, "10.00",
				"shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.monthTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD, ErrorMessageContants.REQUIRED_ERROR_PRESENT,
				ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyYearFieldValidationForNumberlessThan2000AddExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense(utc.getDate(), utc.getMonth(), "1999", category,
				"10.00", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.yearTextBox),
				ValidationMessageContants.ERROR_YEAR_GREATER_THAN_OR_EQUAL_TO_2000,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyYearFieldValidationForNumberGreaterThan2100onAddExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense(utc.getDate(), utc.getMonth(), "2101", category,
				"10.00", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.yearTextBox),
				ValidationMessageContants.ERROR_MONTH_LESS_THAN_OR_EQUAL_TO_2100,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyYearFieldValidationForEmptyDateonEditExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense(utc.getDate(), utc.getMonth(), "", category, "10.00",
				"shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.yearTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD, ErrorMessageContants.REQUIRED_ERROR_PRESENT,
				ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyAmountFieldValidationForEmptyDataOnAddExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense(utc.getDate(), utc.getMonth(), utc.getYear(),
				category, "", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.amountTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD, ErrorMessageContants.REQUIRED_ERROR_PRESENT,
				ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyAmountFieldValidationForNonNumericDataOnAddExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense(utc.getDate(), utc.getMonth(), utc.getYear(),
				category, "dcdsds", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.amountTextBox),
				ValidationMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyAmountFieldValidationForSpaceDataOnEditExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense(utc.getDate(), utc.getMonth(), utc.getYear(),
				category, "  ", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.amountTextBox),
				ValidationMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyReasonFieldValidationForDatalessThan3CharactersOnEditExpensePage() throws IOException {
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
		listExpensePageObj.editFrist.click();
		EditExpensePage editExpensePageObj = PageFactory.initElements(driver, EditExpensePage.class);
		editExpensePageObj.enterExpenseDetailsAndClickSaveExpense(utc.getDate(), utc.getMonth(), utc.getYear(),
				category, "10", UtililtyFunctions.generateRandomStringwithalphabetandNumber(2));
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editExpensePageObj.reasonTextBox),
				ValidationMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}
}
