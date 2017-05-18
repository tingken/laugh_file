package com.quark.skillopedia.api.remote;

import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.quark.api.auto.bean.FileItem;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.util.Utils;
import com.quark.skillopedia.util.ValidateHelper;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuarkApi {

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @param handler
     */
    public static void login(String username, String password,
                             AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("pwd", password);
        params.put("keep_login", 1);
        String loginurl = "action/api/login_validate";
        ApiHttpClient.post(loginurl, params, handler);
    }


    public static void HttpRequest(Object javaBean, AsyncHttpResponseHandler handler) {
        if (ValidateHelper.isNetworkAvailable(AppContext.instance)) {
            boolean needsign = false;
            List<MD5Item> dlist = new ArrayList<MD5Item>();
            String urlStr = "";
            RequestParams params = new RequestParams();
            Method[] methods = javaBean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                try {
                    if (method.getName().startsWith("get")) {
                        String field = method.getName();
                        field = field.substring(field.indexOf("get") + 3);
                        field = field.toLowerCase().charAt(0) + field.substring(1);
                        Object value = method.invoke(javaBean, (Object[]) null);
                        if (field.equals("url")) {
                            urlStr = value.toString();
                        } else if (field.equals("invoke")) {
                            params.put(field, "app");
                        } else if(field.equals("token")){
                            if (!Utils.isEmpty(new AppParam().getToken(new AppContext().instance))){
                                params.put("token", new AppParam().getToken(new AppContext().instance));
                                MD5Item md = new MD5Item("token", new AppParam().getToken(new AppContext().instance));
                                dlist.add(md);
                            }
                        }else {
                            if ((value != null) && (!value.toString().equals(""))) {
                                params.put(field, null == value ? "" : value.toString());
                                if (!field.equals("method")) {
                                    MD5Item md = new MD5Item(field, value.toString());
                                    dlist.add(md);  //save md5 need
                                }
                            }
                            if (field.equals("app_sign")) {
                                needsign = true;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (needsign) {
                params.put("app_sign", sign(dlist));
            }

            ApiHttpClient.get(urlStr, params, handler);
        } else {
            Toast.makeText(AppContext.instance, "当前网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    //md5 sign
    public static String sign(List<MD5Item> dlist) {
        Collections.sort(dlist, new Comparator<MD5Item>() {
            @Override
            public int compare(MD5Item b1, MD5Item b2) {
                return b1.getKey().compareTo(b2.getKey());
            }
        });
        String signStr = "";
        for (int i = 0; i < dlist.size(); i++) {
            signStr += dlist.get(i).getKey() + "=" + dlist.get(i).getValue() + "&";
        }
        signStr += "key=" + new AppParam().app_quark_key;
        String sign = com.jfinal.kit.EncryptionKit.md5Encrypt(signStr).toUpperCase();
        return sign;
    }

/**
 * 不需要token 但是要签名
 * @author leon
 * @time 2016/6/29 0029 下午 4:30
 */

    public static void HttpRequestNoTS(Object javaBean, AsyncHttpResponseHandler handler) {
        if (ValidateHelper.isNetworkAvailable(AppContext.instance)) {
            boolean needsign = false;
            List<MD5Item> dlist = new ArrayList<MD5Item>();
            String urlStr = "";
            RequestParams params = new RequestParams();
            Method[] methods = javaBean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                try {
                    if (method.getName().startsWith("get")) {
                        String field = method.getName();
                        field = field.substring(field.indexOf("get") + 3);
                        field = field.toLowerCase().charAt(0) + field.substring(1);
                        Object value = method.invoke(javaBean, (Object[]) null);
                        if (field.equals("url")) {
                            urlStr = value.toString();
                        } else if (field.equals("invoke")) {
                            params.put(field, "app");

                        } else {
                            if ((value != null) && (!value.toString().equals(""))) {
                                params.put(field, null == value ? "" : value.toString());
                                if (!field.equals("method")) {
                                    MD5Item md = new MD5Item(field, value.toString());
                                    dlist.add(md);  //save md5 need
                                }
                            }
                            if (field.equals("app_sign")) {
                                needsign = true;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (needsign) {
                params.put("app_sign", sign(dlist));
            }

            ApiHttpClient.get(urlStr, params, handler);
        } else {
            Toast.makeText(AppContext.instance, "当前网络不可用", Toast.LENGTH_SHORT).show();
        }
    }


    //上傳文件
    public static void HttpuploadFile(Object javaBean, List<FileItem> fileItems, HttpCallBack httpCallBack) {
        String urlStr = "";
        HttpParams params = new HttpParams();
        Method[] methods = javaBean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    Object value = method.invoke(javaBean, (Object[]) null);
                    if (field.equals("url")) {
                        urlStr = value.toString();
                    } else if (field.equals("invoke")) {
                        params.put(field, "app");
                    } else {
                        if ((value != null) && (!value.toString().equals(""))) {
                            params.put(field, null == value ? "" : value.toString());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String token = new AppParam().getToken(new AppContext().instance);
        if (!Utils.isEmpty(token)) {
            params.put("token", token);
        }
        if (fileItems != null && fileItems.size() > 0) {
            for (int i = 0; i < fileItems.size(); i++) {
                params.put(fileItems.get(i).getName(), fileItems.get(i).getFile());
            }
        }

        KJHttp kjh = new KJHttp();
        kjh.post(ApiHttpClient.HOSTURL + urlStr, params, httpCallBack);
    }


    public static void openIdLogin(String s) {

    }


    public static void getNewsList(int catalog, int page,
                                   AsyncHttpResponseHandler handler) {
    }

    private static void uploadLog(String data, String report,
                                  AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("app", "1");
        params.put("report", report);
        params.put("msg", data);
        ApiHttpClient.post("action/api/user_report_to_admin", params, handler);
    }

    public static void uploadLog(String data, AsyncHttpResponseHandler handler) {
        uploadLog(data, "1", handler);
    }

    /**
     *
     * 所有的参数都签名 空则为 key=""
     * @author leon
     * @time 2016/6/28 0028 下午 3:40
     *
     */
    public static void HttpRequestAllSign(Object javaBean, AsyncHttpResponseHandler handler) {
        if (ValidateHelper.isNetworkAvailable(AppContext.instance)) {
            boolean needsign = false;
            List<MD5Item> dlist = new ArrayList<MD5Item>();
            String urlStr = "";
            RequestParams params = new RequestParams();
            Method[] methods = javaBean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                try {
                    if (method.getName().startsWith("get")) {
                        String field = method.getName();
                        field = field.substring(field.indexOf("get") + 3);
                        field = field.toLowerCase().charAt(0) + field.substring(1);
                        Object value = method.invoke(javaBean, (Object[]) null);
                        if (field.equals("url")) {   //url invoke method 不放入签名
                            urlStr = value.toString();
                        } else if (field.equals("invoke")) {
                            params.put(field, "app");
                        } else if (field.equals("method")){
                        }else{
                            if ((value != null) && (!value.toString().equals(""))) {
                                params.put(field, null == value ? "" : value.toString());
                                MD5Item md = new MD5Item(field, value.toString());
                                dlist.add(md);  //save md5 need
                            }else if (field.equals("app_sign")) {
                                needsign = true;
                            }else{
                                params.put(field, null == value ? "" : value.toString());
                                MD5Item md = new MD5Item(field, "");
                                dlist.add(md);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            params.put("token", new AppParam().getToken(new AppContext().instance));
            MD5Item md = new MD5Item("token", new AppParam().getToken(new AppContext().instance));
            dlist.add(md);  //save md5 need

            if (needsign) {
                params.put("app_sign", sign(dlist));
            }

            ApiHttpClient.get(urlStr, params, handler);
        } else {
            Toast.makeText(AppContext.instance, "当前网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

}
