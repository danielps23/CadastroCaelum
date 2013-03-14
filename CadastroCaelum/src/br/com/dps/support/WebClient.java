package br.com.dps.support;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {
	
	private final String url;
	
	public WebClient(String url) {
		this.url = url;
	}
	
	public String post(String json) {
		String jsonResposta = "";
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new StringEntity(json));
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			
			HttpResponse response = defaultHttpClient.execute(post);
			jsonResposta = EntityUtils.toString(response.getEntity()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonResposta;
	}

}
