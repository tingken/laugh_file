package com.quark.skillopedia.uiview.fenlei;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.CatetoryListResponse;
import com.quark.api.auto.bean.CourseCategoryListRequest;
import com.quark.api.auto.bean.CourseListRequest;
import com.quark.api.auto.bean.CourseListResponse;
import com.quark.api.auto.bean.FilterBean;
import com.quark.api.auto.bean.ListCategoryBean;
import com.quark.api.auto.bean.ListCourse;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.CourseListAdapter;
import com.quark.skillopedia.adapter.TaskTypesAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.AutoListView;
import com.quark.skillopedia.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * @author pan
 *         created at 2016/5/27 0027
 *         教练课程列表
 */
public class CourseListActivity extends BaseActivity implements AutoListView.OnRefreshListener, AutoListView.OnLoadListener {

    ArrayList<ListCourse> datas;
    CourseListAdapter adapter;

    @InjectView(R.id.xlstv)
    AutoListView xlstv;
    @InjectView(R.id.left_img)
    ImageView leftImg;
    @InjectView(R.id.main_left)
    LinearLayout mainLeft;
    @InjectView(R.id.type1)
    TextView type1;
    @InjectView(R.id.type1_right)
    ImageView type1Right;
    @InjectView(R.id.type1_boom)
    ImageView type1Boom;
    @InjectView(R.id.select1)
    RelativeLayout select1;
    @InjectView(R.id.type2)
    TextView type2;
    @InjectView(R.id.type2_right)
    ImageView type2Right;
    @InjectView(R.id.type2_boom)
    ImageView type2Boom;
    @InjectView(R.id.select2)
    RelativeLayout select2;
    @InjectView(R.id.type3)
    TextView type3;
    @InjectView(R.id.type3_right)
    ImageView type3Right;
    @InjectView(R.id.type3_boom)
    ImageView type3Boom;
    @InjectView(R.id.select3)
    RelativeLayout select3;
    @InjectView(R.id.nowifi_ly)
    LinearLayout nowifiLy;
    @InjectView(R.id.recommenttitle)
    TextView recommenttitle;
    @InjectView(R.id.editview)
    EditText editview;

    List<ListCategoryBean> catetorystemp = new ArrayList<>();
    List<FilterBean> catetorys = new ArrayList<>();
    List<FilterBean> distance = new ArrayList<>();
    List<FilterBean> priority = new ArrayList<>();

    String[] dis = {"None", "500miles", "1000miles", "2000miles", "5000miles"};
    String[] pri = {"None", "Price", "Review", "Distance", "Popular"};
    int sw;
    LinearLayout list_lay;
    boolean hasMeasured = false;
    int listheigh;

    private String category_02_id = "0";//0表示全部，其他按分类--分类ID
    private String kw = "";//教练或者课程名
    private String category_02_name = "";//第二类名称
    private String distances = "";//距离
    private String prioritys = "";//price:按价格由低到高排列review:按评价由高到低排序distance:按距离由近到远排序hot:按热度由热到冷排序
    String datatype = "1";//1-直接从首页推荐分类进入，2-下拉选择category/二级分类进入

    String price_type = "0";  //价格排序：0-不需要， 1-高,2-低切换
    String review_type = "0";
    String distance_type = "0";
    String hot_type = "0";

    private String distancesspace = "  ";//距离
    String zipcodestr;
    //价格筛选
    String distance_minStr, distance_maxStr, price_minStr, price_maxStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist);
        ButterKnife.inject(this);

        datatype = (String) getValue4Intent("datatype");
        kw = (String) getValue4Intent("key");
        zipcodestr = (String) getValue4Intent("zipcodestr");

        if (datatype.equals("1")) {  //推荐课程 没有搜索
            recommenttitle.setVisibility(View.VISIBLE);
            editview.setVisibility(View.GONE);
        } else {
            recommenttitle.setVisibility(View.GONE);
            editview.setVisibility(View.VISIBLE);
            category_02_id = (String) getValue4Intent("category_02_id");
        }

        initchooseData();
        sw = new AppParam().getScreenWidth(this);
        list_lay = (LinearLayout) findViewById(R.id.list_lay);
        ViewTreeObserver vto = list_lay.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (hasMeasured == false) {
                    listheigh = list_lay.getMeasuredHeight();
                    hasMeasured = true;
                }
                return true;
            }
        });

        myinitlist();
        categoryListRequest();
        CourseListRequest();
    }

    public void initchooseData() {
        for (int i = 0; i < 51; i++) {
            if (i == 0) {
                FilterBean fb = new FilterBean("None");
                distance.add(fb);
            } else {
                FilterBean fb = new FilterBean(i + distancesspace + "miles");
                distance.add(fb);
            }
        }
        for (int i = 0; i < pri.length; i++) {
            FilterBean fb = new FilterBean(pri[i]);
            priority.add(fb);
        }
    }

    @OnClick({R.id.select1, R.id.select2, R.id.select3, R.id.editview})
    public void select(View v) {
        switch (v.getId()) {
            case R.id.select1:
                fouro(v, 1);
                break;
            case R.id.select2:
                filter(v, 2);
                break;
            case R.id.select3:
                fouro(v, 3);
                break;
            case R.id.editview:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivityForResult(intent, 2001);
                break;
        }
    }

    int position1, position2, position3;

    public void fouro(View v, final int select) {
        int showpositon = 0;
        imagedeal(select, "show");
        LayoutInflater inflater = LayoutInflater.from(this);
        final View popview = inflater.inflate(R.layout.choose_per, null);
        final PopupWindow popupWindow;

        final TaskTypesAdapter adapter;
        int lcount;
        if (select == 1) {
            showpositon = position1;
            adapter = new TaskTypesAdapter(this, catetorys);
            lcount = catetorys.size();
        } else if (select == 2) {
            showpositon = position2;
            adapter = new TaskTypesAdapter(this, distance);
            lcount = distance.size();
            if (distance.size() > 6) {
                lcount = 6;
            }
        } else {
            showpositon = position3;
            adapter = new TaskTypesAdapter(this, priority);
            lcount = priority.size();
        }

        LinearLayout per = (LinearLayout) popview.findViewById(R.id.per);
        per.getBackground().setAlpha(120);
//        LinearLayout showview = (LinearLayout) popview.findViewById(R.id.showview);
//        showview.setLayoutParams(new LinearLayout.LayoutParams(sw, (lcount) * (Utils.dip2px(this, 40))));
        popupWindow = new PopupWindow(popview, sw, listheigh + 3);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        final ListView listView = (ListView) popview.findViewById(R.id.list);

        listView.setAdapter(adapter);
        adapter.setChoose(showpositon);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imagedeal(select, "hine");
                if (select == 1) {
                    position1 = position;
                    if (position == 0) {
                        category_02_name = "";
                        category_02_id = "0";

                        type1.setText("CATEGORY");
                        type1.setTextColor(ContextCompat.getColor(CourseListActivity.this, R.color.zitihuise));
                        type1Right.setImageDrawable(ContextCompat.getDrawable(CourseListActivity.this, R.drawable.up_carset));
                    } else {
                        category_02_id = catetorystemp.get(position).getCategory_02_id();
                        category_02_name = catetorys.get(position).getName();

                        type1.setText(category_02_name);
                        type1.setTextColor(ContextCompat.getColor(CourseListActivity.this, R.color.yellow));
                        type1Right.setImageDrawable(ContextCompat.getDrawable(CourseListActivity.this, R.drawable.carset_yellow_down));
                    }
                } else if (select == 2) {
                    position2 = position;
                    if (position == 0) {
                        distances = "";

                        type2.setText("DISTANCE");
                        type2.setTextColor(ContextCompat.getColor(CourseListActivity.this, R.color.zitihuise));
                        type2Right.setImageDrawable(ContextCompat.getDrawable(CourseListActivity.this, R.drawable.up_carset));
                    } else {
                        distances = distance.get(position).getName();

                        type2.setText(distances);
                        type2.setTextColor(ContextCompat.getColor(CourseListActivity.this, R.color.yellow));
                        type2Right.setImageDrawable(ContextCompat.getDrawable(CourseListActivity.this, R.drawable.carset_yellow_down));
                    }
                } else {
                    position3 = position;
                    if (position == 0) {  //点击一次为升序 再点击一次为降序
                        prioritys = "";

                        type3.setText("PRIORITY");
                        type3.setTextColor(ContextCompat.getColor(CourseListActivity.this, R.color.zitihuise));
                        type3Right.setImageDrawable(ContextCompat.getDrawable(CourseListActivity.this, R.drawable.up_carset));
                        setPriorityFilter(position);
                    } else {
                        prioritys = priority.get(position).getName();

                        type3.setText(prioritys);
                        type3.setTextColor(ContextCompat.getColor(CourseListActivity.this, R.color.yellow));
                        type3Right.setImageDrawable(ContextCompat.getDrawable(CourseListActivity.this, R.drawable.carset_yellow_down));
                        setPriorityFilter(position);
                    }
                }
                datatype = "2";
                onRefresh();
                popupWindow.dismiss();
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                imagedeal(select, "hine");
            }
        });

        per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(v);
    }

    //可输入价格和距离筛选
    public void filter(View v, final int select) {
        imagedeal(select, "show");
        LayoutInflater inflater = LayoutInflater.from(this);
        final View popview = inflater.inflate(R.layout.choose_filter, null);
        final PopupWindow popupWindow;

        LinearLayout per = (LinearLayout) popview.findViewById(R.id.per);
        per.getBackground().setAlpha(120);
        popupWindow = new PopupWindow(popview, sw, Utils.dip2px(this, 110));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        final EditText distance_min = (EditText) popview.findViewById(R.id.distance_min);
        final EditText distance_max = (EditText) popview.findViewById(R.id.distance_max);
        TextView distanceOk = (TextView) popview.findViewById(R.id.distanceOk);
        if (!Utils.isEmpty(distance_minStr)) distance_min.setText(distance_minStr);
        if (!Utils.isEmpty(distance_maxStr)) distance_max.setText(distance_maxStr);

        final EditText price_min = (EditText) popview.findViewById(R.id.price_min);
        final EditText price_max = (EditText) popview.findViewById(R.id.price_max);
        TextView priceOk = (TextView) popview.findViewById(R.id.priceOk);
        if (!Utils.isEmpty(price_minStr)) price_min.setText(price_minStr);
        if (!Utils.isEmpty(price_maxStr)) price_max.setText(price_maxStr);

        distanceOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distance_minStr = distance_min.getText().toString();
                distance_maxStr = distance_max.getText().toString();
//                if ("0".equals(distance_maxStr)) {
//                    showToast("please input max distance");
//                } else {
                if (Utils.isEmpty(distance_minStr)) distance_minStr = "0";
                onRefresh();
                popupWindow.dismiss();
//                }
            }
        });
        priceOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price_minStr = price_min.getText().toString();
                price_maxStr = price_max.getText().toString();
//                if ("0".equals(price_maxStr)) {
//                    showToast("please input max price");
//                } else {
                if (Utils.isEmpty(price_minStr)) price_minStr = "0";
                onRefresh();
                popupWindow.dismiss();
//                }
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                imagedeal(select, "hine");
            }
        });

        per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(v);
    }

    public void setPriorityFilter(int position) {
        if ((!Utils.isEmpty(priority.get(position).getType())) && priority.get(position).getType().equals("1")) {//原本是升序的
//清除其他的状态
            for (int i = 0; i < priority.size(); i++) {
//                if (i!=position){
                priority.get(i).setType("0");
//                }
            }
            priority.get(position).setType("2");//变为降序

            if (position == 1) {
                price_type = "2";
                review_type = "0";
                distance_type = "0";
                hot_type = "0";
            } else if (position == 2) {
                review_type = "2";
                price_type = "0";
                distance_type = "0";
                hot_type = "0";
            } else if (position == 3) {
                distance_type = "2";
                price_type = "0";
                review_type = "0";
                hot_type = "0";
            } else if (position == 4) {
                hot_type = "2";
                price_type = "0";
                review_type = "0";
                distance_type = "0";
            }
        } else {
            for (int i = 0; i < priority.size(); i++) {
                priority.get(i).setType("0");
            }
            priority.get(position).setType("1");//变为降序

//            if (position==1){
//                price_type = "1";
//            }else if(position==2){
//                review_type = "1";
//            }else if(position==3){
//                distance_type = "1";
//            }else if(position==4){
//                hot_type = "1";
//            }
            if (position == 1) {
                price_type = "1";
                review_type = "0";
                distance_type = "0";
                hot_type = "0";
            } else if (position == 2) {
                review_type = "1";
                price_type = "0";
                distance_type = "0";
                hot_type = "0";
            } else if (position == 3) {
                distance_type = "1";
                price_type = "0";
                review_type = "0";
                hot_type = "0";
            } else if (position == 4) {
                hot_type = "1";
                price_type = "0";
                review_type = "0";
                distance_type = "0";
            }

        }
    }

    public void imagedeal(int select, String type) {
        type1Boom.setVisibility(View.GONE);
        if (!Utils.isEmpty(category_02_name)) {   //选有条件黄色显示
            type1Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_yellow_down));
        } else {
            type1Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_2));
        }

        type2Boom.setVisibility(View.GONE);
        if (!Utils.isEmpty(distances)) {
            type2Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_yellow_down));
        } else {
            type2Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_2));
        }

        type3Boom.setVisibility(View.GONE);
        if (!Utils.isEmpty(prioritys)) {
            type3Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_yellow_down));
        } else {
            type3Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_2));
        }

        if (type.equals("show")) {
            if (select == 1) {
                type1Boom.setVisibility(View.VISIBLE);
                if (!Utils.isEmpty(category_02_name)) {
                    type1Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_yellow_up));
                } else {
                    type1Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_3));
                }
            } else if (select == 2) {
                type2Boom.setVisibility(View.VISIBLE);
                if (!Utils.isEmpty(distances)) {
                    type2Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_yellow_up));
                } else {
                    type2Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_3));
                }
            } else {
                type3Boom.setVisibility(View.VISIBLE);
                if (!Utils.isEmpty(prioritys)) {
                    type3Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_yellow_up));
                } else {

                    type3Right.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.carset_3));
                }
            }
        }
    }

    public void categoryListRequest() {
        CourseCategoryListRequest rq = new CourseCategoryListRequest();

        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlercategory);
    }

    private final AsyncHttpResponseHandler mHandlercategory = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, CourseListActivity.this, CatetoryListResponse.class);
            if (kd != null) {
                CatetoryListResponse info = (CatetoryListResponse) kd;
                catetorystemp.addAll(info.getCategoryListResult().getCategorys());
                if (catetorystemp != null) {
                    for (int i = 0; i < catetorystemp.size(); i++) {
                        FilterBean fb = new FilterBean(catetorystemp.get(i).getCategory_02_name());
                        catetorys.add(fb);
                    }
                }
                ListCategoryBean tmp = new ListCategoryBean();
                tmp.setCategory_02_name("None");
                catetorystemp.add(0, tmp);
                FilterBean fb = new FilterBean("None");
                catetorys.add(0, fb);
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }

        @Override
        public void onFinish() {
            super.onFinish();
            showWait(false);
        }
    };

    public void myinitlist() {
        datas = new ArrayList<>();
        adapter = new CourseListAdapter(this, datas, handler);
        xlstv.setOnRefreshListener(this);
        xlstv.setOnLoadListener(this);
        xlstv.setAdapter(adapter);
        xlstv.setFocusable(false);
        xlstv.setOnItemClickListener(listListener);
    }

    AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position = position - 1;
            if (position < datas.size()) {
                Intent in = new Intent(CourseListActivity.this, CourseDetailActivity.class);
                in.putExtra("course_id", datas.get(position).getCourse_id() + "");
                startActivity(in);
            }
        }
    };

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @OnClick(R.id.main_left)
    public void onClick() {
        finish();
    }

    public void CourseListRequest() {
        CourseListRequest rq = new CourseListRequest();
        rq.setPn(pn);
        rq.setPage_size(10);
        rq.setCategory_02_id(category_02_id);
        rq.setType(datatype);
        rq.setLatitude(new AppParam().getLat(this));
        rq.setLongitude(new AppParam().getLng(this));
        rq.setCategory_02_name(category_02_name);
        if (!Utils.isEmpty(distances)) {
            String[] dist = distances.split(distancesspace);
            rq.setDistances(dist[0]);
        } else {
            rq.setDistances("");
        }
        rq.setKw(kw);
        if (!Utils.isEmpty(zipcodestr)) {
            rq.setZipcode(zipcodestr);
        }

        rq.setPrice_type(price_type);
        rq.setReview_type(review_type);
        rq.setDistance_type(distance_type);
        rq.setHot_type(hot_type);
        if (Utils.isEmpty(distance_maxStr)&&(!"0".equals(distance_maxStr))){
            rq.setDistances(distance_maxStr);
        }
        rq.setDistances_begin(distance_minStr);
        if (Utils.isEmpty(price_maxStr)&&(!"0".equals(price_maxStr))){
            rq.setSession_rate(price_maxStr);
        }

        rq.setSession_rate_begin(distance_minStr);

        showWait(true);
        QuarkApi.HttpRequestNoTS(rq, mHandler);
    }

    private final AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, CourseListActivity.this, CourseListResponse.class);
            if (kd != null) {
                CourseListResponse info = (CourseListResponse) kd;
                dealdatas(info);
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }

        @Override
        public void onFinish() {
            super.onFinish();
            showWait(false);
        }
    };

    public void dealdatas(CourseListResponse info) {
        if (type == 1) {
            datas.clear();
        }
        List<ListCourse> data = info.getCourseListResult().getCourses().getList();
        Message msg = handler.obtainMessage();
        msg.what = type;
        if (data != null && data.size() > 0) {
            datas.addAll(data);
            msg.arg1 = data.size();
        } else {
            msg.arg1 = 0;
        }
        handler.sendMessage(msg);
    }

    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            int rs = msg.arg1;
            switch (msg.what) {
                case 1:
                    xlstv.onRefreshComplete();
                    break;
                case 2:
                    xlstv.onLoadComplete();
                    break;
            }
            xlstv.setResultSize(rs);
            adapter.notifyDataSetChanged();
        }

        ;
    };

    public int pn = 1;
    public int type = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 0) {
            if (requestCode == 2001) {
                kw = data.getStringExtra("key");
                zipcodestr = (String) getValue4Intent("zipcodestr");
                editview.setText(kw);
                onRefresh();
            }
        }
    }

    @Override
    public void onRefresh() {
        pn = 1;
        type = 1;
        CourseListRequest();
    }

    @Override
    public void onLoad() {
        type = 2;
        pn++;
        CourseListRequest();
    }
}
