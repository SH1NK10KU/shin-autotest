package cn.shin.autotest.util;

import java.util.Map;

/**
 * Interface for IHttpUtilImpl.
 * 
 * @author Shin Feng
 * @date 2014-11-07
 *
 */
public interface IHttpClientUtil {
	/**
	 * Get response string via HTTP GET.
	 * 
	 * @param url
	 * @param data
	 *            Map for parameters
	 * @return
	 */
	public String getHttpGetResponse(String url, Map<String, String> data);

	/**
	 * Get response string via HTTP POST.
	 * 
	 * @param url
	 * @param data
	 *            Map for parameters
	 * @return
	 */
	public String getHttpPostResponse(String url, Map<String, String> data);
}
