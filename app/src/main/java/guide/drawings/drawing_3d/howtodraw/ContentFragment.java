package guide.drawings.drawing_3d.howtodraw;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {
    int bundleInt;
    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private TwoImageAdapter adapter;
    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        verticalLinearLayoutManager = new LinearLayoutManager(getActivity());
        verticalLinearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        Bundle bundle = getArguments();
        if (bundle != null) {
            bundleInt = bundle.getInt("bundle_int");
        }



        adapter = new TwoImageAdapter(width);
        recyclerView.setAdapter(adapter);
        if (bundleInt==0) {
            adapter.addMessage(new Item(R.drawable.im1));
            adapter.addMessage(new Item(R.drawable.im2));
            adapter.addMessage(new Item(R.drawable.im3));
        }
        if (bundleInt==1) {
            adapter.addMessage(new Item(R.drawable.im1));
            adapter.addMessage(new Item(R.drawable.im3));
            adapter.addMessage(new Item(R.drawable.im3));
        }

        return view;
    }

}
