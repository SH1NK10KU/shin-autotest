package cn.shin.autotest.testcase;

import java.util.LinkedList;
import java.util.List;

import org.fest.assertions.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import cn.shin.autotest.json.DataJson;

public class MokitoTest {
	private DataJson dataJsonMock = Mockito.mock(DataJson.class);

	@Test
	public void MockitoTest() {
		Assertions.assertThat(dataJsonMock).isInstanceOf(DataJson.class);

		Mockito.when(dataJsonMock.getFirstname()).thenReturn("Shin");
		Assertions.assertThat(dataJsonMock.getFirstname()).isEqualTo("Shin");
		dataJsonMock.setLastname("Feng");
		Mockito.verify(dataJsonMock).setLastname("Feng");
	}

	@Test
	public void SpyTest() {
		// Lets mock a LinkedList
		List list = new LinkedList();
		List spy = Mockito.spy(list);

		// You have to use doReturn() for stubbing
		Mockito.doReturn("foo").when(spy).get(0);

		// this would not work
		// real method is called so spy.get(0)
		// throws IndexOutOfBoundsException (list is still empty)
		Mockito.when(spy.get(0)).thenReturn("foo");

		Assertions.assertThat(spy.get(0)).isEqualTo("foo");
	}

}
