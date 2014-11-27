package cn.shin.autotest.util.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import cn.shin.autotest.util.IHttpClientUtil;

/**
 * Provide the basic methods for HTTP post/get.
 * 
 * @author Shin Feng
 * @date 2014-11-07
 *
 */
public class HttpClientUtilImpl implements IHttpClientUtil {
	private static final String CONTENT_CHARSET = "UTF-8";

	/**
	 * Get response string via HTTP GET.
	 * 
	 * @param url
	 * @param params
	 *            HashMap for parameters
	 * @return
	 */
	public String getHttpGetResponse(String url, Map<String, String> params) {
		String result = "";
		try {
			Iterator<?> iterator = params.entrySet().iterator();
			url += "?";
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				url += (String) entry.getKey() + "="
						+ (String) entry.getValue() + "&";
			}

			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), CONTENT_CHARSET));

			String line = "";
			while ((line = rd.readLine()) != null) {
				result += line;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Get response string via HTTP POST.
	 * 
	 * @param url
	 * @param params
	 *            HashMap for parameters
	 * @return
	 */
	public String getHttpPostResponse(String url, Map<String, String> params) {
		String result = "";
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			Iterator<?> iterator = params.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				urlParameters.add(new BasicNameValuePair((String) entry
						.getKey(), (String) entry.getValue()));
			}

			httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

			HttpResponse response = httpClient.execute(httpPost);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), CONTENT_CHARSET));

			String line = "";
			while ((line = rd.readLine()) != null) {
				result += line;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}