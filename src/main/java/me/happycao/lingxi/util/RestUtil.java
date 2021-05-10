package me.happycao.lingxi.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : happyc
 * e-mail  : bafs.jy@live.com
 * time    : 2019/04/20
 * desc    : http util
 * version : 1.0
 */
public final class RestUtil {

    private static final Logger logger = LoggerFactory.getLogger(RestUtil.class);

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";

    private static final String CHARSET = "UTF-8";

    enum HttpMethod {
        /**
         * mode
         */
        GET,
        POST,
        PUT,
        DELETE
    }

    /**
     * get请求
     */
    public static Builder doGet() {
        return new Builder(HttpMethod.GET);
    }

    /**
     * post请求
     */
    public static Builder doPost() {
        return new Builder(HttpMethod.POST);
    }

    public static Builder doPut() {
        return new Builder(HttpMethod.PUT);
    }

    public static Builder doDelete() {
        return new Builder(HttpMethod.DELETE);
    }

    public static class Builder {

        private final HttpMethod method;
        private boolean isJson;
        private String url;
        private final Map<String, String> headers;
        private final Map<String, String> formParams;
        private final Map<String, List<String>> urlParams;
        private String requestBody;

        public Builder(HttpMethod method) {
            this.method = method;
            this.isJson = false;
            this.url = "";
            this.headers = new HashMap<>();
            this.formParams = new HashMap<>();
            this.urlParams = new HashMap<>();
            this.requestBody = "";
        }

        /**
         * 请求url
         */
        public Builder url(String url) {
            this.url = url;
            return this;
        }

        /**
         * json
         */
        public Builder isJson() {
            this.isJson = true;
            return this;
        }

        /**
         * 添加请求头
         */
        public Builder addHeader(String key, String value) {
            if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
                this.headers.put(key, value);
            }
            return this;
        }

        /**
         * 添加请求头
         */
        public Builder addHeaders(Map<String, String> headers) {
            if (headers != null && !headers.isEmpty()) {
                this.headers.putAll(headers);
            }
            return this;
        }

        /**
         * 添加参数
         */
        public Builder addParam(String key, String value) {
            if (key == null || value == null) {
                return this;
            }
            this.formParams.put(key, value);
            return this;
        }

        /**
         * 添加url参数
         */
        public Builder addUrlParam(String key, String value) {
            if (value == null) {
                return this;
            }
            List<String> list = new ArrayList<>();
            list.add(value);
            this.urlParams.put(key, list);
            return this;
        }

        /**
         * 添加url参数
         */
        public Builder addUrlParam(String key, List<String> values) {
            if (values == null || values.size() == 0) {
                return this;
            }
            this.urlParams.put(key, values);
            return this;
        }

        /**
         * 设置body参数
         */
        public Builder setBody(String requestBody) {
            this.requestBody = requestBody;
            this.isJson = true;
            return this;
        }

        /**
         * 设置body参数
         */
        public Builder setBody(Object requestBody) {
            this.requestBody = JSONObject.toJSONString(requestBody);
            this.isJson = true;
            return this;
        }

        /**
         * 构建请求头
         */
        private void buildHeader(HttpRequestBase httpRequest) {
            if (!headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    if (CONTENT_TYPE.equals(entry.getKey())) {
                        break;
                    }
                    httpRequest.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }

        /**
         * 构建相关设置
         */
        private RequestConfig buildRequestConfig(){
            // 设置请求和传输超时时间
            return RequestConfig.custom()
                    .setSocketTimeout(6000)
                    .setConnectTimeout(6000)
                    .build();
        }

        /**
         * 构建HttpClient
         */
        private CloseableHttpClient buildHttpClient() {
            return HttpClients.createDefault();
        }

        /**
         * 拼装url参数
         */
        private static String buildUrlParams(String url, Map<String, List<String>> params) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(url);
                if (url.indexOf('&') > 0 || url.indexOf('?') > 0) {
                    sb.append("&");
                } else {
                    sb.append("?");
                }
                for (Map.Entry<String, List<String>> urlParams : params.entrySet()) {
                    List<String> urlValues = urlParams.getValue();
                    for (String value : urlValues) {
                        // 对参数进行utf-8编码
                        String urlValue = URLEncoder.encode(value, CHARSET);
                        sb.append(urlParams.getKey()).append("=").append(urlValue).append("&");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                return sb.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return url;
        }

        /**
         * 构建FormEntity
         */
        private HttpEntity buildFormEntity() {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setContentType(ContentType.MULTIPART_FORM_DATA);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            if (!formParams.isEmpty()) {
                for (Map.Entry<String, String> param : this.formParams.entrySet()) {
                    builder.addPart(param.getKey(), new StringBody(param.getValue(), ContentType.MULTIPART_FORM_DATA));
                }
            }
            return builder.build();
        }

        /**
         * 构建StringEntity
         */
        private StringEntity buildStringEntity() {
            requestBody = requestBody == null ? "" : requestBody;
            StringEntity stringEntity = new StringEntity(requestBody, CHARSET);
            stringEntity.setContentType(APPLICATION_JSON_UTF8);
            return stringEntity;
        }

        /**
         * 发起请求
         */
        public <T> T exchange(Class<T> bean) {
            // url参数
            if (!urlParams.isEmpty()) {
                url = buildUrlParams(url, urlParams);
            }
            logger.debug("url is {}", url);
            // 请求方式
            switch (method) {
                case GET:
                    return exchangeGet(bean);
                case POST:
                    return exchangePost(bean);
                case PUT:
                    return exchangePut(bean);
                case DELETE:
                    return exchangeDelete(bean);
                default:
                    return exchangeGet(bean);
            }
        }

        private <T> T exchangePut(Class<T> bean) {
            HttpPut httpPut = new HttpPut(url);
            httpPut.setConfig(buildRequestConfig());
            buildHeader(httpPut);

            if (isJson) {
                httpPut.setEntity(buildStringEntity());
            } else {
                httpPut.setEntity(buildFormEntity());
            }
            CloseableHttpClient httpClient = buildHttpClient();

            CloseableHttpResponse response;
            try {
                response = httpClient.execute(httpPut);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return null;
            }
            return parsingResponse(response, httpClient, bean);
        }

        private <T> T exchangeDelete(Class<T> bean) {
            HttpDelete httpDelete = new HttpDelete(url);
            httpDelete.setConfig(buildRequestConfig());
            buildHeader(httpDelete);

            CloseableHttpClient httpClient = buildHttpClient();

            CloseableHttpResponse response;
            try {
                response = httpClient.execute(httpDelete);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return null;
            }
            return parsingResponse(response, httpClient, bean);
        }

        /**
         * 执行post请求
         */
        private <T> T exchangePost(Class<T> bean) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(buildRequestConfig());
            buildHeader(httpPost);

            if (isJson) {
                httpPost.setEntity(buildStringEntity());
            } else {
                httpPost.setEntity(buildFormEntity());
            }
            CloseableHttpClient httpClient = buildHttpClient();

            CloseableHttpResponse response;
            try {
                response = httpClient.execute(httpPost);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return null;
            }
            return parsingResponse(response, httpClient, bean);
        }

        /**
         * 执行get请求
         */
        private <T> T exchangeGet(Class<T> bean) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(buildRequestConfig());
            buildHeader(httpGet);

            CloseableHttpClient httpClient = buildHttpClient();

            CloseableHttpResponse response;
            try {
                response = httpClient.execute(httpGet);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return null;
            }
            return parsingResponse(response, httpClient, bean);
        }

        /**
         * 解析结果
         */
        private <T> T parsingResponse(CloseableHttpResponse response, CloseableHttpClient httpClient, Class<T> bean) {
            if (response == null) {
                return null;
            }

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                logger.error("Status Code {}", statusCode);
            }

            String body;
            try {
                body = EntityUtils.toString(entity, CHARSET);
                if (body == null || body.length() == 0) {
                    return null;
                }
                if (bean.getName().equals(String.class.getName())) {
                    return (T) body;
                } else {
                    return JSONObject.parseObject(body, bean);
                }
            } catch (IOException | JSONException e) {
                logger.error(e.getMessage(), e);
                return null;
            } finally {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
