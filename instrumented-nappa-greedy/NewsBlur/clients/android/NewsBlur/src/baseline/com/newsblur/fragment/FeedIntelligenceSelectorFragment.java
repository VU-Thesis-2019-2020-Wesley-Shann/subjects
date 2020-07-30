package baseline.com.newsblur.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewGroup;

import baseline.com.newsblur.R;
import baseline.com.newsblur.view.StateToggleButton;
import baseline.com.newsblur.view.StateToggleButton.StateChangedListener;
import baseline.com.newsblur.util.StateFilter;

public class FeedIntelligenceSelectorFragment extends Fragment implements StateChangedListener {
    
    private StateToggleButton button;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_intelligenceselector, null);
        button = (StateToggleButton) v.findViewById(R.id.fragment_intelligence_statebutton);
        button.setStateListener(this);
        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                button.setParentWidthPX(v.getWidth());
            }
        });
                
        return v;
    }

    @Override
    public void changedState(StateFilter state) {
        ((StateChangedListener) getActivity()).changedState(state);
    }
    
    public void setState(StateFilter state) {
        button.setState(state);
    }

}
