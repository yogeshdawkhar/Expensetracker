package com.expensetracker.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.expensetracker.base.BaseClass;
import com.expensetracker.constants.ErrorMessageContants;
import com.expensetracker.constants.ValidationMessageContants;
import com.expensetracker.pages.AddCategoryPage;
import com.expensetracker.pages.ListCategoriesPage;
import com.expensetracker.pages.LoginPage;
import com.expensetracker.pages.RegisterUserPage;
import com.expensetracker.utility.AssertionUtil;
import com.expensetracker.utility.ExtentReportLog;
import com.expensetracker.utility.UtililtyFunctions;

public class ListCategoryPageTestCase extends BaseClass {
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
	public void verifyHeaderLabelsAndLinksOnListCategories() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		userName = UtililtyFunctions.generateRandomString(8);
		password = UtililtyFunctions.generateRandomString(8);
		createUser(userName, password);
		loginPageobj.clickListCategories();
		ListCategoriesPage listCategoryPageObj = PageFactory.initElements(driver, ListCategoriesPage.class);

		AssertionUtil.assertEquals(listCategoryPageObj.getListCategoriesHeading(),
				ValidationMessageContants.HEADER_LIST_CATEGORIES, ErrorMessageContants.HEADER_LIST_CATEGORY_PRESENT,
				ErrorMessageContants.HEADER_LIST_CATEGORY_NOT_PRESENT);
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.addCategoryLink.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_DISPLAYED,
				ErrorMessageContants.ELEMENT_NOT_DISPLAYED);
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.tableHeaderName.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_DISPLAYED,
				ErrorMessageContants.ELEMENT_NOT_DISPLAYED);
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.tableHeaderModify.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_DISPLAYED,
				ErrorMessageContants.ELEMENT_NOT_DISPLAYED);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyAddedCategoryIsDiplayedonListCategoryPage() throws IOException {
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
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_DISPLAYED,
				ErrorMessageContants.ELEMENT_NOT_DISPLAYED);
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.deleteCategory.isDisplayed()),
				ValidationMessageContants.BTRUE, ErrorMessageContants.ELEMENT_DISPLAYED,
				ErrorMessageContants.ELEMENT_NOT_DISPLAYED);
		AssertionUtil.assertEquals(String.valueOf(listCategoryPageObj.findCategory(category)),
				ValidationMessageContants.BTRUE, ErrorMessageContants.CATEGORY_PRESENT,
				ErrorMessageContants.CATEGORY_NOT_PRESENT);
		loginPageobj.clickLogout();
	}

	@Test
	public void verifyUserShouldNotableToUseListCategoriesFeatureWithoutLogin() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		UtililtyFunctions utililtyFunctionsobj = PageFactory.initElements(driver, UtililtyFunctions.class);
		utililtyFunctionsobj.openURL(loginUrl);
		LoginPage loginPageobj = PageFactory.initElements(driver, LoginPage.class);
		if (UtililtyFunctions.isElementExists(loginPageobj.logoutLink)) {
			loginPageobj.clickLogout();
		}
		loginPageobj.clickListCategories();
		AssertionUtil.assertEquals(utililtyFunctionsobj.getCurrentUrl(), loginUrl,
				ErrorMessageContants.USER_CANNOT_USE_CLICKED_FUNCTIONALITY,
				ErrorMessageContants.USER_CAN_USE_CLICKED_FUNCTIONALITY);
	}
}
