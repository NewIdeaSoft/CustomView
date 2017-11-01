package com.nisoft.popupwindow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/1.
 */

public class PopupWindowListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mMessageList;
    private OnItemViewClickedListener mListener;
    public PopupWindowListAdapter(Context context, ArrayList<String> messageList,OnItemViewClickedListener onItemViewClickedListener) {
        mContext = context;
        mMessageList = messageList;
        mListener = onItemViewClickedListener;
    }

    @Override
    public int getCount() {
        return mMessageList == null?0:mMessageList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMessageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext,R.layout.item_popup_window_list,null);
            holder.mUserImageView = (ImageView) convertView.findViewById(R.id.iv_item_popup_icon);
            holder.mMessageTextView = (TextView) convertView.findViewById(R.id.tv_item_popup_message);
            holder.mDeleteImageView = (ImageView) convertView.findViewById(R.id.iv_item_popup_delete);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String message = mMessageList.get(position);
        holder.mMessageTextView.setText(message);
        holder.mMessageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMessageClicked(message);
            }
        });
        holder.mDeleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDeleteClicked(message);
                mMessageList.remove(message);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    private class ViewHolder{
        ImageView mUserImageView;
        TextView mMessageTextView;
        ImageView mDeleteImageView;
    }
    public interface OnItemViewClickedListener{
        void onMessageClicked(String message);
        void onDeleteClicked(String message);
    }
}
