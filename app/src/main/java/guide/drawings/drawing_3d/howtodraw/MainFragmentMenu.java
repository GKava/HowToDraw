package guide.drawings.drawing_3d.howtodraw;


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



/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragmentMenu extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private TwoImageAdapter adapter;

    public MainFragmentMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_fragment_menu, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        verticalLinearLayoutManager = new GridLayoutManager(getActivity(),2);
        verticalLinearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/2;

        adapter = new TwoImageAdapter(width);
        recyclerView.setAdapter(adapter);
        adapter.addMessage(new Item(R.drawable.im1));
        adapter.addMessage(new Item(R.drawable.im2));
        adapter.addMessage(new Item(R.drawable.im3));
        adapter.addMessage(new Item(R.drawable.im1));
        adapter.addMessage(new Item(R.drawable.im1));
        adapter.addMessage(new Item(R.drawable.im1));
        adapter.addMessage(new Item(R.drawable.im1));
        return view;
    }

}
