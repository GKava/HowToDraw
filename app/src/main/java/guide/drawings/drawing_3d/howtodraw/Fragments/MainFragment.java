package guide.drawings.drawing_3d.howtodraw.Fragments;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import guide.drawings.drawing_3d.howtodraw.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener{
ImageView creative_mode;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        creative_mode = view.findViewById(R.id.creative_mode);
        creative_mode.setOnClickListener(this);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        double heingt_double =width*0.625;
        int height_int = (int) heingt_double;

        creative_mode.getLayoutParams().height = height_int;
                creative_mode.requestLayout();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.creative_mode:
                Fragment fr = new CreativeModeCategories();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fr)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
