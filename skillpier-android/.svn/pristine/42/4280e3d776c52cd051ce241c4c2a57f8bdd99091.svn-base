package com.quark.skillopedia.uiview.fenlei;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CatetoryListResponse;
import com.quark.api.auto.bean.CourseCategoryListRequest;
import com.quark.api.auto.bean.HistoryResponse;
import com.quark.api.auto.bean.ListCategoryBean;
import com.quark.api.auto.bean.ListCityBean;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.HistoryAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.util.JsonUtil;
import com.quark.skillopedia.util.Utils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * 搜索
 *
 * @author leon
 * @time 2016/7/12 0012 下午 5:55
 */
public class SearchActivity extends BaseActivity {
    @InjectView(R.id.editview)
    AutoCompleteTextView editview;
    @InjectView(R.id.tips)
    TextView tips;
    @InjectView(R.id.historyview)
    LinearLayout historyview;
    @InjectView(R.id.GridView)
    android.widget.GridView gridView;
    @InjectView(R.id.zipcode)
    AutoCompleteTextView zipcode;

    List<HistoryResponse> history = new ArrayList<>();
    HistoryAdapter adapter;
    String keystr;
    String from;  //首页进入：index
    List<ListCategoryBean> catetorystemp = new ArrayList<>();
    private String[] items;
    private String[] catetorystr;
    String divisionSpace = "        ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        ButterKnife.inject(this);

        categoryListRequest();
        from = (String) getValue4Intent("from");
        List<HistoryResponse> his = new AppParam().getHistory(this);
        if (his != null && his.size() > 0) {
            history.addAll(his);
            tips.setVisibility(View.GONE);
            historyview.setVisibility(View.VISIBLE);
        } else {
            tips.setVisibility(View.VISIBLE);
            historyview.setVisibility(View.GONE);
        }
        adapter = new HistoryAdapter(SearchActivity.this, history, handler, 0);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keystr = history.get(position).getSearchkey();
                String zipcodestr = history.get(position).getSearchkey();
                if (!Utils.isEmpty(keystr)) {
                    saveHistory(keystr, "save", zipcodestr);
                }

                if (from.equals("courselist")) {
                    Intent intent = new Intent();
                    intent.putExtra("key", keystr);
                    intent.putExtra("zipcodestr", zipcodestr);
                    setResult(2001, intent);
                    finish();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("key", keystr);
                    intent.putExtra("datatype", "1");
                    intent.putExtra("zipcodestr", zipcodestr);
                    intent.setClass(SearchActivity.this, CourseListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void getCategory() {
        if (catetorystr != null) {
            editview.setDropDownWidth(new AppParam().getScreenWidth(this));
            editview.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_dropdown_item_1line_my, catetorystr));
            editview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ts = editview.getText().toString();
                    if (Utils.isEmpty(ts)) {
                        editview.showDropDown();
                    }
                }
            });
        }
    }

    public void getzcode() {
        List<ListCityBean> zcodes = new AppParam().getZcode(this);
        if (zcodes != null) {
            items = new String[zcodes.size()];
            for (int i = 0; i < zcodes.size(); i++) {
                items[i] = zcodes.get(i).getZipcode() + divisionSpace + zcodes.get(i).getCity();
            }
            zipcode.setDropDownWidth(new AppParam().getScreenWidth(this));
            zipcode.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_dropdown_item_1line_my, items));
            zipcode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    zipcode.setText(items[position].split(divisionSpace)[0]);
                    CharSequence charSequence = zipcode.getText();
                    if (charSequence instanceof Spannable) {
                        Spannable spanText = (Spannable) charSequence;
                        Selection.setSelection(spanText, charSequence.length());
                    }
                }
            });
        }
    }

    @OnClick({R.id.searchbar, R.id.main_left, R.id.clear})
    public void se(View v) {
        switch (v.getId()) {
            case R.id.searchbar:
                keystr = editview.getText().toString();
                String zipcodestr = zipcode.getText().toString();
                if (!Utils.isEmpty(keystr)) {
                    saveHistory(keystr, "save", zipcodestr);
                }
                if (from.equals("courselist")) {
                    Intent intent = new Intent();
                    intent.putExtra("key", keystr);
                    intent.putExtra("zipcodestr", zipcodestr);
                    setResult(2001, intent);
                    finish();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("key", keystr);
                    intent.putExtra("datatype", "1");
                    intent.putExtra("zipcodestr", zipcodestr);
                    intent.setClass(this, CourseListActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.main_left:
                finish();
                break;
            case R.id.clear:
                saveHistory("", "clear", "");
                break;
        }
    }

    public void saveHistory(String key, String type, String zipcodestr) {
        if (type.equals("save")) {
            HistoryResponse histories = new HistoryResponse();
            histories.setSearchkey(key);
            histories.setZipcodestr(zipcodestr);
            boolean nohaved = true;
            for (int i = 0; i < history.size(); i++) {
                if (history.get(i).getSearchkey().equals(key)) {
                    nohaved = false;
                }
            }
            if (nohaved) {
                history.add(histories);
                try {
                    JSONArray hstjson = JsonUtil.coverModelToJSONArray(history);
                    String jsstr = hstjson.toString();
                    new AppParam().setSharedPreferencesy(this, "history", jsstr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (type.equals("remove")) {
            HistoryResponse histories = new HistoryResponse();
            histories.setSearchkey(key);
            boolean nohaved = true;
            int current = 0;
            for (int i = 0; i < history.size(); i++) {
                if (history.get(i).getSearchkey().equals(key)) {
                    current = i;
                    nohaved = false;
                }
            }
            if (!nohaved) {
                history.remove(current);
                try {
                    JSONArray hstjson = JsonUtil.coverModelToJSONArray(history);
                    String jsstr;
                    if (hstjson == null || hstjson.length() == 0) {
                        jsstr = "";
                    } else {
                        jsstr = hstjson.toString();
                    }
                    new AppParam().setSharedPreferencesy(this, "history", jsstr);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (history != null && history.size() > 0) {
                    tips.setVisibility(View.GONE);
                    historyview.setVisibility(View.VISIBLE);
                } else {
                    tips.setVisibility(View.VISIBLE);
                    historyview.setVisibility(View.GONE);
                }

                adapter.notifyDataSetChanged();
            }
        } else if (type.equals("clear")) {
            history.clear();
            new AppParam().setSharedPreferencesy(this, "history", "");
            if (history != null && history.size() > 0) {
                tips.setVisibility(View.GONE);
                historyview.setVisibility(View.VISIBLE);
            } else {
                tips.setVisibility(View.VISIBLE);
                historyview.setVisibility(View.GONE);
            }
            adapter.notifyDataSetChanged();
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    saveHistory(history.get(msg.arg1).getSearchkey(), "remove", "");
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    public void categoryListRequest() {
        CourseCategoryListRequest rq = new CourseCategoryListRequest();
        QuarkApi.HttpRequest(rq, mHandlercategory);
    }

    private final AsyncHttpResponseHandler mHandlercategory = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, SearchActivity.this, CatetoryListResponse.class);
            if (kd != null) {
                CatetoryListResponse info = (CatetoryListResponse) kd;
                catetorystemp.addAll(info.getCategoryListResult().getCategorys());
                if (catetorystemp != null) {
                    catetorystr = new String[catetorystemp.size()];
                    for (int i = 0; i < catetorystemp.size(); i++) {
                        catetorystr[i] = catetorystemp.get(i).getCategory_02_name();
                    }
                    getCategory();
                }
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            getzcode();
        }
    }
}
