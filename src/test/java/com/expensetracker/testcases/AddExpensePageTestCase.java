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

public class AddExpensePageTestCase extends BaseClass {
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
	public void verifyUserShouldNotableToUseAddExpenseFeatureWithoutLogin() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		if (UtililtyFunctions.isElementExists(loginPageobj.logoutLink)) {
			loginPageobj.clickLogout();
		}
		loginPageobj.clickAddExpenseLink();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyAddExpenseFunctionalityOnAddExpensePage() throws IOException {

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
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), listExpensesUrl,
				ErrorMessageContants.USER_REDIRECTED_ON_NEXTPAGE, ErrorMessageContants.USER_NOT_REDIRECTED_ON_NEXTPAGE);
		ListExpensePage listExpensePageObj = PageFactory.initElements(driver, ListExpensePage.class);

		AssertionUtil.assertEquals(
				String.valueOf(listExpensePageObj.isFristAddedRecordPresent(
						utililtyFunctionsobj.getDateInListExpenseFormat(day, month, year), category,
						utililtyFunctionsobj.getFormatedAmount(amount), reason)),
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense("0", utc.getMonth(), utc.getYear(), category,
				"10.00", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.dayTextBox),
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense("32", utc.getMonth(), utc.getYear(), category,
				"10.00", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.dayTextBox),
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense("", utc.getMonth(), utc.getYear(), category, "10.00",
				"shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.dayTextBox),
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(utc.getDate(), "0", utc.getYear(), category, "10.00",
				"shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.monthTextBox),
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(utc.getDate(), "13", utc.getYear(), category,
				"10.00", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.monthTextBox),
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(utc.getDate(), "", utc.getYear(), category, "10.00",
				"shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.monthTextBox),
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(utc.getDate(), utc.getMonth(), "1999", category,
				"10.00", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.yearTextBox),
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(utc.getDate(), utc.getMonth(), "2101", category,
				"10.00", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.yearTextBox),
				ValidationMessageContants.ERROR_MONTH_LESS_THAN_OR_EQUAL_TO_2100,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyYearFieldValidationForEmptyDateonAddExpensePage() throws IOException {
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(utc.getDate(), utc.getMonth(), "", category, "10.00",
				"shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.yearTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD, ErrorMessageContants.REQUIRED_ERROR_PRESENT,
				ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyCategoryFieldValidationForNoneCategoryCreatedOnAddExpensePage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		AddExpensePage addExpensePageObj = PageFactory.initElements(driver, AddExpensePage.class);
		addExpensePageObj.clickAddExpenseLink();
		UtcDate utc = new UtcDate();
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpenseWithoutCategory(utc.getDate(), utc.getMonth(),
				utc.getYear(), "10.00", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.categoryDropDown),
				ValidationMessageContants.ERROR_SELECT_CATEGORY, ErrorMessageContants.REQUIRED_ERROR_PRESENT,
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(utc.getDate(), utc.getMonth(), utc.getYear(),
				category, "", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.amountTextBox),
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(utc.getDate(), utc.getMonth(), utc.getYear(),
				category, "dcdsds", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.amountTextBox),
				ValidationMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyAmountFieldValidationForSpaceDataOnAddExpensePage() throws IOException {
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(utc.getDate(), utc.getMonth(), utc.getYear(),
				category, "  ", "shopping");
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.amountTextBox),
				ValidationMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyReasonFieldValidationForDatalessThan3CharactersOnAddExpensePage() throws IOException {
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
		addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(utc.getDate(), utc.getMonth(), utc.getYear(),
				category, "10", UtililtyFunctions.generateRandomStringwithalphabetandNumber(2));
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(addExpensePageObj.reasonTextBox),
				ValidationMessageContants.ERROR_PLEASE_MATCH_FORMAT_REQUESTED,
				ErrorMessageContants.REQUIRED_ERROR_PRESENT, ErrorMessageContants.REQUIRED_ERROR_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyPlaceHolderLabelButtonsOnAddExpensePage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);

		AddExpensePage addExpensePageObj = PageFactory.initElements(driver, AddExpensePage.class);
		addExpensePageObj.clickAddExpenseLink();

		AssertionUtil.assertEquals(addExpensePageObj.addExpenseHeading.getText(),
				ValidationMessageContants.HEADER_ADD_EXPENSE, ErrorMessageContants.HEADER_ADD_EXPENSE_PRESENT,
				ErrorMessageContants.HEADER_ADD_EXPENSE_NOT_PRESENT);
		AssertionUtil.assertEquals(addExpensePageObj.dateLabel.getText(), ValidationMessageContants.LABEL_DATE,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(addExpensePageObj.dayLabel.getText(), ValidationMessageContants.LABEL_DAY,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(addExpensePageObj.dayTextBox.getAttribute(addExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_DAY, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(addExpensePageObj.monthLabel.getText(), ValidationMessageContants.LABEL_MONTH,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(addExpensePageObj.monthTextBox.getAttribute(addExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_MONTH, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(addExpensePageObj.yearLabel.getText(), ValidationMessageContants.LABEL_YEAR,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(addExpensePageObj.yearTextBox.getAttribute(addExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_YEAR, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(addExpensePageObj.categoryLabel.getText(), ValidationMessageContants.LABEL_CATEGORY,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(addExpensePageObj.categoryDropDown.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);

		AssertionUtil.assertEquals(addExpensePageObj.amountLabel.getText(), ValidationMessageContants.LABEL_AMOUNT,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(addExpensePageObj.amountTextBox.getAttribute(addExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_AMOUNT, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);

		AssertionUtil.assertEquals(addExpensePageObj.reasonLabel.getText(), ValidationMessageContants.LABEL_REASON,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(addExpensePageObj.amountTextBox.getAttribute(addExpensePageObj.placeHolder),
				ValidationMessageContants.PLACEHOLDER_AMOUNT, ErrorMessageContants.PLACEHOLDER_PRESENT,
				ErrorMessageContants.PLACEHOLDER_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(addExpensePageObj.resetButton.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(addExpensePageObj.createExpenseButton.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		loginPageobj.clickLogout();
	}
}
