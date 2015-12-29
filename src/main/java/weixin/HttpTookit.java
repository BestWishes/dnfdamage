package weixin;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
 
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
 
import com.alibaba.fastjson.JSON;
 
public class HttpTookit {
   
    private String loginUrl = "https://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";
    private final String HOST_NAME = "Host";
    private final String HOST_VALUE = "mp.weixin.qq.com";
    private final String HOME_URL = "https://mp.weixin.qq.com/";
    private boolean isLogin ;
    private String cookie;
    private final String USER_AGENT_VALUE="Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0";
    private final String USER_AGENT_NAME="User-Agent";
    private final String CONTENT_TYPE_NAME="Content-Type";
    private final String CONTENT_TYPE_VALUE="application/x-www-form-urlencoded; charset=UTF-8";
    private final String REFERER_NAME="Referer";
    private int    RETURN_SUCCESS = 0;
    private String userName;
    private String password;
    private final String COOKIE_NAME="Cookie";
    private String personalHomeUrl ;
    private String ticket_id;
    private String ticket;
    private String token;
    private String uplaodImageUrl;
    private final String BOUNDARY = "----------ThIs_Is_tHe_bouNdaRY_$";
    private final String UPLOAD_SINGLE_PHOTO_URL  = "https://mp.weixin.qq.com/cgi-bin/operate_appmsg";
    private final String REMOVE_IMAGE_FILE_URL = "https://mp.weixin.qq.com/cgi-bin/modifyfile";
     
    public HttpTookit(String userName,String password){
        this.userName = userName;
        this.password = password;
    }
     
    /**
     * 加载Tock信息
     * @throws KeyManagementException
     * @throws ClientProtocolException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public void loadTicket() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, IOException{
         
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(HOST_NAME, HOST_VALUE);
        headers.put(REFERER_NAME, HOME_URL);
        headers.put(USER_AGENT_NAME, USER_AGENT_VALUE);
        headers.put(COOKIE_NAME, cookie);
        Result result  = SendRequest.sendGet(personalHomeUrl, headers, null, "utf-8",true);
        String responseText = EntityUtils.toString(result.getHttpEntity());
         
        token = TextParse.prase(responseText, "t:[\"][\\d]+?[\"]", 1).get(0).split(":")[1];
        token = token.substring(1,token.length()-1);
        ticket = TextParse.prase(responseText, "ticket:[\"][\\dA-Za-z]+?[\"]", 1).get(0).split(":")[1];
        ticket = ticket.substring(1,ticket.length()-1);
        ticket_id = TextParse.prase(responseText, "user_name:[\"].+?[\"]", 1).get(0).split(":")[1];
        ticket_id = ticket_id.substring(1,ticket_id.length()-1);
        this.cookie = result.getCookie();
    }
     
    /**
     * 登录
     * @return
     * @throws KeyManagementException
     * @throws ClientProtocolException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public LoginReurnInfo login() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, IOException{
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(CONTENT_TYPE_NAME, CONTENT_TYPE_VALUE);
        headers.put(HOST_NAME, HOST_VALUE);
        headers.put(REFERER_NAME, HOME_URL);
        headers.put(USER_AGENT_NAME, USER_AGENT_VALUE);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("lang", "zh_CN");
        param.put("f", "json");
        param.put("imagecode", "");
        param.put("username", this.userName);
        param.put("pwd", DigestUtils.md5Hex(this.password.getBytes()));
        Result result  = SendRequest.sendPost(loginUrl, headers, param, "utf-8",true);
        String responseText = EntityUtils.toString(result.getHttpEntity());
        LoginReurnInfo  loginReurnInfo  =JSON.parseObject(responseText,LoginReurnInfo.class);
        if(loginReurnInfo.getErrCode()==0){
            isLogin = true;
            cookie = result.getCookie();
            personalHomeUrl = HOME_URL+loginReurnInfo.getErrMsg();
            loadTicket();
            uplaodImageUrl  = HOME_URL+"cgi-bin/filetransfer?action=upload_material&f=json&ticket_id="+ticket_id+"&ticket="+ticket+"&token="+token+"&lang=zh_CN";
        }
        return loginReurnInfo;
    }
     
    /**
     * 文件上传
     * @param path
     * @return
     * @throws KeyManagementException
     * @throws ClientProtocolException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public UplaodFIleReturnInfo fileUpload(String path) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, IOException {
     
        if(!isLogin){
               if(login().getErrCode()!=RETURN_SUCCESS){
                   return null;
               }
           }
        HttpPost request = new HttpPost(uplaodImageUrl);     
        File uploadFile = new File(path);
        FileBody file = new FileBody(uploadFile,"image/jpg");  
        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,BOUNDARY, Charset.defaultCharset());  
        multipartEntity.addPart("Upload",new StringBody("Submit Query", Charset.forName("UTF-8")));    
        multipartEntity.addPart("folder",new StringBody("/cgi-bin/uploads",Charset.forName("UTF-8")));    
        multipartEntity.addPart("file",file); 
          
        request.setEntity(multipartEntity);  
        request.addHeader(CONTENT_TYPE_NAME,"multipart/form-data; boundary="+BOUNDARY); 
        request.addHeader(COOKIE_NAME,cookie);
        request.addHeader(USER_AGENT_NAME, USER_AGENT_VALUE);
        request.addHeader(HOST_NAME,HOST_VALUE);
        
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        HttpResponse response =httpClient.execute(request);  
         
        if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){  
        HttpEntity entity = response.getEntity();  
        if (entity != null) {  
            String responseText =  EntityUtils.toString(entity); 
            return JSON.parseObject(responseText,UplaodFIleReturnInfo.class);
           }  
         }
     return null;
   }  
     
    /**
     * 更新图文
     * @param appMsgId
     * @param singlePhotoAll
     * @return
     * @throws KeyManagementException
     * @throws ClientProtocolException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
   public BaseResp updateSinglePhoto(String appMsgId,SinglePhoto...singlePhotoAll) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, IOException{
     
       if(!isLogin){
           if(login().getErrCode()!=RETURN_SUCCESS){
               return null;
           }
       }
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(CONTENT_TYPE_NAME, CONTENT_TYPE_VALUE);
        headers.put(HOST_NAME, HOST_VALUE);
        headers.put(USER_AGENT_NAME, USER_AGENT_VALUE);
        headers.put(COOKIE_NAME,cookie );
        headers.put(REFERER_NAME, "https://mp.weixin.qq.com/cgi-bin/appmsg?t=media/appmsg_edit&action=edit&type=10&isMul=0&isNew=1&lang=zh_CN&token="+token);
        HashMap<String, String> param = new HashMap<String, String>();
        int i=0;
        for (SinglePhoto singlePhoto : singlePhotoAll) {
            param.put("title"+i, singlePhoto.getTitle());
            param.put("digest"+i, singlePhoto.getDigest());
            param.put("author"+i, singlePhoto.getAuthor());
            param.put("fileid"+i, fileUpload(singlePhoto.getFilePath()).getContent());
            param.put("show_cover_pic"+i, singlePhoto.isDisplayCover()?"1":"0");
            param.put("content"+i,singlePhoto.getContent());
            param.put("sourceurl"+(i++), singlePhoto.getSourceurl());
        }
         
        if(appMsgId!=null){
            param.put("AppMsgId",appMsgId);
            param.put("sub", "update");
        }else{
            param.put("sub", "create");
        }
        param.put("count",String.valueOf(singlePhotoAll.length));
        param.put("ajax", "1");
        param.put("token", token);
        param.put("lang", "zh_CN");
        param.put("random", "0.7844814438014191");
        param.put("f", "json");
        param.put("t", "ajax-response");
        param.put("type", "10");
        Result result  = SendRequest.sendPost(UPLOAD_SINGLE_PHOTO_URL, headers, param, "utf-8",true);
        String responseText = EntityUtils.toString(result.getHttpEntity());
        return JSON.parseObject(responseText,BaseResp.class);
   }
   public BaseResp addSinglePhoto(SinglePhoto...singlePhotoAll) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, IOException{
       return updateSinglePhoto(null, singlePhotoAll);
   }
    
   /**
    * 删除单图文
    * @param appMsgId
    * @return
    * @throws KeyManagementException
    * @throws ClientProtocolException
    * @throws NoSuchAlgorithmException
    * @throws IOException
    */
   public BaseResp removeSinglePhoto(String appMsgId) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, IOException{
        
       if(!isLogin){
           if(login().getErrCode()!=RETURN_SUCCESS){
               return null;
           }
       }
        
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(CONTENT_TYPE_NAME, CONTENT_TYPE_VALUE);
        headers.put(HOST_NAME, HOST_VALUE);
        headers.put(USER_AGENT_NAME, USER_AGENT_VALUE);
        headers.put(COOKIE_NAME,cookie );
        headers.put("x-requested-with","XMLHttpRequest" );
        headers.put(REFERER_NAME,"https://mp.weixin.qq.com/cgi-bin/appmsg?begin=0&count=10&t=media/appmsg_list&type=10&action=list&token="+token+"&lang=zh_CN");
    
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("AppMsgId", appMsgId);
        param.put("token",token);
        param.put("lang", "zh_CN");
        param.put("random", "0.5512174578492246");
        param.put("f", "json");
        param.put("ajax", "1");
        param.put("sub", "del");
        param.put("t", "ajax-response");
        Result result  = SendRequest.sendPost(UPLOAD_SINGLE_PHOTO_URL, headers, param, "utf-8",true);
        String responseText = EntityUtils.toString(result.getHttpEntity());
        return JSON.parseObject(responseText,BaseResp.class);
    
   }
    
   /**
    * 删除已经上传的文件
    * @param fileid
    * @return
    * @throws KeyManagementException
    * @throws ClientProtocolException
    * @throws NoSuchAlgorithmException
    * @throws IOException
    */
   public BaseResp removeImage(String fileid) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, IOException{
        
       if(!isLogin){
           if(login().getErrCode()!=RETURN_SUCCESS){
               return null;
           }
       }
        
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(CONTENT_TYPE_NAME, CONTENT_TYPE_VALUE);
        headers.put(HOST_NAME, HOST_VALUE);
        headers.put(USER_AGENT_NAME, USER_AGENT_VALUE);
        headers.put(COOKIE_NAME,cookie );
        headers.put("x-requested-with","XMLHttpRequest" );
        headers.put(REFERER_NAME,"https://mp.weixin.qq.com/cgi-bin/filepage?type=2&begin=0&count=10&t=media/list&token="+token+"&lang=zh_CN");
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("fileid",fileid);
        param.put("token", token);
        param.put("lang", "zh_CN");
        param.put("random", "0.7610577117156507");
        param.put("f", "json");
        param.put("ajax", "1");
        param.put("oper", "del");
        param.put("t", "ajax-response");
         
        Result result  = SendRequest.sendPost(REMOVE_IMAGE_FILE_URL, headers, param, "utf-8",true);
        String responseText = EntityUtils.toString(result.getHttpEntity());
        return JSON.parseObject(responseText,BaseResp.class);
   }
    
   /**
    * 获得已经上传的文件
    * @return
    * @throws KeyManagementException
    * @throws ClientProtocolException
    * @throws NoSuchAlgorithmException
    * @throws IOException
    */
   public List<MaterialInfo>  getUploadFileInfo() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, IOException{
       if(!isLogin){
           if(login().getErrCode()!=RETURN_SUCCESS){
               return null;
           }
       }
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(CONTENT_TYPE_NAME, CONTENT_TYPE_VALUE);
        headers.put(HOST_NAME, HOST_VALUE);
        headers.put(USER_AGENT_NAME, USER_AGENT_VALUE);
        headers.put(COOKIE_NAME,cookie );
         
        Result result  = SendRequest.sendGet("https://mp.weixin.qq.com/cgi-bin/filepage?type=2&begin=0&count=100000000000000000&t=media/list&token="+token+"&lang=zh_CN", headers, null, "utf-8",true);
        String responseText = EntityUtils.toString(result.getHttpEntity());
        responseText = TextParse.prase(responseText,"\\[[{]\"file_id\".+?\\]").get(0);
        return JSON.parseArray(responseText, MaterialInfo.class);
   }
    
   /**
    * 下载图片
    * @param path
    * @param materialInfoAll
    * @throws KeyManagementException
    * @throws ClientProtocolException
    * @throws NoSuchAlgorithmException
    * @throws IOException
    */
   public void downloadImage(String path,MaterialInfo...materialInfoAll) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, IOException{
        
       if(!isLogin){
           if(login().getErrCode()!=RETURN_SUCCESS){
               return ;
           }
       }
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(CONTENT_TYPE_NAME, CONTENT_TYPE_VALUE);
        headers.put(HOST_NAME, HOST_VALUE);
        headers.put(USER_AGENT_NAME, USER_AGENT_VALUE);
        headers.put(COOKIE_NAME,cookie );
        
       for (MaterialInfo materialInfo : materialInfoAll) {
           String downloadAddress = "https://mp.weixin.qq.com/cgi-bin/getimgdata?token="+token+"&"+ URLEncoder.encode(("msgid={msgid}"),"utf-8")+"&mode=small&source=file&fileId="+materialInfo.getFile_id()+"&ow=-1";
           Result result  = SendRequest.sendGet(downloadAddress,headers, null, "utf-8",true);
           svaeFile(result.getHttpEntity().getContent(),path+"/"+materialInfo.getName());
       }
   }
    
   private void svaeFile(InputStream input,String savePath) throws IOException{
       int b = 0;
       FileOutputStream output = new FileOutputStream(new File(savePath));
       while((b=input.read())!=-1){
           output.write(b);
       }
       output.close();
       input.close();
   }
}