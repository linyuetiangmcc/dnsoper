package gmcc.hxs.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpClientUtil {
    public static String doGet(String url,Map<String,String> map,String charset){
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = "";
        HttpResponse response = null;
        try{
            httpClient = new SSLClient();
            httpGet = new HttpGet(url);
            httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
            httpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
            Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
				Entry<String,String> elem = (Entry<String, String>) iterator.next();
                httpGet.addHeader(elem.getKey(), elem.getValue());
            }

            response = httpClient.execute(httpGet);

            if(response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();

                if (httpEntity.getContentEncoding() != null) {
                    if ("gzip".equalsIgnoreCase(httpEntity.getContentEncoding().getValue())) {
                        httpEntity = new GzipDecompressingEntity(httpEntity);
                    } else if ("deflate".equalsIgnoreCase(httpEntity.getContentEncoding().getValue())) {
                        httpEntity = new DeflateDecompressingEntity(httpEntity);
                    }
                }



                if(httpEntity != null){
                    result = EntityUtils.toString(httpEntity, charset);
                    //EntityUtils.toString()
                }
            }

            if(httpClient != null)
                httpClient.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static String doPut(String url,Map<String,String> map,String charset){
        CloseableHttpClient httpClient = null;
        //HttpGet httpGet = null;
        HttpPut httpPut = null;
        String result = "";
        HttpResponse response = null;
        try{
            httpClient = new SSLClient();
            httpPut = new HttpPut(url);
            httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
            httpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
            Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,String> elem = (Entry<String, String>) iterator.next();
                httpPut.addHeader(elem.getKey(), elem.getValue());
            }

            response = httpClient.execute(httpPut);

            if(response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();

                if (httpEntity.getContentEncoding() != null) {
                    if ("gzip".equalsIgnoreCase(httpEntity.getContentEncoding().getValue())) {
                        httpEntity = new GzipDecompressingEntity(httpEntity);
                    } else if ("deflate".equalsIgnoreCase(httpEntity.getContentEncoding().getValue())) {
                        httpEntity = new DeflateDecompressingEntity(httpEntity);
                    }
                }

                if(httpEntity != null){
                    result = EntityUtils.toString(httpEntity, charset);
                    //EntityUtils.toString()
                }

            }
            if(httpClient != null)
                httpClient.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static String doPostHttp(String url,Map<String,String> map,String charset,String content){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse response = null;
        String result = null;
        try{
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 1500);
            httpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 1500);
            Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,String> elem = (Entry<String, String>) iterator.next();
                httpPost.addHeader(elem.getKey(), elem.getValue());
            }

            StringEntity entity = new StringEntity(content, "UTF-8");
            httpPost.setEntity(entity);

            response = httpClient.execute(httpPost);
            if(response != null){
/*                if(response.getStatusLine().getStatusCode() == 200)
                    return "200 OK";*/

                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,"UTF-8");
                }
            }
            if(httpClient != null)
                httpClient.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static String doPostHttps(String url,Map<String,String> map,String charset,String content){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse response = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 1500);
            httpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 1500);
            Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,String> elem = (Entry<String, String>) iterator.next();
                httpPost.addHeader(elem.getKey(), elem.getValue());
            }

            StringEntity entity = new StringEntity(content, "UTF-8");
            httpPost.setEntity(entity);

            response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
            if(httpClient != null)
                httpClient.close();
        }catch(Exception ex){
            //ex.printStackTrace();
        }
        return result;
    }

    public static HttpResponse doGetResponse(String url,Map<String,String> map,String charset){
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = "";
        HttpResponse response = null;
        try{
            httpClient = new SSLClient();
            httpGet = new HttpGet(url);
            httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
            httpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
            Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,String> elem = (Entry<String, String>) iterator.next();
                httpGet.addHeader(elem.getKey(), elem.getValue());
            }
            response = httpClient.execute(httpGet);
            if(httpClient != null)
                httpClient.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }
}