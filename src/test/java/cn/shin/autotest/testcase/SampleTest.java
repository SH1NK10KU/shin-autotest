package cn.shin.autotest.testcase;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.fest.assertions.Assertions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import cn.shin.autotest.dao.IActorDao;
import cn.shin.autotest.dao.impl.ActorDaoImpl;
import cn.shin.autotest.entity.Actor;
import cn.shin.autotest.json.Json;
import cn.shin.autotest.page.BaiduPage;
import cn.shin.autotest.selenium.WebDriverDecorator;
import cn.shin.autotest.test.BaseTest;
import cn.shin.autotest.util.IDBUnitUtil;
import cn.shin.autotest.util.IElementVerifyUtil;
import cn.shin.autotest.util.IHttpClientUtil;
import cn.shin.autotest.util.IRobotUtil;
import cn.shin.autotest.util.impl.DBUnitUtilImpl;
import cn.shin.autotest.util.impl.ElementVerifyUtilImpl;
import cn.shin.autotest.util.impl.HttpClientUtilImpl;
import cn.shin.autotest.util.impl.RobotUtilImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Provide the sample for using this framework.
 * 
 * @author Shin Feng
 * @date 2014-10-27
 *
 */
public class SampleTest extends BaseTest {

	@BeforeTest(groups = "WebUITest")
	public void BeforeTest() {

	}

	@Test(description = "Search the keyword via Baidu.", groups = { "Sample",
			"WebUITest" }, dataProvider = "TestDataProvider")
	public void BaiduTest(Map<String, String> data) {
		try {
			driver = new WebDriverDecorator(data.get("Browser"));
			driver.get(data.get("URL"));
			BaiduPage baiduPage = new BaiduPage();
			baiduPage.search(data.get("SearchContent"));
			driver.wait(2);
			Assertions.assertThat(driver.getTitle()).isEqualTo(
					data.get("ExpectedTitle"));
		} finally {
			driver.quit();
		}
	}

	@Test(description = "Open the page, 'www.qq.com'.", groups = { "Sample",
			"WebUITest" }, dataProvider = "TestDataProvider")
	public void QQTest(Map<String, String> data) {
		try {
			driver = new WebDriverDecorator(data.get("Browser"));
			driver.get(data.get("URL"));
			driver.wait(2);
			Assertions.assertThat(driver.getTitle()).isEqualTo(
					data.get("ExpectedTitle"));
		} finally {
			driver.quit();
		}
	}

	@Test(description = "JSON Sample Test", groups = { "Sample", "JsonTest" })
	public void JsonToObjectTest() {
		String json = "{\"code\":1,\"data\":[{\"firstname\":\"Shin\",\"lastname\":\"Feng\"},{\"firstname\":\"Kan\",\"lastname\":\"Feng\"}]}";
		Type type = new TypeToken<Json>() {
		}.getType();
		Gson gson = new Gson();
		Json object = gson.fromJson(json, type);

		Assertions.assertThat(object.getCode()).isEqualTo(1);
		Assertions.assertThat(object.getData().size()).isEqualTo(2);
		Assertions.assertThat(object.getData().get(0).getFirstname())
				.isEqualTo("Shin");
	}

	@Test(description = "HTTP Interface Sample Test", groups = { "Sample",
			"InterfaceTest" }, dataProvider = "TestDataProvider")
	public void HttpInterfaceTest(Map<String, String> data) {
		IHttpClientUtil httpClientUtil = new HttpClientUtilImpl();
		String html = httpClientUtil.getHttpGetResponse(
				"http://www.baidu.com/s", data);
		Assertions.assertThat(html).contains(data.get("ExpectedResult"));
	}

	@Test(description = "DBUnit Sample Test", groups = { "Sample",
			"DatabaseTest" })
	public void DBUnitTest() {
		IDBUnitUtil dbUnitUtil = new DBUnitUtilImpl();
		dbUnitUtil.assertTableEqualsXml("Actor",
				"SHIN_DBUNIT_TEST_ACTOR_PARTIAL", "actor_id");
	}

	@Test(description = "Hibernate Sample Test", groups = { "Sample",
			"DatabaseTest" })
	public void HibernateTest() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath*:/applicationContext.xml");
		IActorDao dao = applicationContext.getBean(ActorDaoImpl.class);
		// Actor actual = dao.findById((short) 1);
		// AssertJUnit.assertEquals("PENELOPE", actual.getFirstName());
		//
		Actor actor = new Actor();
		actor.setFirstName("Shin");
		actor.setLastName("Feng");
		actor.setLastUpdate(new Date());
		dao.save(actor);

		List<Actor> actorList = dao.getAll();
		short maxId = actorList.get(actorList.size() - 1).getActorId();
		actor.setActorId(maxId);
		Date date = actorList.get(actorList.size() - 1).getLastUpdate();
		actor.setLastUpdate(date);
		actor.setActorId(maxId);
		dao.delete(actor);
	}

	@Test(description = "Upload the image to search in baidu.", groups = {
			"Sample", "WebUITest" })
	public void UploadFileTest() {
		try {
			driver = new WebDriverDecorator("chrome");
			driver.get("http://image.baidu.com/");
			driver.findElementAndClickByXpath("//a[@id='sttb']");
			driver.findElementAndClickByXpath("//a[@id='uploadImg']");

			IRobotUtil robotUtil = new RobotUtilImpl();
			robotUtil.uploadFile("菊花.jpg",
					"C:\\Users\\Public\\Pictures\\Sample Pictures\\");

			IElementVerifyUtil elementVerifyUtil = new ElementVerifyUtilImpl();
			elementVerifyUtil
					.verifyElementIsDisplayedByXpath("//span[@class='s_ipt_a']");
		} finally {
			driver.quit();
		}
	}
}