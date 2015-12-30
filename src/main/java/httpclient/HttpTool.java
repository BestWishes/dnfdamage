package httpclient;

import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

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
	static HttpCookie cookie;
	static StringBuffer result=new StringBuffer();
	public static void main(String[] args) throws Exception {
		register("521526172785");
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
//		responseHeader=response.getAllHeaders();
//		for (Header header : responseHeader) {
//			System.out.println(header.toString());
//		}
		httpEntity=response.getEntity();
		String itemNameString=EntityUtils.toString(httpEntity);
		Document document=Jsoup.parse(itemNameString);
//		System.out.println(document);
		String promotionPrice=itemNameString.substring(itemNameString.indexOf("var l,url="),1000).split("[\']")[1].toString();
//		System.err.println(promotionPrice);
		Elements elements=document.getElementById("J_DetailMeta").getElementsByTag("h1");
		result.append("商品名称：");
		result.append(elements.get(0).text());
		getPromotionPrice("1");
		//获取商品评价
		get.setURI(new URI("https://dsr-rate.tmall.com/list_dsr_info.htm?itemId="+itemId+"&spuId="+response.getFirstHeader("at_prid").getValue().toString()+"&callback=jsonp203"));
		
		response=client.execute(get);
		httpEntity=response.getEntity();
		result.append(" \n商品评价:");
		result.append(EntityUtils.toString(httpEntity).split("[\"]")[4].substring(1, 4));

		//获取价格
		get=new HttpGet("https://detail.tmall.com/item.htm?id="+itemId);
		response=client.execute(get);
		httpEntity=response.getEntity();
		String priceString=EntityUtils.toString(httpEntity);
//		System.out.println(priceString);
		int index=priceString.indexOf("defaultItemPrice");
		String pirce=priceString.substring(index,index+40).split("[\"]")[2].toString();
		result.append(" \n价格:");
		result.append(pirce);
		
		//获取促销价
		get=new HttpGet("https://mdskip.taobao.com/core/initItemDetail.htm?cachedTimestamp=1451445518987&itemId=41574354318&tryBeforeBuy=false&isApparel=true&isAreaSell=false&cartEnable=true&isSecKill=false&sellerPreview=false&isUseInventoryCenter=false&queryMemberRight=true&addressLevel=2&household=false&showShopProm=false&progressiveSupport=false&isForbidBuyItem=false&service3C=false&tmallBuySupport=true&isRegionLevel=false&offlineShop=false&callback=setMdskip&timestamp=1451454926677&isg=AtfX/C7WIhHVggBpKgk11YxG50Ui/Kt8&areaId=510100&cat_id=2&ref=https%3A%2F%2Flist.tmall.com%2Fsearch_product.htm%3Fspm%3Da21bo.7724922.8452-tmallHotWords.1.UKpxZ4%26sourceId%3Dtb.index%26commend%3Dall%26q%3D%25E7%25BE%25BD%25E7%25BB%2592%25E6%259C%258D%26from%3Dtbmain_1.0_hq");
		response=client.execute(get);
		httpEntity=response.getEntity();
		priceString=EntityUtils.toString(httpEntity);
//		System.out.println(priceString);
		index=priceString.indexOf("defaultItemPrice");
//		pirce=priceString.substring(index,index+40).split("[\"]")[2].toString();
		result.append(" \n促销价:");
		result.append(pirce);
//		System.out.println(result.toString());
		return null;
	}
	

	private static String getPromotionPrice(String str){
//		CookieStore cookieStore=client.getCookieStore();
//		CookieStore store=new BasicCookieStore();
		String[] strings=new String[5];
		ArrayList<String> strings2=new ArrayList<String>();
		strings[0]="callback=setMdskip";
		strings[1]="timestamp="+(+new Date().getTime());
//		strings[2]="isg="+(isg&&isg[2])]
		return null;
	}
}
