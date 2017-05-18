package com.quark.skillopedia.uiview.fenlei;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.quark.api.auto.bean.BookingRequest;
import com.quark.api.auto.bean.BookingResponse;
import com.quark.api.auto.bean.BookingScheduleListRequest;
import com.quark.api.auto.bean.BookingScheduleListResponse;
import com.quark.api.auto.bean.ListScheduleBean;
import com.quark.skillopedia.AppContext;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.ScheduleAdapter;
import com.quark.skillopedia.api.ApiResponse;
import com.quark.skillopedia.api.remote.QuarkApi;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.ui.widget.MyGridView;
import com.quark.skillopedia.ui.widget.WheelChooseBorthdayDialog;
import com.quark.skillopedia.util.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * 预定时间
 *
 * @author leon
 * @time 2016/7/2 0002 下午 3:55
 */
public class ScheduleBookingActivity extends BaseActivity {
    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.gridview)
    MyGridView gridview;
    @InjectView(R.id.number)
    TextView number;
    @InjectView(R.id.tip)
    TextView tip;
    @InjectView(R.id.ffreeday)
    TextView ffreeday;
    @InjectView(R.id.booking)
    TextView booking;

    ScheduleAdapter adapter;
    String timestr;
    List<ListScheduleBean> datas;
    String coure_id;
    String orders_id = "";
    int countNumber;
    int totalCountNumber;
    String coureTitle;
    String choice_currentdates = "";
    String under_select_course_num = "";   //直接传接口获取到的时候或详情页传过来的
    HashMap<String, String> choosetime;  //key 日期 + "*" + 位置 ;  value： 日期 + "A" + 位置 + "A" + 位置+1
    HashMap<String, List<ListScheduleBean>> datacash;
    String from;  //订单进入order 课程详情进入, detail 详情进入不需要调预约时间接口
    String schedule_datas_init;
    String zTimeStr;
    String freeCourseDay; //课程时间表打开时应该显示第一个可以预定的日期

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_schedule);
        ButterKnife.inject(this);
        setTopTitle("Appointment time");
        setBackButton();
        choosetime = new HashMap<>();
        datacash = new HashMap<>();
        datas = new ArrayList<>();

        from = (String) getValue4Intent("from");
        coure_id = (String) getValue4Intent("coure_id");
        coureTitle = (String) getValue4Intent("coureTitle");
        schedule_datas_init = (String) getValue4Intent("schedule_datas");  //2015-06-11A1A2#2015-06-12A21A22#2015-06-14A23A24
        booking.setText("Schedule");

        if (!Utils.isEmpty(schedule_datas_init)) {
            String[] initschedule = schedule_datas_init.split("#");//
            for (int i = 0; i < initschedule.length; i++) {
                String[] initschedule_item = initschedule[i].split("A");
                if (initschedule_item.length>1){
                    int pposition = Integer.valueOf(initschedule_item[1]);
                    choosetime.put(initschedule_item[0] + "" + (pposition-1), initschedule[i]);  //加入到已选中
                }
            }
        }

        String countNumberinit = (String) getValue4Intent("countNumber");
        countNumber = Integer.valueOf(countNumberinit);
        totalCountNumber = countNumber;
        number.setText(countNumber + "");

        under_select_course_num = countNumberinit;
        if (from.equals("detail")) {
//            String countNumberStr = (String) getValue4Intent("buy_amount");
//            under_select_course_num = countNumberStr;
        } else if (from.equals("order")) {
            orders_id = (String) getValue4Intent("orders_id");
        }
        tip.setText("you are booking the time of " + coureTitle + " course,after your book,coach will call you to confirm your time.Click on second time could cancel your selection.");

        gridview.setOnItemClickListener(gradonclick);
//课程时间表打开时应该显示第一个可以预定的日期
        freeCourseDay = (String) getValue4Intent("freeCourseDay");
        if (!Utils.isEmpty(freeCourseDay)) {
            String etime = Utils.getEnglishTime(freeCourseDay);
            ffreeday.setText("The first available date is " + etime);
            timestr = freeCourseDay;
            time.setText(etime);
        } else {
            Date date = new Date();
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            timestr = d.format(date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
            zTimeStr = dateFormat.format(date);
            time.setText(zTimeStr);
        }
        bookingScheduleListRequest();
    }

    AdapterView.OnItemClickListener gradonclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //必须连续选择两个 每节课固定时间为60min 只往后取时间 如果后一个时间不空闲则不能选择 提示“不能选择该时间段”
            //取消时 如果取消 则取消 上次选择的两个时间
            //时间段个数不能大于课程数
            // 取消 HashMap<Integer,String>记录if点击的是已选择的 判断是否包含key 如果包含设置key key+1为未选 remove key；如果不在判断key-1是否在map中在 key-1 key为空 remove key-1
            if (datas.get(position).getSchedule_state().equals("3")) {   //1-unavaliable，2-busy,3-有空
                if (datas.get(position).isCheck()) {  //取消
                    if (choosetime.containsKey(timestr + position)) {
                        datas.get(position).setCheck(false);
                        datas.get(position + 1).setCheck(false);
                        choosetime.remove(timestr + position);
                        countNumber++;
                        number.setText(countNumber + "");
                    } else {
                        if (choosetime.containsKey(timestr + (position - 1))) {
                            datas.get(position).setCheck(false);
                            datas.get(position - 1).setCheck(false);
                            choosetime.remove(timestr + (position - 1));
                            countNumber++;
                            number.setText(countNumber + "");
                        }
                    }
                } else { //选择
                    if (countNumber > 0) {
                        if (((position + 1) < 32) && (!datas.get(position + 1).isCheck()) && (datas.get(position + 1).getSchedule_state().equals("3"))) {
                            datas.get(position).setCheck(true);
                            datas.get(position + 1).setCheck(true);
                            choosetime.put(timestr + position, timestr + "A" + datas.get(position).getHour_index() + "A" + datas.get(position + 1).getHour_index());
                            countNumber--;
                            number.setText(countNumber + "");
                        } else {
                            showToast("Can not choose this time period");
                        }
                    } else {
                        showToast("Your course has been selected");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }
    };

    public void bookingScheduleListRequest() {
        if (datacash.containsKey(timestr)) {
            datas.clear();
            datas.addAll(datacash.get(timestr));
            adapter.notifyDataSetChanged();
        } else {
            BookingScheduleListRequest rq = new BookingScheduleListRequest();
            rq.setChoice_currentdate(timestr);
            rq.setUser_id(new AppParam().getUser_id(this));
            rq.setCourse_id(coure_id);
            rq.setOrders_id(orders_id);
            showWait(true);
            QuarkApi.HttpRequestNoTS(rq, mHandlerdata);
        }
    }

    private final AsyncHttpResponseHandler mHandlerdata = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleBookingActivity.this, BookingScheduleListResponse.class);
            if (kd != null) {
                BookingScheduleListResponse info = (BookingScheduleListResponse) kd;
                if (info.getStatus() == 1) {
                    if (from.equals("order")) { //订单进入 获取未选课程数
                        String unchoose = info.getUnder_select_course_num();
                        under_select_course_num = unchoose;
                        countNumber = Integer.valueOf(unchoose);
                        number.setText(countNumber + "");
                    }
                    datas.clear();
                    datas.addAll(info.getScheduleBeans());

                    if (!Utils.isEmpty(schedule_datas_init)) {
                        String[] initschedule = schedule_datas_init.split("#");//
                        for (int i = 0; i < initschedule.length; i++) {
                            String[] initschedule_item = initschedule[i].split("A");
                            if (initschedule_item[0].equals(timestr)) {
                                int positiontwo = Integer.valueOf(initschedule_item[1]);
                                int positionone = positiontwo - 1;
                                datas.get(positionone).setCheck(true);
                                datas.get(positiontwo).setCheck(true);
                            }
                        }
                    }

                    adapter = new ScheduleAdapter(ScheduleBookingActivity.this, datas);
                    gridview.setAdapter(adapter);

                    List<ListScheduleBean> tempdatas = info.getScheduleBeans();
                    datacash.put(timestr, tempdatas);
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

    @OnClick({R.id.choosetimeview})
    public void ss(View b) {
        switch (b.getId()) {
            case R.id.choosetimeview:
                WheelChooseBorthdayDialog wccd = new WheelChooseBorthdayDialog();
                wccd.showSheetPic(this, handler, 101);
                break;
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    timestr = (String) msg.obj;
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(timestr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // 将date转化为String
                    String s = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(date);
                    time.setText(s);
                    bookingScheduleListRequest();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

        ;
    };

    @OnClick(R.id.booking)
    public void book(View b) {
        if (choosetime.size() == 0) {
            showToast("Please select the time");
        } else {
            Iterator iter = choosetime.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object val = entry.getValue();
                choice_currentdates += val + "#";
            }

            if (choice_currentdates.endsWith("#")) {
                choice_currentdates = choice_currentdates.substring(0, choice_currentdates.length() - 1);
            }
            if (from.equals("detail")) {
                Intent intent = new Intent();
                intent.putExtra("schedule_datas", choice_currentdates);
                intent.putExtra("countNumber", (choosetime.size())+"");

                setResult(102, intent);
                finish();
            } else {
                bookingRequest();
            }
        }
    }

    public void bookingRequest() {
        BookingRequest rq = new BookingRequest();
        rq.setChoice_currentdates(choice_currentdates);
        rq.setCourse_id(coure_id);
        rq.setOrders_id(orders_id);
        rq.setUnder_select_course_num(under_select_course_num);
        showWait(true);
        QuarkApi.HttpRequest(rq, mHandlerbook);
    }

    private final AsyncHttpResponseHandler mHandlerbook = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
            Object kd = ApiResponse.get(arg2, ScheduleBookingActivity.this, BookingResponse.class);
            if (kd != null) {
                BookingResponse info = (BookingResponse) kd;
                showToast(info.getMessage());
                if (info.getStatus() == 1) {
                    Bundle bundle = new Bundle();
                    bundle.putString("countNumber", countNumber + "");
                    bundle.putString("schedule_datas", choice_currentdates);
                    //下单选时间
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(102, intent);
                    //刷新全部订单 已付款
                    Intent allIntent = new Intent("orders_all");
                    allIntent.putExtra("option", "refresh");
                    sendBroadcast(allIntent);
                    //
                    Intent comIntent = new Intent("orders_apaid");
                    comIntent.putExtra("option", "refresh");
                    sendBroadcast(comIntent);

                    finish();
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
    public void initView() {
    }

    @Override
    public void initData() {
    }
}
