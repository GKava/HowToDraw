package guide.drawings.drawing_3d.howtodraw.Fragments;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import guide.drawings.drawing_3d.howtodraw.Atapters.TwoImageAdapter;
import guide.drawings.drawing_3d.howtodraw.Item;
import guide.drawings.drawing_3d.howtodraw.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseCategory extends Fragment implements TwoImageAdapter.OnImageClickListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private TwoImageAdapter adapter;
    private InterstitialAd mInterstitialAd;
    int bundleInt;

    @Override
    public void onImageClick(int position) {
        viewAds();
        Fragment fr = new Content();
        Bundle args = new Bundle();
        args.putInt("bundle_int", position);
        fr.setArguments(args);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fr)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onPause() {
        if (adapter != null) {
            adapter.setListener(null);
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.setListener(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_fragment_menu, container, false);

        MobileAds.initialize(getActivity(),
                "ca-app-pub-3940256099942544~3347511713");

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        recyclerView = view.findViewById(R.id.recycler);
        verticalLinearLayoutManager = new GridLayoutManager(getActivity(), 1);
        verticalLinearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/2;


        Bundle bundle = getArguments();
        if (bundle != null) {
            bundleInt = bundle.getInt("bundle_int");
        }

        adapter = new TwoImageAdapter(width,2);
        recyclerView.setAdapter(adapter);

        if (bundleInt==0) {
            adapter.addMessage(new Item(R.drawable.im1, "Name content HARD"));
        }
        if (bundleInt==1) {
            adapter.addMessage(new Item(R.drawable.im2, "LEGENDARY"));
        }
        if (bundleInt==2) {
            adapter.addMessage(new Item(R.drawable.im3, "vot da EZ"));
        }

        return view;
    }

    public void viewAds() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
