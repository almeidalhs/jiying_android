package com.atman.jishang.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.Urls;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.choicepicture_library.R;
import com.choicepicture_library.model.SelectImageItem;
import com.choicepicture_library.tools.Bimp;
import com.choicepicture_library.tools.FileUtils;
import com.corelib.util.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/3 10:06
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MemberDetailsGridViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int selectedPosition = -1;
    private boolean shape;
    private Context context;
    private ImageLoader mImageLoader;
    private List<String> imgList;
    private ScrollView memberdetailsScrollviewLl;
    private AdapterInterface onClick;

    public boolean isShape() {
        return shape;
    }

    public void setShape(boolean shape) {
        this.shape = shape;
    }

    public MemberDetailsGridViewAdapter(Context context, ScrollView memberdetailsScrollviewLl
            , ImageLoader iL, AdapterInterface onClick) {
        this.context = context;
        this.mImageLoader = iL;
        this.imgList = new ArrayList<>();
        this.memberdetailsScrollviewLl = memberdetailsScrollviewLl;
        this.onClick = onClick;
        inflater = LayoutInflater.from(context);
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
        update();
        notifyDataSetChanged();
    }

    public void update() {
        loading();
    }

    public int getCount() {
        return imgList.size();
    }

    public Object getItem(int arg0) {

        return null;
    }

    public long getItemId(int arg0) {

        return 0;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    /**
     * ListView Item
     */
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int coord = position;
        ViewHolder holder = null;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_published_grida,parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        mImageLoader.displayImage(
                Urls.HEADIMG_BEFOR + imgList.get(position),
                holder.image,
                BaiYeBaseApplication.getApp().getOptions(), new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {
                        memberdetailsScrollviewLl.scrollTo(0,0);
                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {
                        memberdetailsScrollviewLl.scrollTo(0,0);
                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        memberdetailsScrollviewLl.scrollTo(0,0);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {
                        memberdetailsScrollviewLl.scrollTo(0,0);
                    }
                });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(v, position);
            }
        });


        return convertView;
    }

    public class ViewHolder {
        public ImageView image;
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void loading() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (Bimp.max == Bimp.drr.size()) {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                        break;
                    } else {
                        try {
                            if (Bimp.drr.size()==0 || Bimp.num>=Bimp.drr.size()) {
                                return;
                            }
                            String path = Bimp.drr.get(Bimp.num);
                            System.out.println(path);
                            Bitmap bm = Bimp.revitionImageSize(path);
                            Bimp.bmp.add(bm);
                            String newStr = path.substring(
                                    path.lastIndexOf("/") + 1,
                                    path.lastIndexOf("."));
                            FileUtils.saveBitmap(bm, "" + newStr);
                            SelectImageItem item = new SelectImageItem();
                            item.setDrr_or(path);
                            item.setDrr_sle("" + newStr);
                            Bimp.drr_or.add(item);
                            Bimp.num += 1;
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
