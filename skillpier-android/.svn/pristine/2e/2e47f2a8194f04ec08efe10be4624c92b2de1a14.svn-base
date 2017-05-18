package com.quark.skillopedia.uiview.zhanghu.jiaolian;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quark.skillopedia.R;
import com.quark.skillopedia.base.BaseActivity;
import com.quark.skillopedia.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/5/26 0026.
 * 折扣
 */
public class DiscountActivity extends BaseActivity {

    String discount_onetion_pur_money, discount_price, type;
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
    @InjectView(R.id.text1)
    TextView text1;
    @InjectView(R.id.money)
    EditText money;
    @InjectView(R.id.discountprice)
    EditText discountprice;
    @InjectView(R.id.add_bt)
    Button addBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        ButterKnife.inject(this);
        setTopTitle("Discount");
        setBackButton();
        discount_onetion_pur_money = (String) getValue4Intent("discount_onetion_pur_money");
        discount_price = (String) getValue4Intent("discount_price");

        money.setText(discount_onetion_pur_money);
        discountprice.setText(discount_price);

    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    String moneystr, discountpricestr;

    @OnClick(R.id.add_bt)
    public void onClick() {
        moneystr = money.getText().toString();
        discountpricestr = discountprice.getText().toString();
        if (check()) {
            Intent intent = new Intent();
            intent.putExtra("moneystr", moneystr);
            intent.putExtra("discountpricestr", discountpricestr);//优惠的钱
            setResult(102, intent);
            finish();
        }
    }

    public boolean check() {
        if (Utils.isEmpty(discountpricestr)) {
            showToast("Please enter a discount");
            return false;
        }
        if (Utils.isNumericZheng(discountpricestr)){
            int number = Integer.valueOf(discountpricestr);
            if (number<0||number>100){
                showToast("Number Only,0-100");
                return  false;
            }
        }


        return true;
    }

}
