package cn.shin.autotest.steps;

import org.fest.assertions.Assertions;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;

import cn.shin.autotest.json.DataJson;

public class DataJsonSteps {
	private DataJson data;

	@Given("有一个DataJson")
	public void 有一个DataJson() {
		data = new DataJson();
	}

	@When("创建DataJson：$datajson")
	public void 创建DataJson(@Named("datajson") ExamplesTable datajson) {
		Parameters parameters = datajson.getRowAsParameters(0);
		data.setFirstname(parameters.valueAs("FirstName", String.class));
		data.setLastname(parameters.valueAs("LastName", String.class));
	}

	@Then("姓应该是：$expectedLastName")
	public void 姓应该是(@Named("expectedLastName") String expectedLastName) {
		Assertions.assertThat(data.getLastname()).isEqualTo(expectedLastName);
	}
}
