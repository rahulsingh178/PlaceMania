package com.place.mania.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.place.mania.DataObjects.InstagramObject;
import com.place.mania.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chirag on 3/17/15.
 */
public class ImageListAdapter extends BaseAdapter {


    private Context context;
    private List<String> listDataHeader; // header titles
    // child data in format of header title, child title
    private ArrayList<InstagramObject> datalist;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    Activity activity;
    public ImageListAdapter(Context context, ArrayList<InstagramObject> datalist, Activity activity) {
        this.activity = activity;
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public InstagramObject getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final InstagramObject childParent = (InstagramObject) datalist.get(position);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_image, null);
        }

        imageLoader.displayImage(datalist.get(position).getUrl(),(ImageView)(convertView.findViewById(R.id.image)));
        ((TextView)convertView.findViewById(R.id.caption)).setText(datalist.get(position).getCaption());
        return convertView;
    }


    public  DisplayImageOptions getRelatedContentOptions() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .resetViewBeforeLoading(false)
                .showImageForEmptyUri(R.drawable.more_default_thumbnail)
                .showImageOnLoading(R.drawable.more_default_thumbnail).build();

        return defaultOptions;
    }

}
