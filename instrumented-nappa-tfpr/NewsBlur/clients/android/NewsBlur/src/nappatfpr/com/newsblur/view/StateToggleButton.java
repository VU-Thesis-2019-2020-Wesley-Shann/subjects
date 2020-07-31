package nappatfpr.com.newsblur.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

import nappatfpr.com.newsblur.R;
import nappatfpr.com.newsblur.util.StateFilter;
import nappatfpr.com.newsblur.util.UIUtils;

public class StateToggleButton extends LinearLayout {

    /** the parent width in dp under which the widget will auto-collapse to a compact form */
    private final static int COLLAPSE_WIDTH_DP = 450;

	private StateFilter state = StateFilter.SOME;

	private StateChangedListener stateChangedListener;

    private int parentWidthPX = 0;

	@BindView(R.id.toggle_all) ViewGroup allButton;
	@BindView(R.id.toggle_all_icon) View allButtonIcon;
	@BindView(R.id.toggle_all_text) View allButtonText;
	@BindView(R.id.toggle_some) ViewGroup someButton;
	@BindView(R.id.toggle_some_icon) View someButtonIcon;
	@BindView(R.id.toggle_some_text) View someButtonText;
	@BindView(R.id.toggle_focus) ViewGroup focusButton;
	@BindView(R.id.toggle_focus_icon) View focusButtonIcon;
	@BindView(R.id.toggle_focus_text) View focusButtonText;
    @BindView(R.id.toggle_saved) ViewGroup savedButton;
    @BindView(R.id.toggle_saved_icon) View savedButtonIcon;
    @BindView(R.id.toggle_saved_text) View savedButtonText;

	public StateToggleButton(Context context, AttributeSet art) {
		super(context, art);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.state_toggle, this);
        ButterKnife.bind(this, view);

        // smooth layout transitions are enabled in our layout XML; this smooths out toggle
        // transitions on newer devices
        allButton.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        someButton.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        focusButton.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        savedButton.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

		setState(state);
	}

	public void setStateListener(final StateChangedListener stateChangedListener) {
		this.stateChangedListener = stateChangedListener;
	}

	@OnClick({R.id.toggle_all, R.id.toggle_some, R.id.toggle_focus, R.id.toggle_saved})
	public void onClickToggle(View v) {
        if (v.getId() == R.id.toggle_all) {
		    setState(StateFilter.ALL);
        } else if (v.getId() == R.id.toggle_some) {
            setState(StateFilter.SOME);
        } else if (v.getId() == R.id.toggle_focus) {
            setState(StateFilter.BEST);
        } else if (v.getId() == R.id.toggle_saved) {
            setState(StateFilter.SAVED);
        }
	}

	public void setState(StateFilter state) {
        this.state = state;
        updateButtonStates();
		if (stateChangedListener != null) {
			stateChangedListener.changedState(this.state);
		}
    }

    public void setParentWidthPX(int parentWidthPX) {
        this.parentWidthPX = parentWidthPX;
        updateButtonStates();
    }

    public void updateButtonStates() {
        boolean compactMode = true;
        if (parentWidthPX > 0) {
            float widthDP = UIUtils.px2dp(getContext(), parentWidthPX);
            if (widthDP > COLLAPSE_WIDTH_DP) compactMode = false;
        }

        allButtonText.setVisibility((!compactMode || state == StateFilter.ALL) ? View.VISIBLE : View.GONE);
        allButton.setEnabled(state != StateFilter.ALL);
        allButtonIcon.setAlpha(state == StateFilter.ALL ? 1.0f : 0.6f);

        someButtonText.setVisibility((!compactMode || state == StateFilter.SOME) ? View.VISIBLE : View.GONE);
        someButton.setEnabled(state != StateFilter.SOME);
        someButtonIcon.setAlpha(state == StateFilter.SOME ? 1.0f : 0.6f);

        focusButtonText.setVisibility((!compactMode || state == StateFilter.BEST) ? View.VISIBLE : View.GONE);
        focusButton.setEnabled(state != StateFilter.BEST);
        focusButtonIcon.setAlpha(state == StateFilter.BEST ? 1.0f : 0.6f);

        savedButtonText.setVisibility((!compactMode || state == StateFilter.SAVED) ? View.VISIBLE : View.GONE);
        savedButton.setEnabled(state != StateFilter.SAVED);
        savedButtonIcon.setAlpha(state == StateFilter.SAVED ? 1.0f : 0.6f);
	}

	public interface StateChangedListener {
		public void changedState(StateFilter state);
	}

}
