package com.quark.skillopedia.uiview.fenlei.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.quark.api.auto.bean.ListCourseCertification;
import com.quark.skillopedia.AppParam;
import com.quark.skillopedia.R;
import com.quark.skillopedia.adapter.CourseCertificationAdapter;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.base.BaseFragment;
import com.quark.skillopedia.util.Utils;

import org.kymjs.kjframe.Core;
import org.kymjs.kjframe.bitmap.BitmapCallBack;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/6/23 0023.
 * 课程详情 对应的 Certifications片段
 */
public class CertificationsFrament extends BaseFragment {
    @InjectView(R.id.list)
    RecyclerView list;

    CourseCertificationAdapter adapter;
    List<ListCourseCertification> courseCertifications;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View certificationsView = inflater.inflate(R.layout.certifications_fragment_layout, container, false);
        ButterKnife.inject(this, certificationsView);
        courseCertifications = (List<ListCourseCertification>) getArguments().getSerializable("courseCertifications");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        list.setLayoutManager(layoutManager);

        if (courseCertifications != null && courseCertifications.size() > 0) {
            deal();
        } else {
            ListCourseCertification empty = new ListCourseCertification();
            courseCertifications.add(empty);
            adapter = new CourseCertificationAdapter(getActivity(), courseCertifications);
            list.setAdapter(adapter);
        }


        return certificationsView;
    }

    public void deal() {
        adapter = new CourseCertificationAdapter(getActivity(), courseCertifications);
        list.setAdapter(adapter);
        adapter.setItemClickListener(new CourseCertificationAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                showpic(position);
            }

        });

    }

    public void showpic(int i){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View popview = inflater.inflate(R.layout.skill_dialog, null);
        final PopupWindow popupWindow;
        ImageView dialog_close = (ImageView)popview.findViewById(R.id.dialog_close);
        ImageView pic = (ImageView)popview.findViewById(R.id.pic);

        RelativeLayout per = (RelativeLayout)popview.findViewById(R.id.per);
        per.getBackground().setAlpha(125);

        loadImage(courseCertifications.get(i).getImage_01(), pic, R.drawable.example_2);

        popupWindow = new PopupWindow(popview, new AppParam().getScreenWidth(getActivity()), new AppParam().getScreenHeight(getActivity()));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(getActivity().findViewById(R.id.head),0,-Utils.dip2px(getActivity(),60));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

//显示进度条
    public void loadImage(String cpic, final ImageView img, final int defaultpic){
        if((cpic!=null)&&(!cpic.equals(""))){
            try {
                showWait(true);
                cpic = URLEncoder.encode(cpic, "UTF-8");
                String url = ApiHttpClient.loadImage + cpic;
                new Core.Builder().view(img).url(url)
                        .loadBitmap(new ColorDrawable(0x000000))
                        .errorBitmap(new ColorDrawable(0x000000))
                        .size(0, 0)
                        .bitmapCallBack(new BitmapCallBack() {
                            @Override
                            public void onPreLoad() {

                                img.setImageResource(defaultpic);
                            }
                            @Override
                            public void onFinish() {
                                showWait(false);
                            }
                            @Override
                            public void onFailure(Exception arg0) {
                                showWait(false);
                                img.setImageResource(defaultpic);
                            }
                        }).doTask();
            } catch (UnsupportedEncodingException e) {
                showWait(false);
                e.printStackTrace();
            }
        }
    }
}
