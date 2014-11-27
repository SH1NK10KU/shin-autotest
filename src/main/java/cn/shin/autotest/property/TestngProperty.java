package cn.shin.autotest.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Shin Feng
 * @date 2014-11-12
 * 
 */
@Component
public class TestngProperty {
	@Value("${retry.times}")
	private int retryTimes;
	@Value("${excel.path}")
	private String xlsPath;
	
	public int getRetryTimes() {
		return retryTimes;
	}
	public String getXlsPath() {
		return xlsPath;
	}
}
