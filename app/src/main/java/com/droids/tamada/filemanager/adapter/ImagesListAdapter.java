package com.droids.tamada.filemanager.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.droids.tamada.filemanager.model.MediaFileListModel;
import com.example.satish.filemanager.R;

import java.io.File;
import java.util.List;

/**
 * Created by inventbird on 17/10/16.
 */
public class ImagesListAdapter extends RecyclerView.Adapter<ImagesListAdapter.MyViewHolder> {
    private List<MediaFileListModel> mediaFileListModels;
    final int THUMB_SIZE = 64;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblFileName;
        public ImageView imgItemIcon;

        public MyViewHolder(View view) {
            super(view);
            lblFileName = (TextView) view.findViewById(R.id.file_name);
            imgItemIcon = (ImageView) view.findViewById(R.id.icon);
        }
    }

    public ImagesListAdapter(List<MediaFileListModel> mediaFileListModels) {
        this.mediaFileListModels = mediaFileListModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.media_list_item_view, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        MediaFileListModel mediaFileListModel = mediaFileListModels.get(position);
        holder.lblFileName.setText(mediaFileListModel.getFileName());
        File imgFile = new File(mediaFileListModel.getFilePath());
        if (imgFile.exists()) {
            Log.d("action", mediaFileListModel.getFilePath());
            Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile( mediaFileListModel.getFilePath()),
                    THUMB_SIZE, THUMB_SIZE);
            holder.imgItemIcon.setImageBitmap(ThumbImage);
        }
    }

    @Override
    public int getItemCount() {
        return mediaFileListModels.size();
    }
}