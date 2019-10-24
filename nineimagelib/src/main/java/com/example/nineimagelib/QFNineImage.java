package com.example.nineimagelib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

/**
 * @author:wangshouxue
 * @date:2019-10-23 14:00
 * @description:九宫格
 */
public class QFNineImage extends LinearLayout {
    private Context mContext;
    private int SCREENWIDTH;
    private int SCREENHEIGHT;
    private Random random;//用于随机取色生成占位符或失败图
    private View view_one;//只有一张图片
    private View view_two_three;
    private View view_four;
    private View view_five_more;
    Drawable ChangeColorsDrawable[];

    public QFNineImage(Context context) {
        this(context,null,0);
    }

    public QFNineImage(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QFNineImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.mContext=context;
        random=new Random();
        SCREENWIDTH=mContext.getResources().getDisplayMetrics().widthPixels;
        SCREENHEIGHT=mContext.getResources().getDisplayMetrics().heightPixels;
        ChangeColorsDrawable = new Drawable[]{ContextCompat.getDrawable(mContext, R.color.color_1),
                ContextCompat.getDrawable(mContext, R.color.color_2),
                ContextCompat.getDrawable(mContext, R.color.color_3),
                ContextCompat.getDrawable(mContext, R.color.color_4),
                ContextCompat.getDrawable(mContext, R.color.color_5),
                ContextCompat.getDrawable(mContext, R.color.color_6),
                ContextCompat.getDrawable(mContext, R.color.color_7)};
    }
    //设置数据源
    public void setData(final List<NineImageEntity> infos,NineClicklistener nineClicklistener){
        this.nineClicklistener=nineClicklistener;
        int size = 0;
        if (infos != null){
            size = infos.size();
        }
        if (size >0) {
            this.setVisibility(View.VISIBLE);
        }
        if (view_one != null) {
            view_one.setVisibility(GONE);
        }
        if (view_two_three != null) {
            view_two_three.setVisibility(GONE);
        }
        if (view_four != null) {
            view_four.setVisibility(GONE);
        }
        if (view_five_more != null) {
            view_five_more.setVisibility(GONE);
        }
        if (size == 0) {//无图片
            this.setVisibility(View.GONE);
        }else if (size == 1) {//1张图
            ViewHolder_one viewHolder_one;
            if (view_one == null) {
                view_one = LayoutInflater.from(getContext()).inflate(R.layout.layout_nineimg_one, null);
                this.addView(view_one);
                viewHolder_one = new ViewHolder_one(view_one);
                view_one.setTag(viewHolder_one);
            } else {
                viewHolder_one = (ViewHolder_one) view_one.getTag();
            }
            view_one.setVisibility(VISIBLE);
            NineImageEntity info = infos.get(0);
            String oneImageSize = getOneImageSize(info.getWidth(), info.getHeight(), SCREENHEIGHT * 2 / 5, SCREENHEIGHT / 15,
                    SCREENWIDTH * 2 / 3, SCREENWIDTH / 6);
            int width = Integer.parseInt(oneImageSize.split("#")[0]);
            int height = Integer.parseInt(oneImageSize.split("#")[1]);
            if (width == 0 || height == 0) {
                width = dp2px(200);
                height = width;
            }
            viewHolder_one.simpleDraweeView_one.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
            setController(viewHolder_one.simpleDraweeView_one, viewHolder_one.img_hasgif, infos, 0);
        }else if (size > 1 && size <= 3) {//2或3张图
            ViewHolder_two_three viewHolder_two_three;
            if (view_two_three == null) {
                view_two_three = LayoutInflater.from(getContext()).inflate(R.layout.layout_nineimg_2or3, null);
                this.addView(view_two_three);
                viewHolder_two_three = new ViewHolder_two_three(view_two_three);
                view_two_three.setTag(viewHolder_two_three);
            } else {
                viewHolder_two_three = (ViewHolder_two_three) view_two_three.getTag();
            }
            view_two_three.setVisibility(VISIBLE);
            setController(viewHolder_two_three.simpleDraweeView_one, viewHolder_two_three.img_hasgif_one, infos, 0);
            setController(viewHolder_two_three.simpleDraweeView_two, viewHolder_two_three.img_hasgif_two, infos, 1);
            if (size == 3) {
                viewHolder_two_three.rel_three.setVisibility(VISIBLE);
                setController(viewHolder_two_three.simpleDraweeView_three, viewHolder_two_three.img_hasgif_three, infos, 2);
            } else {
                viewHolder_two_three.rel_three.setVisibility(INVISIBLE);
            }
        }else if (size == 4) {//4张图片
            ViewHolder_four viewHolder_four;
            if (view_four == null) {
                view_four = LayoutInflater.from(getContext()).inflate(R.layout.layout_nineimg_four, null);
                this.addView(view_four);
                viewHolder_four = new ViewHolder_four(view_four);
                view_four.setTag(viewHolder_four);
            } else {

                viewHolder_four = (ViewHolder_four) view_four.getTag();
            }
            view_four.setVisibility(VISIBLE);
            setController(viewHolder_four.simpleDraweeView_one, viewHolder_four.img_hasgif_one, infos, 0);
            setController(viewHolder_four.simpleDraweeView_two, viewHolder_four.img_hasgif_two, infos, 1);
            setController(viewHolder_four.simpleDraweeView_three, viewHolder_four.img_hasgif_three, infos, 2);
            setController(viewHolder_four.simpleDraweeView_four, viewHolder_four.img_hasgif_four, infos, 3);
        }else if (size > 4) {//5-9张图片
            ViewHolder_five_more viewHolder_five_more;
            if (view_five_more == null) {
                view_five_more = LayoutInflater.from(getContext()).inflate(R.layout.layout_nineimg_5to9, null);
                this.addView(view_five_more);
                viewHolder_five_more = new ViewHolder_five_more(view_five_more);
                view_five_more.setTag(viewHolder_five_more);
            } else {
                viewHolder_five_more = (ViewHolder_five_more) view_five_more.getTag();

            }
            view_five_more.setVisibility(VISIBLE);
            viewHolder_five_more.rel_six.setVisibility(View.INVISIBLE);
            viewHolder_five_more.rel_seven.setVisibility(View.INVISIBLE);
            viewHolder_five_more.rel_eight.setVisibility(View.INVISIBLE);
            viewHolder_five_more.rel_nine.setVisibility(View.INVISIBLE);
            viewHolder_five_more.ll_three_row.setVisibility(View.GONE);

            setController(viewHolder_five_more.simpleDraweeView_one, viewHolder_five_more.img_hasgif_one, infos, 0);
            setController(viewHolder_five_more.simpleDraweeView_two, viewHolder_five_more.img_hasgif_two, infos, 1);
            setController(viewHolder_five_more.simpleDraweeView_three, viewHolder_five_more.img_hasgif_three, infos, 2);
            setController(viewHolder_five_more.simpleDraweeView_four, viewHolder_five_more.img_hasgif_four, infos, 3);
            setController(viewHolder_five_more.simpleDraweeView_five, viewHolder_five_more.img_hasgif_five, infos, 4);
            if (size >= 6) {
                viewHolder_five_more.rel_six.setVisibility(View.VISIBLE);
                setController(viewHolder_five_more.simpleDraweeView_six, viewHolder_five_more.img_hasgif_six, infos, 5);
            }
            if (size >= 7) {
                viewHolder_five_more.ll_three_row.setVisibility(View.VISIBLE);
                viewHolder_five_more.rel_seven.setVisibility(View.VISIBLE);
                setController(viewHolder_five_more.simpleDraweeView_seven, viewHolder_five_more.img_hasgif_seven, infos, 6);
            }
            if (size >= 8) {
                viewHolder_five_more.ll_three_row.setVisibility(View.VISIBLE);
                viewHolder_five_more.rel_eight.setVisibility(View.VISIBLE);
                setController(viewHolder_five_more.simpleDraweeView_eight, viewHolder_five_more.img_hasgif_eight, infos, 7);
            }
            if (size >= 9) {
                viewHolder_five_more.ll_three_row.setVisibility(View.VISIBLE);
                viewHolder_five_more.rel_nine.setVisibility(View.VISIBLE);
                setController(viewHolder_five_more.simpleDraweeView_nine, viewHolder_five_more.img_hasgif_nine, infos, 8);
            }
        }

    }
    /**
     * 为SimpleDraweeView设置Controller
     *
     * @param simpleDraweeView 加载图片的simpleDraweeView
     * @param gifView          表示图片是否是gif的imageview
     * @param infos            图片数据
     * @param position         选中图片的位置
     */
    @SuppressLint("ResourceType")
    private void setController(ImageView simpleDraweeView, ImageView gifView, final List<NineImageEntity> infos, final int position) {
        String filepath = relpaceJING("" + infos.get(position).getUrl());
        if (random == null) {
            random = new Random();
        }
        if (infos.size()>1){
            simpleDraweeView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }
        Drawable drawable = ChangeColorsDrawable[random.nextInt(7)];
        Glide.with(mContext).load(getUriWithPath(filepath)).placeholder(drawable).error(drawable).into(simpleDraweeView);
        simpleDraweeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                startViewpagerActivity(infos, position);
                nineClicklistener.imageClick(infos,position);
            }
        });
        if (isGifUrl("" + filepath)) {
            gifView.setVisibility(View.VISIBLE);
        } else {
            gifView.setVisibility(View.GONE);
        }
    }
    private String relpaceJING(String localurl) {
        String replaceUrl = localurl.replace("#", "%23");
        return replaceUrl;
    }
    /**
     * 根据filepath获取巷友圈图片的Uri
     */
    private String getUriWithPath(String filepath) {
        if (filepath.contains("file://")) {
            filepath = filepath.replace("file://", "");
        }
        String url;
        if (filepath.startsWith("/storage/")) {
            url = "file://" + mContext.getPackageName() + "/" + relpaceJING(filepath);
        } else if (filepath.startsWith("/data")) {
            url = "file://" + filepath;
        } else {
            url = getGif2JpgUrl("" + filepath);
        }
        return url;
    }
    /**
     * 七牛处理gif为jpg
     */
    private String getGif2JpgUrl(String url) {
        //webp暂时不支持，客户端发布只会有gif、小概率的webp暂时不管
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        if (url.contains(".gif")) {
            if (url.contains("imageView2")) {
                url = url + PL_GIF_THUMB;
            } else {
                url = url + "?imageView2/2/format/jpg";
            }
        }
        return url;
    }
    /**
     * 判断Url链接是否包含.gif
     */
    private boolean isGifUrl(String url) {
        if (url.toLowerCase().contains(".gif")) {
            return true;
        }
        return false;
    }
    /**
     * 获取gif的缩略图--加载url末尾
     */
    private final String PL_GIF_THUMB = "/format/jpg";

    class ViewHolder_one{
        ImageView simpleDraweeView_one;
        ImageView img_hasgif;

        public ViewHolder_one(View view){
            simpleDraweeView_one = view.findViewById(R.id.simpleDraweeView_one);
            img_hasgif = view.findViewById(R.id.img_hasgif);
        }
    }
    class ViewHolder_two_three{
        ImageView simpleDraweeView_one,simpleDraweeView_two,simpleDraweeView_three;
        ImageView img_hasgif_one,img_hasgif_two,img_hasgif_three;
        RelativeLayout rel_three;

        public ViewHolder_two_three(View view){
            simpleDraweeView_one = view.findViewById(R.id.simpleDraweeView_one);
            img_hasgif_one = view.findViewById(R.id.img_hasgif_one);
            simpleDraweeView_two = view.findViewById(R.id.simpleDraweeView_two);
            img_hasgif_two = view.findViewById(R.id.img_hasgif_two);
            simpleDraweeView_three = view.findViewById(R.id.simpleDraweeView_three);
            img_hasgif_three = view.findViewById(R.id.img_hasgif_three);
            rel_three = view.findViewById(R.id.rel_three);
            rel_three.setVisibility(View.INVISIBLE);

        }
    }
    class ViewHolder_four{
        ImageView simpleDraweeView_one,simpleDraweeView_two,simpleDraweeView_three,simpleDraweeView_four;
        ImageView img_hasgif_one,img_hasgif_two,img_hasgif_three,img_hasgif_four;

        public ViewHolder_four(View view){
            simpleDraweeView_one = view.findViewById(R.id.simpleDraweeView_one);
            img_hasgif_one = view.findViewById(R.id.img_hasgif_one);
            simpleDraweeView_two = view.findViewById(R.id.simpleDraweeView_two);
            img_hasgif_two = view.findViewById(R.id.img_hasgif_two);
            simpleDraweeView_three = view.findViewById(R.id.simpleDraweeView_three);
            img_hasgif_three = view.findViewById(R.id.img_hasgif_three);
            simpleDraweeView_four = view.findViewById(R.id.simpleDraweeView_four);
            img_hasgif_four = view.findViewById(R.id.img_hasgif_four);
        }
    }
    class ViewHolder_five_more{
        ImageView simpleDraweeView_one;
        ImageView img_hasgif_one;
        ImageView simpleDraweeView_two;
        ImageView img_hasgif_two;
        ImageView simpleDraweeView_three;
        ImageView img_hasgif_three;
        ImageView simpleDraweeView_four;
        ImageView img_hasgif_four;
        ImageView simpleDraweeView_five;
        ImageView img_hasgif_five;
        RelativeLayout rel_six;
        ImageView simpleDraweeView_six;
        ImageView img_hasgif_six;
        RelativeLayout rel_seven;
        ImageView simpleDraweeView_seven;
        ImageView img_hasgif_seven;
        RelativeLayout rel_eight;
        ImageView simpleDraweeView_eight;
        ImageView img_hasgif_eight;
        RelativeLayout rel_nine;
        ImageView simpleDraweeView_nine;
        ImageView img_hasgif_nine;
        LinearLayout ll_three_row;

        public ViewHolder_five_more(View view){
            simpleDraweeView_one = view.findViewById(R.id.simpleDraweeView_one);
            img_hasgif_one = view.findViewById(R.id.img_hasgif_one);
            simpleDraweeView_two = view.findViewById(R.id.simpleDraweeView_two);
            img_hasgif_two = view.findViewById(R.id.img_hasgif_two);
            simpleDraweeView_three = view.findViewById(R.id.simpleDraweeView_three);
            img_hasgif_three = view.findViewById(R.id.img_hasgif_three);
            simpleDraweeView_four = view.findViewById(R.id.simpleDraweeView_four);
            img_hasgif_four = view.findViewById(R.id.img_hasgif_four);
            simpleDraweeView_five = view.findViewById(R.id.simpleDraweeView_five);
            img_hasgif_five = view.findViewById(R.id.img_hasgif_five);
            simpleDraweeView_six = view.findViewById(R.id.simpleDraweeView_six);
            img_hasgif_six = view.findViewById(R.id.img_hasgif_six);
            simpleDraweeView_seven = view.findViewById(R.id.simpleDraweeView_seven);
            img_hasgif_seven = view.findViewById(R.id.img_hasgif_seven);
            simpleDraweeView_eight = view.findViewById(R.id.simpleDraweeView_eight);
            img_hasgif_eight = view.findViewById(R.id.img_hasgif_eight);
            simpleDraweeView_nine = view.findViewById(R.id.simpleDraweeView_nine);
            img_hasgif_nine = view.findViewById(R.id.img_hasgif_nine);
            rel_six = view.findViewById(R.id.rel_six);
            rel_seven = view.findViewById(R.id.rel_seven);
            rel_eight = view.findViewById(R.id.rel_eight);
            rel_nine = view.findViewById(R.id.rel_nine);
            ll_three_row = view.findViewById(R.id.ll_three_row);
        }
    }
    private int dp2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 获取单张图片的宽高
     * @param width     原图宽
     * @param height    原图高
     * @param maxHeight 最大高
     * @param minHeight 最小高
     * @param maxWidth  最大宽
     * @param minWidth  最小宽
     * @return
     */
    private String getOneImageSize(float width, float height, float maxHeight, float minHeight, float maxWidth, float minWidth) {
        float finalWidth;
        float finalHeight;
        float heightWidthScale = height / width;
        if (width >= height) {
            finalWidth = maxWidth;
            finalHeight = maxWidth * heightWidthScale;
            if (finalHeight < minHeight) {
                finalHeight = minHeight;
            }
        } else {
            finalHeight = maxHeight;
            finalWidth = maxHeight / heightWidthScale;
            if (finalWidth < minWidth) {
                finalWidth = minWidth;
            }
        }
        return (int) finalWidth + "#" + (int) finalHeight;
    }

    private NineClicklistener nineClicklistener;
    public interface NineClicklistener{
        void imageClick(List<NineImageEntity> infos, int position);
    }
}
