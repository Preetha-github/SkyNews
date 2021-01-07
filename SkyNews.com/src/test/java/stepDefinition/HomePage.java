package stepDefinition;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hooks.Hooks;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class HomePage {

	WebDriver driver;
	Hooks hook;
	String ExpectedHeadlineWord;
	public Logger logger = Logger.getLogger(HomePage.class);

	public HomePage(Hooks hook) {
		driver = hook.StartBrowser();
	}

	@Given("^User navigates to SkyNews homepage$")
	public void user_navigates_to_SkyNews_homepage() {
		logger.info("--Launching SkyNews Homepage--");
		driver.navigate().to("https://news.sky.com/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

	@When("^User clicks 'Accept' cookies$")
	public void user_clicks_Accept_cookies() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement cookieFrame = driver.findElement(By.id("sp_message_iframe_368417"));
		wait.until(ExpectedConditions.elementToBeClickable(cookieFrame));

		driver.switchTo().frame(cookieFrame);
		WebElement agreeButton = driver.findElement(By.cssSelector("button.sp_message-accept-button"));
		agreeButton.click();
		logger.info("--Clicking accept cookie button--");
		driver.switchTo().defaultContent();

	}

	@Then("^Page title should be displayed$")
	public void page_title_should_be_displayed() {

		logger.info("--Checking Page title--");
		String pageTitle = driver.getTitle();
		System.out.println("HomePage title is: " + pageTitle);
		Assert.assertEquals("The Latest News from the UK and Around the World | Sky News", pageTitle);

	}

	@Then("^Number of categories should be displayed along with their names\\.$")
	public void number_of_categories_should_be_displayed_along_with_their_names() {

		logger.info("--Checking the number of categories on Homepage--");
		List<WebElement> listOfCategories = driver
				.findElements(By.cssSelector("nav ul.sdc-site-header__menu-cell--1 a.sdc-site-header__menu-item-link"));
		int categoriesSize = listOfCategories.size();
		System.out.println("Number of Categories displayed: " + categoriesSize);
		Assert.assertEquals(15, categoriesSize);
		System.out.println("List of all Category names: ");
		for (WebElement webElement : listOfCategories) {
			System.out.println(webElement.getAttribute("textContent"));
		}

		System.out.println("List of Category names that are visible ");
		for (WebElement webElement : listOfCategories) {
			System.out.println(webElement.getText());
		}
	}

	@Then("^Home category should be selected by default$")
	public void home_category_should_be_selected_by_default() {
		logger.info("--Checking Home category is selected by default--");
		WebElement categoryHome = driver
				.findElement(By.cssSelector(".sdc-site-header__menu-item-link[aria-current=true]"));
		String homeFocusFlag = categoryHome.getAttribute("aria-current");
		Assert.assertTrue("Focus is NOT in Home category", Boolean.parseBoolean(homeFocusFlag));

	}

	@When("^User clicks 'Climate' category$")
	public void user_clicks_Climate_category() {
		logger.info("--Clicking climate category--");
		WebElement climateLink = driver.findElement(
				By.cssSelector("[data-role=\"main-nav-item\"]>.sdc-site-header__menu-item-link[href*='/climate']"));
		climateLink.click();

	}

	@Then("^Climate should be focussed$")
	public void climate_should_be_focussed() {
		logger.info("--Checking climate category focus--");
		WebElement categoryClimate = driver.findElement(
				By.cssSelector("[data-role=\"main-nav-item\"]>.sdc-site-header__menu-item-link[href*='/climate']"));
		String climateFocusFlag = categoryClimate.getAttribute("aria-current");
		Assert.assertTrue("Focus is NOT in Climate category", Boolean.parseBoolean(climateFocusFlag));

	}

	@When("^User clicks a story from homepage$")
	public void user_clicks_a_story_from_homepage() {
		logger.info("--Clicking a story from homepage--");
		// Choosing the 1st word from the 1st article
		List<WebElement> Headlines = driver.findElements(By.cssSelector("h3.sdc-site-tile__headline"));
		WebElement firstHeadline = Headlines.get(0);
		String StoryTitle = firstHeadline.getText();
		System.out.println("Homepage article: " + StoryTitle);
		String Words[] = StoryTitle.split(" ");
		System.out.println("Choosing the 1st word from the 1st article: " + "'" + Words[0] + "'");
		ExpectedHeadlineWord = Words[0];
		firstHeadline.click();

	}

	@Then("^Title text of the article selected should appear in the new page$")
	public void title_text_of_the_article_selected_should_appear_in_the_new_page() {
		logger.info("--Checking the title text of the story--");
		WebElement article = driver.findElement(By.cssSelector(".sdc-article-header__long-title"));
		System.out.println("'" + ExpectedHeadlineWord + "'" + " is present in title text of article selected - "
				+ article.getText());
		Assert.assertTrue(article.getText().contains(ExpectedHeadlineWord));

	}

}
