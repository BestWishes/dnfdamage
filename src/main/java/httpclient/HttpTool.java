package httpclient;

import java.io.Serializable;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator.Matches;

import sun.misc.Regexp;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
/**
 * @author Administrator
 *
 */
public class HttpTool {
	static CookieStore cookieStore=new BasicCookieStore();

	static CloseableHttpClient client=HttpClients.custom().setDefaultCookieStore(cookieStore).build();
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
	
	
	//促销价相关
	static String promotionBasicString;
	static String promotionScriptString;
	static String isg;
	public static void main(String[] args) throws Exception {
		check();
//		register("44797938669");
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
//		System.err.println(document);
		String promotionPrice=itemNameString.substring(itemNameString.indexOf("var l,url="),1000).split("[\']")[1].toString();
		promotionBasicString=itemNameString.substring(itemNameString.indexOf("isg=do"),1000).split("[\n]")[0].toString();
		System.err.println(promotionBasicString);
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
//		System.err.println(priceString);
		index=priceString.indexOf("defaultItemPrice");
//		pirce=priceString.substring(index,index+40).split("[\"]")[2].toString();
		result.append(" \n促销价:");
		result.append(pirce);
		System.out.println(result.toString());
		return null;
	}
	

	private static String getPromotionPrice(String str){
		
		
		List<Cookie> cookies=cookieStore.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("cookie2")){
				boolean bool=cookie.getValue().matches("(^|;) ?l=([^;]*)(;|$)");
				System.out.println(bool);
			}
		}
		String[] strings=new String[5];
		ArrayList<String> strings2=new ArrayList<String>();
		strings[0]="callback=setMdskip";
		strings[1]="timestamp="+(+new Date().getTime());
//		strings[2]="isg="+(isg&&isg[2])]
		return null;
	}
	private static void check(){
		
		List<Long>	list=new ArrayList<Long>();
		Random random=new Random(new Date().getTime());
		for(int i=0;i<10;i++){
			list.add(Long.valueOf(random.nextInt(10)));
		}
		System.out.println("原始数组:"+list.toString());
		Collections.sort(list);
		Collections.reverse(list);
		System.err.println("排序后数组:"+list.toString());
		System.out.println(Collections.max(list));
		
	}
	
	private static List<Long> getlist(List<Long> list){
		List<Long> result=new ArrayList<Long>();
		Long t;
		for(int i=0;i<list.size()-1;i++){
			for(int j=i+1;j<list.size();j++){
				if(list.get(i)>list.get(j)){
					t=list.get(i);
					list.set(i, list.get(j));
					list.set(j, t);
				}
			}
		}
//		int minIndex=0;
//		for(int i=0;i<list.size()-1;i++){
//			minIndex=i;
//				for(int j=i+1;j<list.size();j++){
//					if(list.get(j)<list.get(minIndex)){
//						minIndex=j;
//					}
//				}
//			t=list.get(i);
//			list.set(i, list.get(minIndex));
//			list.set(minIndex, t);
//		}
		
		getSite(list,Long.valueOf(new Random().nextInt(10)));
		return result=list;
	}
	private static void getSite(List<Long> list,Long num){
		int i=0;
		int j=list.size();
		int k=(i+j)/2;
		System.out.println("num:"+num);
		int times=0;
	    do {
			k=(i+j)/2;
			System.out.println("k:"+k);
	    	if(num==list.get(k-1)){
	    		System.err.println(num+"在："+k);
	    		break;
	    	}else {
	    		if(num>list.get(k-1)){
	    			i=k;
	    		}else {
					j=k-1;
				}
			}
	    	System.out.println("i:"+i+"   j:"+j);
	    	if(i==j){
	    		System.err.println("无此数据");
	    	}
	    	times++;
		} while (i!=j&&times<10);
	    System.out.println("times:"+times);
	}
}
