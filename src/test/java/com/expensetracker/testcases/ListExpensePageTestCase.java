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

public class ListExpensePageTestCase extends BaseClass {
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
	public void verifyUserShouldNotableToUseListExpensesFeatureWithoutLogin() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		if (UtililtyFunctions.isElementExists(loginPageobj.logoutLink)) {
			loginPageobj.clickLogout();
		}
		loginPageobj.clickListExpenses();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}

	@Test
	public void verifyUserShouldbeAbleToUseListExpensesFeatureWithLogin() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		loginPageobj.clickListExpenses();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), listExpensesUrl,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyAddedExpenseOnListExpensesTable() throws IOException {

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
		AssertionUtil.assertEquals(
				String.valueOf(listExpensePageObj.isFristAddedRecordPresent(
						utililtyFunctionsobj.getDateInListExpenseFormat(day, month, year), category,
						utililtyFunctionsobj.getFormatedAmount(amount), reason)),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyAddedExpenseShouldNotbeVisibleToOtherUserOnListExpensesTable() throws IOException {

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
		AssertionUtil.assertEquals(
				String.valueOf(listExpensePageObj.isFristAddedRecordPresent(
						utililtyFunctionsobj.getDateInListExpenseFormat(day, month, year), category,
						utililtyFunctionsobj.getFormatedAmount(amount), reason)),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_PRESENT,
				ErrorMessageContants.ELEMENT_NOT_PRESENT);
		loginPageobj.clickLogout();
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		category = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		addExpensePageObj.clickListExpenseLink();

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

	@Test
	public void verifyEditIconFunctionalityOnListExpensesTable() throws IOException {

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
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), editExpensesUrl,
				ErrorMessageContants.USER_REDIRECTED_ON_NEXTPAGE, ErrorMessageContants.USER_NOT_REDIRECTED_ON_NEXTPAGE);
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

		AssertionUtil.assertEquals(listExpensePageObj.listExpensesHeading.getText(),
				ValidationMessageContants.HEADER_LIST_EXPENSES, ErrorMessageContants.HEADER_LIST_EXPENSES_PRESENT,
				ErrorMessageContants.HEADER_LIST_EXPENSES_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(listExpensePageObj.table.isEnabled()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_DISABLED,
				ErrorMessageContants.ELEMENT_NOT_DISABLED);

		AssertionUtil.assertEquals(listExpensePageObj.dateLabel.getText(), ValidationMessageContants.LABEL_DATE,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);

		AssertionUtil.assertEquals(listExpensePageObj.categoryLabel.getText(), ValidationMessageContants.LABEL_CATEGORY,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(listExpensePageObj.amountLabel.getText(),
				ValidationMessageContants.LABEL_AMOUNT_LIST, ErrorMessageContants.LABEL_PRESENT,
				ErrorMessageContants.LABEL_NOT_PRESENT);

		AssertionUtil.assertEquals(listExpensePageObj.reasonLabel.getText(), ValidationMessageContants.LABEL_REASON,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(listExpensePageObj.modifyLabel.getText(), ValidationMessageContants.LABEL_MODIFY,
				ErrorMessageContants.LABEL_PRESENT, ErrorMessageContants.LABEL_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(listExpensePageObj.navigateBackInactiveLessthan10Rec.isEnabled()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_DISABLED,
				ErrorMessageContants.ELEMENT_NOT_DISABLED);
		AssertionUtil.assertEquals(String.valueOf(listExpensePageObj.navigateForwardInactiveLessthan10Rec.isEnabled()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_DISABLED,
				ErrorMessageContants.ELEMENT_NOT_DISABLED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyNavigateButtonsareDisabledfor10Records() throws IOException {
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
		for (int i = 0; i < 10; i++) {
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
		}
		ListExpensePage listExpensePageObj = PageFactory.initElements(driver, ListExpensePage.class);
		AssertionUtil.assertEquals(
				listExpensePageObj.getAltValue(listExpensePageObj.navigateForwardInactiveLessthan10Rec),
				ValidationMessageContants.INACTIVE_RIGHT_ARROW, ErrorMessageContants.ELEMENT_ENABLED,
				ErrorMessageContants.ELEMENT_NOT_ENABLED);

		AssertionUtil.assertEquals(listExpensePageObj.getAltValue(listExpensePageObj.navigateBackInactiveLessthan10Rec),
				ValidationMessageContants.INACTIVE_LEFT_ARROW, ErrorMessageContants.ELEMENT_ENABLED,
				ErrorMessageContants.ELEMENT_NOT_ENABLED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyForwardAndbackwardButtonsFunctionalityformorethan10Records() throws IOException {
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
		for (int i = 0; i < 15; i++) {
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
		}
		ListExpensePage listExpensePageObj = PageFactory.initElements(driver, ListExpensePage.class);
		AssertionUtil.assertEquals(listExpensePageObj.getAltValue(listExpensePageObj.navigateForwardActive),
				ValidationMessageContants.ACTIVE_RIGHT_ARROW, ErrorMessageContants.ELEMENT_ENABLED,
				ErrorMessageContants.ELEMENT_NOT_ENABLED);

		AssertionUtil.assertEquals(listExpensePageObj.getAltValue(listExpensePageObj.navigateBackInactiveMorethan10Rec),
				ValidationMessageContants.INACTIVE_LEFT_ARROW, ErrorMessageContants.ELEMENT_ENABLED,
				ErrorMessageContants.ELEMENT_NOT_ENABLED);
		listExpensePageObj.navigateForwardActive.click();
		AssertionUtil.assertEquals(
				String.valueOf(UtililtyFunctions
						.isCurrentUrlSame("http://thawing-shelf-73260.herokuapp.com/listexpenses?page=2")),
				ValidationMessageContants.BTRUE, ErrorMessageContants.USER_REDIRECTED_ON_NEXTPAGE,
				ErrorMessageContants.USER_NOT_REDIRECTED_ON_NEXTPAGE);

		AssertionUtil.assertEquals(listExpensePageObj.getAltValue(listExpensePageObj.navigateBackActive),
				ValidationMessageContants.ACTIVE_LEFT_ARROW, ErrorMessageContants.ELEMENT_ENABLED,
				ErrorMessageContants.ELEMENT_NOT_ENABLED);
		listExpensePageObj.navigateBackActive.click();
		AssertionUtil.assertEquals(
				String.valueOf(UtililtyFunctions
						.isCurrentUrlSame("http://thawing-shelf-73260.herokuapp.com/listexpenses?page=1")),
				ValidationMessageContants.BTRUE, ErrorMessageContants.USER_REDIRECTED_ON_NEXTPAGE,
				ErrorMessageContants.USER_NOT_REDIRECTED_ON_NEXTPAGE);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyNumberofRecordsOnListExpensesShouldnotBeMoreThan10() throws IOException {
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
		for (int i = 0; i < 15; i++) {
			addExpensePageObj.clickAddExpenseLink();
			UtcDate utc = new UtcDate();
			String day = utc.getDate();
			String month = utc.getMonth();
			String year = utc.getYear();
			String amount = "10.00";
			String reason = UtililtyFunctions.generateRandomStringwithalphabetandNumber(6);
			addExpensePageObj.enterExpenseDetailsAndClickCreateExpense(day, month, year, category, amount, reason);
			addExpensePageObj.clickListExpenseLink();
		}
		AssertionUtil.assertEquals(utililtyFunctionsobj.getTableRowCount(), ValidationMessageContants.MAX_ROW_ON_TABLE,
				ErrorMessageContants.ELEMENT_DISABLED, ErrorMessageContants.ELEMENT_NOT_DISABLED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyNumberofRecordsOnListExpensesWithoutanyData() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		loginPageobj.clickListExpenses();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getTableRowCount(), 0, ErrorMessageContants.ELEMENT_DISABLED,
				ErrorMessageContants.ELEMENT_NOT_DISABLED);
		loginPageobj.clickLogout();
	}
}
