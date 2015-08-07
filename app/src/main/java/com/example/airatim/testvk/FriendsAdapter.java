package com.example.airatim.testvk;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by Airat Im on 07.08.2015.
 */
public class FriendsAdapter extends ArrayAdapter<User>{
    private final Activity activity;
    private final ArrayList<User> entries;

    public FriendsAdapter(final Activity a,final int textViewResourseId, final ArrayList<User> entries ){
        super(a,textViewResourseId, entries);
        this.entries = entries;
        activity =a;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View v = convertView;
        ViewHolder holder;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.mylistitem,parent,false);
            holder = new ViewHolder();
            holder.textView = (TextView) v.findViewById(R.id.label);
            holder.imageView = (ImageView) v.findViewById(R.id.logo);
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }
        User user = entries.get(position);
        if ( user != null){
            holder.textView.setText(user.getName());
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
            imageLoader.displayImage(user.getUrl(), holder.imageView);

        }
        return v;
    }

    private static class ViewHolder{
        public ImageView imageView;
        public TextView textView;
    }
}
