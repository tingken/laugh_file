package com.quark.skillopedia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.quark.api.auto.bean.ListCatetory;
import com.quark.skillopedia.R;
import com.quark.skillopedia.api.ApiHttpClient;
import com.quark.skillopedia.uiview.fenlei.CourseDetailActivity;
import com.quark.skillopedia.uiview.fenlei.CourseListActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class HottypeAdapter extends BaseAdapter {

    private Context context;

    private List<ListCatetory> datas;
    int typeminhight;
    int typebighight;

    public HottypeAdapter(Context context, List<ListCatetory> datas, int typeminhight, int typebighight) {
        this.context = context;
        this.datas = datas;
        this.typeminhight = typeminhight;
        this.typebighight = typebighight;

    }

    @Override
    public int getCount() {
        return datas.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (position % 2 == 1) {//基数
            convertView = LayoutInflater.from(context).inflate(R.layout.hottype_right_item, null);
            ImageView type1 = (ImageView) convertView.findViewById(R.id.type1);
            ImageView type2 = (ImageView) convertView.findViewById(R.id.type2);
            ImageView type3 = (ImageView) convertView.findViewById(R.id.type3);
            ImageView type4 = (ImageView) convertView.findViewById(R.id.type4);
            ImageView type5 = (ImageView) convertView.findViewById(R.id.type5);

            ImageView type11 = (ImageView) convertView.findViewById(R.id.type11);
            ImageView type22 = (ImageView) convertView.findViewById(R.id.type22);
            ImageView type33 = (ImageView) convertView.findViewById(R.id.type33);
            ImageView type44 = (ImageView) convertView.findViewById(R.id.type44);
            ImageView type55 = (ImageView) convertView.findViewById(R.id.type55);

            ViewGroup.LayoutParams params1 = type1.getLayoutParams();
            params1.height = typebighight;
            params1.width = typebighight;
            type1.setLayoutParams(params1);
            type11.setLayoutParams(params1);

            ViewGroup.LayoutParams params2 = type2.getLayoutParams();
            params2.height = typeminhight;
            params2.width = typeminhight;
            type2.setLayoutParams(params2);
            type22.setLayoutParams(params2);

            ViewGroup.LayoutParams params3 = type3.getLayoutParams();
            params3.height = typeminhight;
            params3.width = typeminhight;
            type3.setLayoutParams(params3);
            type33.setLayoutParams(params3);

            ViewGroup.LayoutParams params4 = type4.getLayoutParams();
            params4.height = typeminhight;
            params4.width = typeminhight;
            type4.setLayoutParams(params4);
            type44.setLayoutParams(params4);

            ViewGroup.LayoutParams params5 = type5.getLayoutParams();
            params5.height = typeminhight;
            params5.width = typeminhight;
            type5.setLayoutParams(params5);
            type55.setLayoutParams(params5);

            type1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context, CourseListActivity.class);
                    in.putExtra("datatype", "1");
                    context.startActivity(in);

                }
            });

            ApiHttpClient.loadImage(datas.get(position).getImage_01(), type1, R.drawable.example_4);

            if (datas.get(position).getCourses() != null) {
                if (datas.get(position).getCourses().size() > 0) {
                    ApiHttpClient.loadImage(datas.get(position).getCourses().get(0).getCoach_image(), type2, R.drawable.example_3);
                    if (datas.get(position).getCourses().get(0).getCourse_id()+"" != null){
                        type2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(context, CourseDetailActivity.class);
                                in.putExtra("course_id", datas.get(position).getCourses().get(0).getCourse_id()+"");
                                context.startActivity(in);
                            }
                        });
                    }
                }
                if (datas.get(position).getCourses().size() > 1) {
                    ApiHttpClient.loadImage(datas.get(position).getCourses().get(1).getCoach_image(), type3, R.drawable.example_3);
                    if (datas.get(position).getCourses().get(1).getCourse_id()+"" != null){
                        type3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(context, CourseDetailActivity.class);
                                in.putExtra("course_id", datas.get(position).getCourses().get(1).getCourse_id()+"");
                                context.startActivity(in);
                            }
                        });
                    }
                }
                if (datas.get(position).getCourses().size() > 2) {
                    ApiHttpClient.loadImage(datas.get(position).getCourses().get(2).getCoach_image(), type4, R.drawable.example_3);
                    if (datas.get(position).getCourses().get(2).getCourse_id()+"" != null){
                        type4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(context, CourseDetailActivity.class);
                                in.putExtra("course_id", datas.get(position).getCourses().get(2).getCourse_id()+"");
                                context.startActivity(in);
                            }
                        });
                    }
                }
                if (datas.get(position).getCourses().size() > 3) {
                    ApiHttpClient.loadImage(datas.get(position).getCourses().get(3).getCoach_image(), type5, R.drawable.example_3);
                    if (datas.get(position).getCourses().get(3).getCourse_id()+"" != null){
                        type5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(context, CourseDetailActivity.class);
                                in.putExtra("course_id", datas.get(position).getCourses().get(3).getCourse_id()+"");
                                context.startActivity(in);
                            }
                        });
                    }
                }
            }
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.hottype_left_item, null);
            ImageView type1 = (ImageView) convertView.findViewById(R.id.type1);
            ImageView type2 = (ImageView) convertView.findViewById(R.id.type2);
            ImageView type3 = (ImageView) convertView.findViewById(R.id.type3);
            ImageView type4 = (ImageView) convertView.findViewById(R.id.type4);
            ImageView type5 = (ImageView) convertView.findViewById(R.id.type5);

            ImageView type11 = (ImageView) convertView.findViewById(R.id.type11);
            ImageView type22 = (ImageView) convertView.findViewById(R.id.type22);
            ImageView type33 = (ImageView) convertView.findViewById(R.id.type33);
            ImageView type44 = (ImageView) convertView.findViewById(R.id.type44);
            ImageView type55 = (ImageView) convertView.findViewById(R.id.type55);

            ViewGroup.LayoutParams params1 = type1.getLayoutParams();
            params1.height = typebighight;
            params1.width = typebighight;
            type1.setLayoutParams(params1);
            type11.setLayoutParams(params1);

            ViewGroup.LayoutParams params2 = type2.getLayoutParams();
            params2.height = typeminhight;
            params2.width = typeminhight;
            type2.setLayoutParams(params2);
            type22.setLayoutParams(params2);

            ViewGroup.LayoutParams params3 = type3.getLayoutParams();
            params3.height = typeminhight;
            params3.width = typeminhight;
            type3.setLayoutParams(params3);
            type33.setLayoutParams(params3);

            ViewGroup.LayoutParams params4 = type4.getLayoutParams();
            params4.height = typeminhight;
            params4.width = typeminhight;
            type4.setLayoutParams(params4);
            type44.setLayoutParams(params4);

            ViewGroup.LayoutParams params5 = type5.getLayoutParams();
            params5.height = typeminhight;
            params5.width = typeminhight;
            type5.setLayoutParams(params5);
            type55.setLayoutParams(params5);

            type1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context, CourseListActivity.class);
                    in.putExtra("datatype","1");
                    context.startActivity(in);

                }
            });

            ApiHttpClient.loadImage(datas.get(position).getImage_01(), type1, R.drawable.example_4);
            if (datas.get(position).getCourses() != null) {
                if (datas.get(position).getCourses().size() > 0) {
                    ApiHttpClient.loadImage(datas.get(position).getCourses().get(0).getCoach_image(), type2, R.drawable.example_3);
                    if (datas.get(position).getCourses().get(0).getCourse_id()+"" != null){
                        type2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(context, CourseDetailActivity.class);
                                in.putExtra("course_id", datas.get(position).getCourses().get(0).getCourse_id()+"");
                                context.startActivity(in);
                            }
                        });
                    }
                }
                if (datas.get(position).getCourses().size() > 1) {
                    ApiHttpClient.loadImage(datas.get(position).getCourses().get(1).getCoach_image(), type3, R.drawable.example_3);
                    if (datas.get(position).getCourses().get(1).getCourse_id()+"" != null){
                        type3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(context, CourseDetailActivity.class);
                                in.putExtra("course_id", datas.get(position).getCourses().get(1).getCourse_id()+"");
                                context.startActivity(in);
                            }
                        });
                    }
                }
                if (datas.get(position).getCourses().size() > 2) {
                    ApiHttpClient.loadImage(datas.get(position).getCourses().get(2).getCoach_image(), type4, R.drawable.example_3);
                    if (datas.get(position).getCourses().get(2).getCourse_id()+"" != null){
                        type4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(context, CourseDetailActivity.class);
                                in.putExtra("course_id", datas.get(position).getCourses().get(2).getCourse_id()+"");
                                context.startActivity(in);
                            }
                        });
                    }

                }
                if (datas.get(position).getCourses().size() > 3) {
                    ApiHttpClient.loadImage(datas.get(position).getCourses().get(3).getCoach_image(), type5, R.drawable.example_3);
                    if (datas.get(position).getCourses().get(3).getCourse_id()+"" != null){
                        type5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(context, CourseDetailActivity.class);
                                in.putExtra("course_id", datas.get(position).getCourses().get(3).getCourse_id()+"");
                                context.startActivity(in);
                            }
                        });
                    }
                }
            }
        }


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.type2)
        ImageView type2;
        @InjectView(R.id.type3)
        ImageView type3;
        @InjectView(R.id.type4)
        ImageView type4;
        @InjectView(R.id.type5)
        ImageView type5;
        @InjectView(R.id.type1)
        ImageView type1;


        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
