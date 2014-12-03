package cn.shin.autotest.steps;

import org.fest.assertions.Assertions;
import org.jbehave.core.annotations.Aliases;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import cn.shin.autotest.page.BaiduPage;
import cn.shin.autotest.selenium.WebDriverDecorator;

public class BaiduSteps {
	private WebDriverDecorator driver;
	private BaiduPage baiduPage = new BaiduPage();
	

	@Given("initial the browser, '$browser'")
	@Aliases(values = { "initial the browser, 'chrome'" })
	public void initBrowser(@Named("browser") String browser) {
		driver = new WebDriverDecorator(browser);
	}

	@Given("I open the url, '$url'")
	public void openUrl(@Named("url") String url) {
		driver.get(url);
	}

	@Given("the keyword, '$keyword' is input")
	public void searchKeyword(@Named("keyword") String keyword) {
		driver.findElementAndSendKeysByXpath(baiduPage.TEXTFIELD_SEARCH,
				keyword);
	}

	@When("I click the search button")
	public void clickSearch() {
		driver.findElementAndClickByXpath(baiduPage.BUTTON_SEARCH);
	}

	@Then("the page title should be '$title'")
	public void pageTitleShouldBe(@Named("title") String title) {
		Assertions.assertThat(driver.getTitle()).isEqualTo(title);
	}

	@Then("close the browser")
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
