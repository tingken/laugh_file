package com.quark.skillopedia.uiview.fenlei;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.joooonho.SelectableRoundedImageView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.AddCollectionCourseRequest;
import com.quark.api.auto.bean.AddCollectionCourseResponse;
import com.quark.api.auto.bean.CardNumRequest;
import com.quark.api.auto.bean.CardNumResponse;
import com.quark.api.auto.bean.Course;
import com.quark.api.auto.bean.CourseCommentListRequest;
import com.quark.api.auto.bean.CourseCommentListResponse;
import com.quark.api.auto.bean.CourseInfo2Request;
import com.quark.api.auto.bean.CourseInfo2Response;
import com.quark.api.auto.bean.CourseVedios;
import com.quark.api.auto.bean.DeleteCollectionCourseRequest;
import com.quark.api.auto.bean.DeleteCollectionCourseResponse;
import com.quark.api.auto.bean.ListComment;
import com.quark.api.auto.bean.ListCourseBanner;
import com.quark.api.auto.bean.ListCourseCertification;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.CourseDetailCommentsEXBaseAdapter;
import com.quark.skillopedia.adapter.ImageAdapter;
import com.quark.skillopedia.adapter.VedioEXBaseAdapter;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseFragementActivity;
import com.quark.skillopedia.ui.widget.AutoListForScrollView;
import com.quark.skillopedia.ui.widget.AutoScrollView.BorderScrollView;
import com.quark.skillopedia.ui.widget.CourseDetailShowView;
import com.quark.skillopedia.ui.widget.ListViewForScrollView;
import com.quark.skillopedia.uiview.NewLoginActivity;
import com.quark.skillopedia.uiview.ShoppingCartActivity;
import com.quark.skillopedia.uiview.dingdan.FillOrderActivity;
import com.quark.skillopedia.uiview.fenlei.fragment.DetailsFrament;
import com.quark.skillopedia.uiview.youtubeVideos.FullscreenDemoActivity;
import com.quark.skillopedia.uiview.youtubeVideos.VideoEntry;
import com.quark.skillopedia.uiview.zhanghu.zhuye.PersonHomeActivity;
import com.quark.skillopedia.util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * @author pan
 *         created at 2016/5/27 0027
 * @课程 教练 详情
 */
public class CourseDetailActivity extends BaseFragementActivity implements BorderScrollView.OnBorderListener, AutoListForScrollView.OnLoadListener {
    CourseInfo2Response course2info;
    int pn = 1;
    String course_id;
    String collectionId;
    Course course;
    int sw;
    VedioEXBaseAdapter adapter;
    ImageAdapter imageAdapter;
    CourseDetailCommentsEXBaseAdapter commentAdapter;
    List<ListCourseBanner> banners;//教练图片
    List<ListCourseCertification> courseCertifications;//证书
    List<ListCourseBanner> bannersTemp;//传递到证书展示用

    @InjectView(R.id.collection_iv)
    ImageView collectionIv;
    @InjectView(R.id.cart_ly)
    LinearLayout cartLy;
    @InjectView(R.id.iamgeIv)
    SelectableRoundedImageView iamgeIv;
    @InjectView(R.id.text1)
    TextView text1;
    @InjectView(R.id.text3)
    TextView text3;
    @InjectView(R.id.gradeTv)
    TextView gradeTv;
    @InjectView(R.id.footballTv)
    TextView footballTv;
    @InjectView(R.id.milesTv)
    TextView milesTv;
    @InjectView(R.id.one)
    TextView one;
    @InjectView(R.id.two)
    TextView two;
    @InjectView(R.id.three)
    TextView three;
    @InjectView(R.id.four)
    TextView four;
    @InjectView(R.id.boomline)
    ImageView boomline;
    @InjectView(R.id.horizontalScrollView)
    HorizontalScrollView horizontalScrollView;
    @InjectView(R.id.textcontent)
    TextView textcontent;
    @InjectView(R.id.videosline)
    View videosline;
    @InjectView(R.id.videos)
    ListViewForScrollView videos;
    @InjectView(R.id.commentCount)
    TextView commentCount;
    @InjectView(R.id.listComments)
    AutoListForScrollView listComments;
    @InjectView(R.id.experience)
    TextView experience;
    @InjectView(R.id.groupSession)
    TextView groupSession;
    @InjectView(R.id.travel)
    TextView travel;
    @InjectView(R.id.duration)
    TextView duration;
    @InjectView(R.id.ages)
    TextView ages;
    @InjectView(R.id.left_img)
    ImageView leftImg;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.collection_ly)
    LinearLayout collectionLy;
    @InjectView(R.id.shopping_iv)
    ImageView shoppingIv;
    @InjectView(R.id.head)
    LinearLayout head;
    @InjectView(R.id.cpic)
    LinearLayout cpic;
    @InjectView(R.id.scrol)
    BorderScrollView scrol;
    @InjectView(R.id.schedule_ly)
    LinearLayout scheduleLy;
    @InjectView(R.id.paynow_tv)
    LinearLayout paynowTv;
    @InjectView(R.id.coordinatorlayout)
    LinearLayout coordinatorlayout;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.two_recyclerview)
    RecyclerView twoRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursedetail);
        ButterKnife.inject(this);
        course_id = (String) getValue4Intent("course_id");
        sw = new AppParam().getScreenWidth(this);
        setBackButton();

        BorderScrollView scrol = (BorderScrollView) findViewById(R.id.scrol);
        scrol.setOnBorderListener(this);

        courseInfo2Request();
        if (new AppParam().isLogin(this)) {
            cardNumRequest();
        }
        initComments();
    }

    @OnClick({R.id.cart_ly, R.id.paynow_tv, R.id.schedule_ly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cart_ly:
                if (new AppParam().isLogin(this)) {
                    startActivityByClass(ShoppingCartActivity.class);
                } else {
                    startActivity(new Intent().setClass(this, NewLoginActivity.class));
                }
                break;
            case R.id.paynow_tv:
                if (course != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("coure_id", course.getCourse_id() + "");
                    startActivityByClass(FillOrderActivity.class, bundle);
                }
                break;
            case R.id.schedule_ly:
                if (course != null) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("coure_id", course.getCourse_id() + "");
                    startActivityByClass(ScheduleShowActivity.class, bundle2);
                }
                break;
        }
    }

    @OnClick(R.id.collection_ly)
    public void onClick() {
        if (new AppParam().isLogin(this)) {
            if (course2info != null) {
                if (course2info.getCourseInfo2Result().getCourse().getIs_collection().equals("0")) {
                    collectionIv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.saved));
                    AddCollectionCourseRequest();
                } else {
                    collectionIv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.save));
                    DeleteCollectionCourseRequest();
                }
            }
        } else {
            startActivity(new Intent().setClass(this, NewLoginActivity.class));
        }
    }

    /**
     * 课程详情
     */
    public void courseInfo2Request() {
        CourseInfo2Request rq = new CourseInfo2Request();
        rq.setCourse_id(course_id);
        rq.setLatitude(new AppParam().getLat(this));
        rq.setLongitude(new AppParam().getLng(this));
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdata);
    }

    private final AsyncHttpResponseHandler mHandlerdata = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, CourseDetailActivity.this, CourseInfo2Response.class);
            if (kd != null) {
                course2info = (CourseInfo2Response) kd;
                dealdatas();
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    /**
     * 获取数据
     */
    private void dealdatas() {
        course = course2info.getCourseInfo2Result().getCourse();
        showCPic();
        showZSPic();
        iamgeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailActivity.this, PersonHomeActivity.class);
                startActivity(intent);
            }
        });

        ApiHttpClient.loadImage(course.getUser_image_01(), iamgeIv, R.drawable.example_7);
        text1.setText(course.getTitle());
        text3.setText(course.getNickname());
        gradeTv.setText(course.getTotal_coment_num() + " review");
        milesTv.setText(course.getDistance());
        footballTv.setText(course.getCategory_02_name());
        experience.setText(course.getTeaching_since() + "year");
        groupSession.setText(course.getSession_rate());
        if (course.getTravel_to_session() == 1) {
            travel.setText("yes");
        } else {
            travel.setText("no");
        }
        duration.setText(course.getSession_length() + "mins");
        if ("No preference".equals(course.getTeaching_age())) {
            ages.setText("No preference");
        } else {
            ages.setText("at least " + course.getTeaching_age() + " years old");
        }

        collectionId = course.getCollection_id();
        new AppParam().setSharedPreferencesy(CourseDetailActivity.this, "collection_id", course.getCollection_id());
        initpage();

        if (course.getIs_collection().equals("0")) {
            collectionIv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.save));
        } else {
            collectionIv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.saved));
        }
        initVideos();
        setLine(0);
    }

    //教练图片
    public void showCPic() {
        //原轮播图片
        banners = course.getCourseBanners();
        //160/140
        int tabswtep = (sw - Utils.dip2px(this, 70)) / 3;
        int tabsw = (int) (160.0 / 140.0) * tabswtep;
        ViewGroup.LayoutParams params = recyclerview.getLayoutParams();
        params.height = tabsw;
        recyclerview.setLayoutParams(params);

        if (banners != null) {
            //设置布局管理器
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CourseDetailActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerview.setLayoutManager(linearLayoutManager);
            imageAdapter = new ImageAdapter(CourseDetailActivity.this, banners);
            recyclerview.setAdapter(imageAdapter);
            imageAdapter.setOnItemClickLitener(new ImageAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("imgs", (Serializable) banners);
                    bundle.putInt("current", position);
                    startActivityByClass(ShowImageActivity.class, bundle);
                }

                @Override
                public void onItemLongClick(View view, int position) {
                }
            });
        }
    }

    //证书图片
    public void showZSPic() {
        //荣耀 证书
        courseCertifications = course.getCourseCertifications();
        bannersTemp = new ArrayList<>();// 传到查看大图 使用ListCourseBanner实体
        if (courseCertifications != null) {
            for (int i = 0, size = courseCertifications.size(); i < size; i++) {
                ListCourseBanner te = new ListCourseBanner();
                te.setImage_01(courseCertifications.get(i).getImage_01());
                bannersTemp.add(te);
            }
        }

        //160/140
        int tabswtep = (sw - Utils.dip2px(this, 70)) / 3;
        int tabsw = (int) (160.0 / 140.0) * tabswtep;
        ViewGroup.LayoutParams params = twoRecyclerview.getLayoutParams();
        params.height = tabsw;
        twoRecyclerview.setLayoutParams(params);
        if (bannersTemp != null) {
            //设置布局管理器
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CourseDetailActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            twoRecyclerview.setLayoutManager(linearLayoutManager);
            imageAdapter = new ImageAdapter(CourseDetailActivity.this, bannersTemp);
            twoRecyclerview.setAdapter(imageAdapter);
            imageAdapter.setOnItemClickLitener(new ImageAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("imgs", (Serializable) bannersTemp);
                    bundle.putInt("current", position);
                    startActivityByClass(ShowImageActivity.class, bundle);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });
        }
    }

    public void initVideos() {
        List<CourseVedios> courseVedios = course.getCourseVedios();
        if (courseVedios != null && courseVedios.size() > 0) {
            final List<VideoEntry> VIDEO_LIST;
            List<VideoEntry> list = new ArrayList<VideoEntry>();
            for (int i = 0; i < courseVedios.size(); i++) {
                String url = courseVedios.get(i).getVedio_url();
                String[] videoidstr = url.split("watch\\?v=");  //youtube 固定格式
                if (videoidstr.length > 1) {
                    list.add(new VideoEntry("", videoidstr[videoidstr.length - 1]));
                }
            }
            VIDEO_LIST = Collections.unmodifiableList(list);
            adapter = new VedioEXBaseAdapter(this, VIDEO_LIST);
            videos.setAdapter(adapter);
            videos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent().setClass(CourseDetailActivity.this, FullscreenDemoActivity.class).putExtra("videoid", VIDEO_LIST.get(position).getVideoId()));
                }
            });
        } else {
            videosline.setVisibility(View.GONE);
        }
    }

    ArrayList<ListComment> datas;

    public void initComments() {
        listComments.setOnLoadListener(this);
        datas = new ArrayList<>();
        commentAdapter = new CourseDetailCommentsEXBaseAdapter(CourseDetailActivity.this, datas);
        listComments.setAdapter(commentAdapter);
        courseCommentListRequest();
    }

    /**
     * 课程详情评论列表
     */
    public void courseCommentListRequest() {
        CourseCommentListRequest rq = new CourseCommentListRequest();
        rq.setCourse_id(course_id);
        rq.setPn(pn);
        rq.setPage_size(10);
        showWait(true);
        QuarkApi.HttpRequestNoTS(rq, mHandlercom);
    }

    private final AsyncHttpResponseHandler mHandlercom = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, CourseDetailActivity.this, CourseCommentListResponse.class);
            if (kd != null) {
                CourseCommentListResponse info = (CourseCommentListResponse) kd;
                List<ListComment> data = info.getCourseCommentListResult().getComments().getList();
                Message msg = new Message();
                msg.arg1 = 0;
                if (data.size() > 0) {
                    if (data.size() == 10) {
                        loadEnable = true;
                    }
                    datas.addAll(data);
                    commentCount.setText("Reviews(" + info.getCourseCommentListResult().getComments().getTotalRow() + ")");
                    msg.arg1 = data.size();
                }
                msg.what = 101;
                commentHandler.sendMessage(msg);
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    private Handler commentHandler = new Handler() {
        public void handleMessage(Message msg) {
            int rs = msg.arg1;
            switch (msg.what) {
                case 101:
                    commentAdapter.notifyDataSetChanged();
                    listComments.onLoadComplete();
                    listComments.setResultSize(rs);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };

    /**
     * 添加收藏
     */
    public void AddCollectionCourseRequest() {
        AddCollectionCourseRequest rq = new AddCollectionCourseRequest();
        rq.setCourse_id(course_id);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandleradd);
    }

    private final AsyncHttpResponseHandler mHandleradd = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, CourseDetailActivity.this, AddCollectionCourseResponse.class);
            if (kd != null) {
                AddCollectionCourseResponse info = (AddCollectionCourseResponse) kd;
                course2info.getCourseInfo2Result().getCourse().setIs_collection("1");
                collectionId = info.getCollection_id();
                showToast(info.getMessage());
            }
            showWait(false);
        }

        @Override
        public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
            AppContext.showToast("Network error" + arg0);
            showWait(false);
        }
    };

    /**
     * 删除收藏
     */
    public void DeleteCollectionCourseRequest() {
        DeleteCollectionCourseRequest rq = new DeleteCollectionCourseRequest();
        rq.setCollection_id(collectionId);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerdel);
    }

    private final AsyncHttpResponseHandler mHandlerdel = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, CourseDetailActivity.this, DeleteCollectionCourseResponse.class);
            if (kd != null) {
                DeleteCollectionCourseResponse info = (DeleteCollectionCourseResponse) kd;
                course2info.getCourseInfo2Result().getCourse().setIs_collection("0");
                showToast(info.getMessage());
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
    protected void onDestroy() {
        super.onDestroy();
        if (CourseDetailShowView.detailBroad != null) {
            try {
                unregisterReceiver(CourseDetailShowView.detailBroad);
            } catch (Exception e) {
                Log.e("eroor", "can not distroy CourseDetailShowView.detailBroad");
            }
        }
    }

    public void cardNumRequest() {
        CardNumRequest rq = new CardNumRequest();
        QuarkApi.HttpRequest(rq, mHandlercarnumber);
    }

    private final AsyncHttpResponseHandler mHandlercarnumber = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, CourseDetailActivity.this, CardNumResponse.class);
            if (kd != null) {
                CardNumResponse info = (CardNumResponse) kd;
                //购物车小红点
                BadgeView bv = new BadgeView(CourseDetailActivity.this);
                bv.setTargetView(cartLy);
                bv.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
                bv.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC));
                bv.setTextSize(8);
                bv.setShadowLayer(2, -1, -1, Color.WHITE);
                try {
                    int coutn = Integer.valueOf(info.getTotal_card_number());
                    bv.setBadgeCount(coutn);
                } catch (Exception e) {
                    Log.e("eroor", "int 转换error");
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

    public void initpage() {
        int tabsw = (sw - Utils.dip2px(this, 40)) / 4;
        ViewGroup.LayoutParams paramsbg = boomline.getLayoutParams();
        paramsbg.width = tabsw;
        boomline.setLayoutParams(paramsbg);

        ViewGroup.LayoutParams params = one.getLayoutParams();
        params.width = tabsw;
        one.setLayoutParams(params);
        ViewGroup.LayoutParams params1 = two.getLayoutParams();
        params1.width = tabsw;
        two.setLayoutParams(params1);
        ViewGroup.LayoutParams params2 = three.getLayoutParams();
        params2.width = tabsw;
        three.setLayoutParams(params2);
        ViewGroup.LayoutParams params3 = four.getLayoutParams();
        params3.width = tabsw;
        four.setLayoutParams(params3);
    }

    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four})
    public void tabonClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                setLine(0);
                break;
            case R.id.two:
                setLine(1);
                break;
            case R.id.three:
                setLine(2);
                break;
            case R.id.four:
                setLine(3);
                break;
        }
    }

    public void setLine(int position) {
        if (course!=null){
            cleantab(position);
            movePositionX(position);
            if (position == 0) {
                textcontent.setVisibility(View.VISIBLE);
                cpic.setVisibility(View.GONE);
                if (Utils.isEmpty(course.getOverview())) {
                    textcontent.setText(getString(R.string.no_data));
                } else {
                    textcontent.setText(course.getOverview());
                }
            } else if (position == 1) {
                textcontent.setVisibility(View.VISIBLE);
                cpic.setVisibility(View.GONE);
                if (Utils.isEmpty(course.getAchievements())) {
                    textcontent.setText(getString(R.string.no_data));
                } else {
                    textcontent.setText(course.getAchievements());
                }

            } else if (position == 2) {
                textcontent.setVisibility(View.VISIBLE);
                cpic.setVisibility(View.GONE);
                if (Utils.isEmpty(course.getSpecialist())) {
                    textcontent.setText(getString(R.string.no_data));
                } else {
                    textcontent.setText(course.getSpecialist());
                }
            } else if (position == 3) {
                if (courseCertifications != null && courseCertifications.size() > 0) {
                    textcontent.setVisibility(View.GONE);
                    cpic.setVisibility(View.VISIBLE);
                } else {
                    textcontent.setVisibility(View.VISIBLE);
                    cpic.setVisibility(View.GONE);
                    textcontent.setText(getString(R.string.no_data));
                }
            }
        }
    }

    private void movePositionX(int toPosition) {
        int moveOne = (sw - Utils.dip2px(this, 40)) / 4;
        float curTranslationX = boomline.getTranslationX();
        float toPositionX = moveOne * (toPosition);
        ObjectAnimator animator = ObjectAnimator.ofFloat(boomline, "translationX", curTranslationX, toPositionX);
        animator.setDuration(500);
        animator.start();
        int hasScroll = horizontalScrollView.getScrollX();
        float toPositionXscroll = moveOne * (toPosition - 1);
        horizontalScrollView.smoothScrollTo((int) toPositionXscroll, 0);
    }

    public void cleantab(int current) {
        one.setTextColor(ContextCompat.getColor(this, R.color.qianhuise));
        two.setTextColor(ContextCompat.getColor(this, R.color.qianhuise));
        three.setTextColor(ContextCompat.getColor(this, R.color.qianhuise));
        four.setTextColor(ContextCompat.getColor(this, R.color.qianhuise));
        if (current == 0) {
            one.setTextColor(ContextCompat.getColor(this, R.color.yellow));
        } else if (current == 1) {
            two.setTextColor(ContextCompat.getColor(this, R.color.yellow));
        } else if (current == 2) {
            three.setTextColor(ContextCompat.getColor(this, R.color.yellow));
        } else if (current == 3) {
            four.setTextColor(ContextCompat.getColor(this, R.color.yellow));
        }
    }

    @OnClick(R.id.milesTv)
    public void tomapshow(View b) {
        Bundle bundle = new Bundle();
        bundle.putString("lngStr", course.getLongitude());
        bundle.putString("latStr", course.getLatitude());
        startActivityByClass(DetailsFrament.class, bundle);
    }

    boolean loadEnable = false;

    @Override
    public void onBottom() {
        if (loadEnable) {
            loadEnable = false;
            onLoad();
        }
    }

    @Override
    public void onTop() {
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @Override
    public void onLoad() {
        pn++;
        courseCommentListRequest();
    }
}
