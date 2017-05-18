package com.quark.skillopedia.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.quark.api.auto.bean.ListCourseBanners;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.uiview.zhanghu.jiaolian.NewcourseActivity;

import org.kymjs.kjframe.Core;
import org.kymjs.kjframe.bitmap.BitmapCallBack;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


/**
 * @author Administrator
 */
public class RicPicAdapter extends BaseAdapter {

    List<ListCourseBanners> list;
    private Context context;
    Handler handler;
    int status;

    public RicPicAdapter(Context context, Handler handler, int status) {
        list = NewcourseActivity.pics;
        this.context = context;
        this.handler = handler;
        this.status = status;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.ricpic_item, null);
        ImageView pic = (ImageView) convertView.findViewById(R.id.pic);
        ImageView remove = (ImageView) convertView.findViewById(R.id.remove);
//        if (status == 2 || status == 1) {
//            remove.setVisibility(View.GONE);
//        } else {
//            remove.setVisibility(View.VISIBLE);
//        }

        if (list.get(i).isadd()) {
            remove.setVisibility(View.GONE);
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.arg1 = i;
                    msg.what = 202;
                    handler.sendMessage(msg);
                }
            });
            remove.setVisibility(View.GONE);
        } else if (list.get(i).ismodify()) {
            remove.setVisibility(View.VISIBLE);
            pic.setImageBitmap(list.get(i).getBitmap());
        } else {
            remove.setVisibility(View.VISIBLE);
            loadImage(list.get(i).getImage_01(), pic, i);
        }

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.arg1 = i;
                msg.what = 203;
                handler.sendMessage(msg);
            }
        });

        return convertView;
    }

    //將圖片保存在本地
    public void loadImage(String cpic, final ImageView img, final int current) {
        if ((cpic != null) && (!cpic.equals(""))) {
            try {
                cpic = URLEncoder.encode(cpic, "UTF-8");
                String url = ApiHttpClient.loadImage + cpic;
                new Core.Builder().view(img).url(url)
                        .loadBitmap(new ColorDrawable(0x000000))
                        .errorBitmap(new ColorDrawable(0x000000))
                        .size(0, 0)
                        .bitmapCallBack(new BitmapCallBack() {
                            @Override
                            public void onFailure(Exception arg0) {
//										AppContext.showToast(R.string.tip_load_image_faile);
                                img.setImageResource(R.drawable.example_1);
                            }

                            @Override
                            public void onSuccess(Bitmap bitmap) {
                                super.onSuccess(bitmap);

                                File file1 = new File(Environment.getExternalStorageDirectory() + "/tempImage" + current + ".jpg"); //将要保存图片的路径
                                try {
                                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                                    bos.flush();
                                    bos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                list.get(current).setBitmap(bitmap);
                                list.get(current).setFile1(file1);
                            }
                        }).doTask();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }


}


