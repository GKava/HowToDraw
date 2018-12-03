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
    Context context;

    public TwoImageAdapter(int width) {
        super();
        this.width = width;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new ContentFragment();
                Bundle args = new Bundle();
                args.putInt("bundle_int", pos);
                fr.setArguments(args);
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fr)
                        .addToBackStack(null)
                        .commit();

            }
        });

    }

    @NonNull
    @Override
    public TwoImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_all_construction, parent, false);

        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.height = width;
        view.setLayoutParams(lp);

        return new TwoImageAdapter.ViewHolder(view);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageName;


        public ViewHolder(View itemView) {
            super(itemView);
            imageName =(ImageView) itemView.findViewById(R.id.imageName);
        }

        public void bind(Item allConstructionItem) {
            Log.d(TAG, "bind");
        }
    }

    public  void addMessage(Item item) {
        Log.d(TAG, "addMessage");
        items.add(item);
        notifyItemChanged(items.size() - 1);
    }

}

