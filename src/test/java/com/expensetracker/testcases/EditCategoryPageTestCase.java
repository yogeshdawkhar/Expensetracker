package com.expensetracker.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.expensetracker.base.BaseClass;
import com.expensetracker.constants.ErrorMessageContants;
import com.expensetracker.constants.ValidationMessageContants;
import com.expensetracker.pages.AddCategoryPage;
import com.expensetracker.pages.EditCategoryPage;
import com.expensetracker.pages.ListCategoriesPage;
import com.expensetracker.pages.LoginPage;
import com.expensetracker.pages.RegisterUserPage;
import com.expensetracker.utility.AssertionUtil;
import com.expensetracker.utility.ExtentReportLog;
import com.expensetracker.utility.UtililtyFunctions;

public class EditCategoryPageTestCase extends BaseClass {
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
	public void verifyCategoryNameisSameWhenUserClicksEditCategory() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);

		category = UtililtyFunctions.generateRandomString(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);
		listCategoryPageObj.clickEditCategory();
		EditCategoryPage editCategoryPageObj = PageFactory.initElements(driver, EditCategoryPage.class);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValue(editCategoryPageObj.categoryNameTextBox), category,
				ErrorMessageContants.EXPECTED_VALUE_PRESENT_IN_TEXTBOX,
				ErrorMessageContants.EXPECTED_VALUE_NOT_PRESENT_IN_TEXTBOX);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyPlaceholderLabelInputfieldAndButtonsOnEditCategoryPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);

		category = UtililtyFunctions.generateRandomString(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);
		listCategoryPageObj.clickEditCategory();
		EditCategoryPage editCategoryPageObj = PageFactory.initElements(driver, EditCategoryPage.class);
		editCategoryPageObj.clearCategoryTextBox();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getPlaceHolder(editCategoryPageObj.categoryNameTextBox),
				ValidationMessageContants.PLACEHOLDER_CATEGORY, ErrorMessageContants.PLACEHOLDER_CATEGORY_PRESENT,
				ErrorMessageContants.PLACEHOLDER_CATEGORY_NOT_PRESENT);
		AssertionUtil.assertEquals(utililtyFunctionsobj.getText(editCategoryPageObj.nameLabel),
				ValidationMessageContants.LABEL_NAME, ErrorMessageContants.LABEL_NAME_PRESENT,
				ErrorMessageContants.LABEL_NAME_NOT_PRESENT);
		AssertionUtil.assertEquals(editCategoryPageObj.getEditCategoriesHeading(),
				ValidationMessageContants.HEADER_EDIT_CATEGORIES, ErrorMessageContants.HEADER_EDIT_CATEGORY_PRESENT,
				ErrorMessageContants.HEADER_EDIT_CATEGORY_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(editCategoryPageObj.resetButton.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.BUTTON_IS_DISPLAYED,
				ErrorMessageContants.BUTTON_IS_NOT_DISPLAYED);
		AssertionUtil.assertEquals(String.valueOf(editCategoryPageObj.saveCategoryBtn.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.BUTTON_IS_DISPLAYED,
				ErrorMessageContants.BUTTON_IS_NOT_DISPLAYED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyEditIconDisplayedFrontofCategory() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);

		category = UtililtyFunctions.generateRandomString(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.editCategory.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ICON_EDIT_PRESENT,
				ErrorMessageContants.ICON_EDIT_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyEditIconClickableFunctionality() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);

		category = UtililtyFunctions.generateRandomString(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);
		listCategoryPageObj.clickEditCategory();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), editCategoryUrl,
				ErrorMessageContants.USER_REDIRECTED_ON_NEXTPAGE, ErrorMessageContants.USER_NOT_REDIRECTED_ON_NEXTPAGE);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifySaveCategoryFunctionalityWithoutChangingCategoryName() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		category = UtililtyFunctions.generateRandomString(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);
		listCategoryPageObj.clickEditCategory();
		EditCategoryPage editCategoryPageObj = PageFactory.initElements(driver, EditCategoryPage.class);
		editCategoryPageObj.clickSaveCategoryBtn();
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.findCategory(category)),
				ValidationMessageContants.BTRUE, ErrorMessageContants.CATEGORY_DISPLAYED,
				ErrorMessageContants.CATEGORY_NOT_DISPLAYED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifySaveCategoryFunctionalityWithChangingCategoryName() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		category = UtililtyFunctions.generateRandomString(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);
		listCategoryPageObj.clickEditCategory();
		category = UtililtyFunctions.generateRandomString(4);
		EditCategoryPage editCategoryPageObj = PageFactory.initElements(driver, EditCategoryPage.class);
		editCategoryPageObj.enterCategoryName(category);
		editCategoryPageObj.clickSaveCategoryBtn();
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.findCategory(category)),
				ValidationMessageContants.BTRUE, ErrorMessageContants.CATEGORY_DISPLAYED,
				ErrorMessageContants.CATEGORY_NOT_DISPLAYED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyResetButtonFunctionalityOnEditCategoryPage() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		category = UtililtyFunctions.generateRandomString(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);
		listCategoryPageObj.clickEditCategory();
		EditCategoryPage editCategoryPageObj = PageFactory.initElements(driver, EditCategoryPage.class);
		editCategoryPageObj.enterCategoryName(UtililtyFunctions.generateRandomString(4));
		editCategoryPageObj.clickReset();

		AssertionUtil.assertEquals(utililtyFunctionsobj.getValue(editCategoryPageObj.categoryNameTextBox), category,
				ErrorMessageContants.CATEGORY_DISPLAYED, ErrorMessageContants.CATEGORY_NOT_DISPLAYED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyErrorMessageForEmptyCategoryName() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		category = UtililtyFunctions.generateRandomString(6);
		AddCategoryPage addCategoryPageObj = PageFactory.initElements(driver, AddCategoryPage.class);
		addCategoryPageObj.clickListCategoriesLink();
		addCategoryPageObj.createCategory(category);
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);
		listCategoryPageObj.clickEditCategory();
		EditCategoryPage editCategoryPageObj = PageFactory.initElements(driver, EditCategoryPage.class);
		editCategoryPageObj.clearCategoryTextBox();
		editCategoryPageObj.clickSaveCategoryBtn();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getValidationMessage(editCategoryPageObj.categoryNameTextBox),
				ValidationMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_PRESENT,
				ErrorMessageContants.ERROR_PLEASE_FILL_IN_THIS_FIELD_NOT_PRESENT);
		loginPageobj.clickLogout();
	}
}
