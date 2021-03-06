package firstlaunchapptutorial.abhishek.balani.firstlaunchapptutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Abhishek Balani www.oddblogger.com on 11/29/2014.
 */
public class TutorialPage4  extends Fragment {

    static TutorialPage4 init(int val) {
        TutorialPage4 truitonFrag = new TutorialPage4();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonFrag.setArguments(args);
        return truitonFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.tutorial_page_4, container, false);
        return rootView;
    }
}
