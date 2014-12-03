package cn.shin.autotest.steps;

import org.fest.assertions.Assertions;

import cn.shin.autotest.page.BaiduPage;
import cn.shin.autotest.selenium.WebDriverDecorator;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CucumberSteps {
	WebDriverDecorator driver;
	BaiduPage baiduPage = new BaiduPage();

	@Given("^Open the browser, (.*)$")
	public void open(String browser) throws Throwable {
		driver = new WebDriverDecorator(browser);
	}

	@Given("^打开(.*)浏览器$")
	public void 打开浏览器(String browser) throws Throwable {
		driver = new WebDriverDecorator(browser);
	}

	@Given("^Go to the page, (.*)$")
	public void go_to_the_page(String url) throws Throwable {
		driver.get(url);
	}

	@Given("^打开网页(.*)$")
	public void 打开网页(String url) throws Throwable {
		driver.get(url);
	}

	@Given("^Search the content, (.*)$")
	public void search_the_content(String keyword) throws Throwable {
		driver.findElementAndSendKeysByXpath(baiduPage.TEXTFIELD_SEARCH,
				keyword);
	}

	@Given("^搜索(.*)$")
	public void 搜索(String keyword) throws Throwable {
		driver.findElementAndSendKeysByXpath(baiduPage.TEXTFIELD_SEARCH,
				keyword);
	}

	@When("^Click the search button$")
	public void click_the_search_button() throws Throwable {
		driver.findElementAndClickByXpath(baiduPage.BUTTON_SEARCH);
	}

	@When("^点击搜索按钮$")
	public void 点击搜索按钮() throws Throwable {
		driver.findElementAndClickByXpath(baiduPage.BUTTON_SEARCH);
	}

	@Then("^Page title is (.*)$")
	public void page_title_is(String title) throws Throwable {
		Assertions.assertThat(driver.getTitle()).isEqualTo(title);
	}

	@Then("^页面标题(.*)$")
	public void 页面标题(String title) throws Throwable {
		Assertions.assertThat(driver.getTitle()).isEqualTo(title);
	}

	@Then("^Close the browser$")
	public void close_the_browser() throws Throwable {
		driver.quit();
	}
	
	@Then("^关闭浏览器$")
	public void 关闭浏览器() throws Throwable {
		driver.quit();
	}

	@After
	public void afterTest() {
		if (driver != null) {
			driver.quit();
		}
	}
}
