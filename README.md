
# Automation Assignment ExpenseTracker (Selenium-WebDriver + Java)

## Project Structure

```
pom.xml
testng.xml
targets
src
└── ExtentReports (It will contain folder with reports for every execution cycle)

src
└──
    └── test
           └──java
                 └── com
                       └── expensetracker
                  	       └── base
                                ├── BaseClass.java : Base class with driver config
    
                  	       └── constants
                                ├── ErrorMessageContants.java : Class to keep defined Error messages Constants 
                                ├── ValidationMessageContants.java : Class to keep Defined validation messages present in the appication
    
                  	       └── pages
                                ├── AddCategoryPage.java : Class for Add Category page  
                                ├── AddExpensePage.java : Class for Add Expense page 
                                ├── CopyExpensePage.java : Class for Copy Expense page 
                                ├── EditAccountPage.java : Class for Edit Account page
                                ├── EditCategoryPage.java : Class for Edit Category page
                                ├── EditExpensePage.java : Class for Edit Expense page
                                ├── ListCategoriesPage.java : Class for List Categories page
                                ├── ListExpensePage.java : Class for List Expense page 
                                ├── LoginPage.java : Class for Login page
                                ├── RegisterUserPage.java : Class for Register user page
                                ├── SaveExpensePage.java : Class for Save Expense page
                                ├── ShowStatisticsPage.java : Class for Show Statistics page                                    
                                        
                  	       └── testcases
                                ├── AddCategoryPageTestCase.java : Class for Add Category TestCases 
                                ├── AddExpensePageTestCase.java : Class for Add Expense TestCases
                                ├── CopyExpensePageTestCase.java : Class for Copy Expense TestCases
                                ├── DeleteCategoryPageTestCase.java : Class for Delete Category TestCases
                                ├── DeleteExpensePageTestCase.java : Class for Delete Expense TestCases
                                ├── EditAccountPageTestCase.java : Class for Edit Account TestCases
                                ├── EditCategoryPageTestCase.java : Class for Edit Category TestCases
                                ├── EditExpensePageTestCase.java : Class for Edit Expense TestCase 
                                ├── ListCategoryPageTestCase.java : Class for List Category TestCase
                                ├── ListExpensePageTestCase.java : Class for List Expense TestCase
                                ├── LoginPageTestCase.java : Class for Login TestCase
                                ├── RegisterUserPageTestCase.java : Class for Register User TestCase                                    
                                ├── ShowStatisticsPageTestCase.java : Class for Show Statistics TestCase                                    
                                        
                  	       └── utility
                                ├── AssertionUtil.java : Class for Assertion code. 
                                ├── ExtentReportLog.java : Clas for Extent Report Loging
                                ├── ScreenshotUtil.java : Class for Screenshot functionality
                                ├── UtcDate.java : Class for getting date,month,year
                                ├── UtililtyFunctions.java :  Utlity functions with super driver
                                       
```

## Libraries:

- testng 6.14.3
- selenium-java Client 3.141.59
- webdrivermanager 3.4.0
- extentreports 4.0.9


## Requirements

- Chrome browser to be installed
- Maven 3.0 installed

## Framework
 - Maven build tool, maintaining all the library definition in pom 
 - Page Object Model design pattern for maintaining the separate class for each feature
 - Page Factory Model for defining and locating web elements
 - Extentreport for effective reporting


## Java
 -java version "1.8.0_112"
 -Java(TM) SE Runtime Environment (build 1.8.0_112-b16)
 -Java HotSpot(TM) 64-Bit Server VM (build 25.112-b16, mixed mode)
 
## Start the tests

Open terminal, browse to root directory of project, run the test suite:

	mvn clean install

## Test Cases

Please find test cases excel sheet at root folder

/TestCasesDepositSolutions.xlsx


## Test Report Location

Please find test report under below path 

/src/ExtentReports/{currentTestExecutionfolder}/

## Comments in code 

Entire code styling is influenced by Clean Code principle - Robert Martin
Which says
'Truth can only be found in one place: the code’.
So you may not find any comments anywhere in the project.
Keeping in mind that git can be used to versioning of file and method, class names should be kept as self explanatory.

However, if you need comments on each file. I can do that too.

## Design principles used in Project :

- POM (Page Object Model)
- DRY(Don’t repeat yourself)
- KISS(Keep it simple, stupid)


