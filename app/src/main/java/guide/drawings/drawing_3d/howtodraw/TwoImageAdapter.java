package guide.drawings.drawing_3d.howtodraw;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;



public class TwoImageAdapter extends RecyclerView.Adapter<TwoImageAdapter.ViewHolder>  {

    private ArrayList<Item> items = new ArrayList<Item>();
    private static final String TAG = "MY LOG ADAPTER ";
    private int width;
    private int fragment_id;
    private OnImageClickListener onImageClickListener;

    public interface OnImageClickListener {
        void onImageClick(int position);
    }

    public TwoImageAdapter(int width, int fragment_id) {
        super();
        this.width = width;
        this.fragment_id = fragment_id;
    }

    public void setListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull TwoImageAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder");
        final int pos = position;
        final Item item = items.get(position);

        Glide.with(holder.itemView.getContext()).load(item.getImageName1())
                .thumbnail(0.5f)
                .into(holder.imageName);

        holder.itemView.setTag(pos);

        //holder.itemView
    }

    @NonNull
    @Override
    public TwoImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_all_construction, parent, false);

        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.height = width;
        view.setLayoutParams(lp);

        TwoImageAdapter.ViewHolder viewHolder = new TwoImageAdapter.ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onImageClickListener != null) {
                    onImageClickListener.onImageClick((Integer) view.getTag());
                }
            }
        });

        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageName;

        public ViewHolder(View itemView) {
            super(itemView);
            imageName =(ImageView) itemView.findViewById(R.id.imageName);
        }

//        public void bind(Item allConstructionItem) {
//            Log.d(TAG, "bind");
//        }
    }

    public  void addMessage(Item item) {
        Log.d(TAG, "addMessage");
        items.add(item);
        notifyItemChanged(items.size() - 1);
    }

}

