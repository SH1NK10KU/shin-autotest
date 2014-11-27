package cn.shin.autotest.testcase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cn.shin.autotest.dao.IActorDao;
import cn.shin.autotest.dao.impl.ActorDaoImpl;
import cn.shin.autotest.entity.Actor;
import cn.shin.autotest.json.Json;
import cn.shin.autotest.page.BaiduPage;
import cn.shin.autotest.selenium.WebDriverDecorator;
import cn.shin.autotest.testng.ExcelDataProvider;
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
public class SampleTest {
	private WebDriverDecorator webDriverDecorator;

	@BeforeTest(groups = "WebUITest")
	public void BeforeTest() {

	}

	@Test(description = "Search the keyword via Baidu.", groups = { "Sample",
			"WebUITest" }, dataProvider = "SampleDataProvider")
	public void BaiduTest(Map<String, String> data) {
		try {
			webDriverDecorator = new WebDriverDecorator(data.get("Browser"));
			webDriverDecorator.get(data.get("URL"));
			BaiduPage baiduPage = new BaiduPage();
			baiduPage.search(data.get("SearchContent"));
			webDriverDecorator.wait(2);
			AssertJUnit.assertEquals(data.get("ExpectedTitle"),
					webDriverDecorator.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			webDriverDecorator.quit();
		}
	}

	@Test(description = "Open the page, 'www.qq.com'.", groups = { "Sample",
			"WebUITest" }, dataProvider = "SampleDataProvider")
	public void QQTest(Map<String, String> data) {
		try {
			webDriverDecorator = new WebDriverDecorator(data.get("Browser"));
			webDriverDecorator.get(data.get("URL"));
			webDriverDecorator.wait(2);
			AssertJUnit.assertEquals(data.get("ExpectedTitle"),
					webDriverDecorator.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			webDriverDecorator.quit();
		}
	}

	@Test(description = "JSON Sample Test", groups = { "Sample", "JsonTest" })
	public void JsonToObjectTest() {
		String json = "{\"code\":1,\"data\":[{\"firstname\":\"Shin\",\"lastname\":\"Feng\"},{\"firstname\":\"Kan\",\"lastname\":\"Feng\"}]}";
		Type type = new TypeToken<Json>() {
		}.getType();
		Gson gson = new Gson();
		Json object = gson.fromJson(json, type);

		AssertJUnit.assertEquals(1, object.getCode());
		AssertJUnit.assertEquals(2, object.getData().size());
		AssertJUnit
				.assertEquals("Shin", object.getData().get(0).getFirstname());
	}

	@Test(description = "HTTP Interface Sample Test", groups = { "Sample",
			"InterfaceTest" }, dataProvider = "SampleDataProvider")
	public void HttpInterfaceTest(Map<String, String> data) {
		IHttpClientUtil httpClientUtil = new HttpClientUtilImpl();
		String html = httpClientUtil.getHttpGetResponse(
				"http://www.baidu.com/s", data);
		AssertJUnit.assertTrue(html.contains(data.get("ExpectedResult")));
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
		// dao.save(actor);

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
			webDriverDecorator = new WebDriverDecorator("chrome");
			webDriverDecorator.get("http://image.baidu.com/");
			webDriverDecorator.findElementAndClickByXpath("//a[@id='sttb']");
			webDriverDecorator
					.findElementAndClickByXpath("//a[@id='uploadImg']");

			IRobotUtil robotUtil = new RobotUtilImpl();
			robotUtil.uploadFile("菊花.jpg",
					"C:\\Users\\Public\\Pictures\\Sample Pictures\\");

			IElementVerifyUtil elementVerifyUtil = new ElementVerifyUtilImpl();
			elementVerifyUtil
					.verifyElementIsDisplayedByXpath("//span[@class='s_ipt_a']");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			webDriverDecorator.quit();
		}
	}

	@AfterTest(groups = "WebUITest")
	public void AfterTest() {
		if (webDriverDecorator != null) {
			webDriverDecorator.quit();
		}
	}

	@DataProvider(name = "SampleDataProvider")
	public Iterator<Object[]> prepareTestData(Method method) throws IOException {
		return new ExcelDataProvider(this.getClass().getName(),
				method.getName());
	}
}