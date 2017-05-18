package com.quark.skillopedia.uiview.orders;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quark.skillopedia.R;
import com.quark.skillopedia.base.BaseFragementActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
/**
 * 订单管理界面
 * @author pan
 * @time 2016/8/22 0022 下午 5:39
 */
public class OrdermanagenentActivity extends BaseFragementActivity {

    MyOrdersFragment oneFragment, twoFragment, threeFragment;
    @InjectView(R.id.left_img)
    ImageView leftImg;
    @InjectView(R.id.left)
    LinearLayout left;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.sign)
    TextView sign;
    @InjectView(R.id.rightrig)
    ImageView rightrig;
    @InjectView(R.id.right)
    LinearLayout right;
    @InjectView(R.id.head)
    RelativeLayout head;
    @InjectView(R.id.one_text)
    TextView oneText;
    @InjectView(R.id.one_view)
    LinearLayout oneView;
    @InjectView(R.id.one_image)
    View oneImage;
    @InjectView(R.id.one_ly)
    LinearLayout oneLy;
    @InjectView(R.id.two_text)
    TextView twoText;
    @InjectView(R.id.two_view)
    LinearLayout twoView;
    @InjectView(R.id.two_image)
    View twoImage;
    @InjectView(R.id.two_ly)
    LinearLayout twoLy;
    @InjectView(R.id.three_text)
    TextView threeText;
    @InjectView(R.id.three_view)
    LinearLayout threeView;
    @InjectView(R.id.three_image)
    View threeImage;
    @InjectView(R.id.three_ly)
    LinearLayout threeLy;
    @InjectView(R.id.menusd)
    LinearLayout menusd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.inject(this);
        setTopTitle("Order management");
        setBackButton();
        setTabSelection(1);
        setTabSelection(2);
        setTabSelection(0);
    }

    @OnClick({R.id.one_ly, R.id.two_ly, R.id.three_ly})
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.one_ly) {
            setTabSelection(0);
        } else if (id == R.id.two_ly) {
            setTabSelection(1);
        } else if (id == R.id.three_ly) {
            setTabSelection(2);
        }
    }

    private void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                oneImage.setVisibility(View.VISIBLE);
                oneText.setTextColor(getResources().getColor(R.color.chengse));
                if (oneFragment == null) {
                    oneFragment = new MyOrdersFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("myorder_type", "1");
                    oneFragment.setArguments(bundle);
                    transaction.add(R.id.content, oneFragment, "oneFragment");
                } else {
                    transaction.show(oneFragment);
                }
                break;
            case 1:
                twoImage.setVisibility(View.VISIBLE);
                twoText.setTextColor(getResources().getColor(R.color.huise));
                if (twoFragment == null) {
                    twoFragment = new MyOrdersFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("myorder_type", "2");
                    twoFragment.setArguments(bundle);
                    transaction.add(R.id.content, twoFragment, "twoFragment");
                } else {
                    transaction.show(twoFragment);
                }
                break;
            case 2:
                threeImage.setVisibility(View.VISIBLE);
                threeText.setTextColor(getResources().getColor(R.color.huise));
                if (threeFragment == null) {
                    threeFragment = new MyOrdersFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("myorder_type", "3");
                    threeFragment.setArguments(bundle);
                    transaction.add(R.id.content, threeFragment, "threeFragment");
                } else {
                    transaction.show(threeFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        oneImage.setVisibility(View.GONE);
        oneText.setTextColor(ContextCompat.getColor(this, R.color.zitihuise));

        twoImage.setVisibility(View.GONE);
        twoText.setTextColor(ContextCompat.getColor(this, R.color.zitihuise));

        threeImage.setVisibility(View.GONE);
        threeText.setTextColor(ContextCompat.getColor(this, R.color.zitihuise));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (oneFragment != null) {
            transaction.hide(oneFragment);
        }
        if (twoFragment != null) {
            transaction.hide(twoFragment);
        }
        if (threeFragment != null) {
            transaction.hide(threeFragment);
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

}
