package httpclient;

import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Administrator
 *
 */
public class HttpTool {

	static HttpClient client=HttpClients.createDefault();
	static HttpGet get=new HttpGet("http://www.oschina.net/");
	static StringBuffer uriString=new StringBuffer("https://detail.tmall.com/item.htm?id=");
	static HttpPost post=new HttpPost();
	static Header[] requestHeader;
	static Header[] responseHeader;
	static HttpResponse response;
	static HttpRequest request;
	static HttpEntity httpEntity;
	static StringBuffer result=new StringBuffer();
	public static void main(String[] args) throws Exception {
		register("520595856271");
	}
	
	/**
	 * http模拟表单提交
	 * @return
	 * @throws Exception
	 */
	private static String register(String itemId) throws Exception{
		
		uriString.append(itemId);
		get.setURI(new URI(uriString.toString()));
		response=client.execute(get);
		responseHeader=response.getAllHeaders();
		for (Header header : responseHeader) {
			System.err.println(header.toString());
		}
		httpEntity=response.getEntity();
		Document document=Jsoup.parse(EntityUtils.toString(httpEntity));
		Elements elements=document.getElementById("J_DetailMeta").getElementsByTag("h1");
		result.append("商品名称：");
		result.append(elements.get(0).text());
		get.setURI(new URI("https://dsr-rate.tmall.com/list_dsr_info.htm?itemId="+itemId+"&spuId="+response.getFirstHeader("at_prid").getValue().toString()+"&callback=jsonp203"));
		
		response=client.execute(get);
		httpEntity=response.getEntity();
		result.append(" \n商品评价:");
		result.append(EntityUtils.toString(httpEntity).split("[\"]")[4].substring(1, 4));
		responseHeader =response.getAllHeaders();
	
		elements=document.getElementById("J_StrPriceModBox").getElementsByTag("dd");
		result.append(" \n价格:");
		result.append(elements.get(0).text());
		elements=document.getElementById("J_PromoPrice").getElementsByTag("dd");
		result.append(" \n促销价:");
		result.append(elements.get(0).text());
		System.out.println(result.toString());
		return null;
	}
	

}
