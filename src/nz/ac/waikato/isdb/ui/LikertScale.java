package nz.ac.waikato.isdb.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import nz.ac.waikato.isdb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * UI element representing a Likert scale from 1 to 5.
 */
public class LikertScale extends LinearLayout implements View.OnClickListener {
    /** return value if no button has been pressed */
    static int NO_SELECTION = -1;

    /** all toggle buttons of this Likert scale */
    List<ToggleButton> buttons;

    public LikertScale(Context context) {
        super(context);
        init();
    }

    public LikertScale(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LikertScale(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    protected void init() {
        Context ctxt = getContext();
        if (ctxt == null)
            return;

        buttons = new ArrayList<ToggleButton>();

        for (int i = 1; i <= 5; i++) {
            String label = Integer.toString(i);
            ToggleButton button = new ToggleButton(ctxt);
            button.setText(label);
            button.setTextOn(label);
            button.setTextOff(label);
            button.setBackgroundResource(R.drawable.purple_likert_button);
            button.setOnClickListener(this);
            addView(button);
            buttons.add(button);
        }
    }

    /**
     * Get the selected value. If none has been selected then -1 is returned.
     *
     * @return selected value or -1
     */
    public int getValue() {
        ToggleButton checkedButton = getCheckedButton();
        return checkedButton == null ? NO_SELECTION : Integer.parseInt(checkedButton.getText().toString());
    }

    @Override
    public void onClick(View view) {
        ToggleButton clickedButton = (ToggleButton) view;
        for (ToggleButton button : buttons) {
            if (button.equals(clickedButton))
                continue;
            button.setChecked(false);
        }
    }

    /**
     * Gets the currently checked button. If none has been checked then null is return.
     *
     * @return checked button or null
     */
    protected ToggleButton getCheckedButton() {
        for (ToggleButton button : buttons) {
            if (button.isChecked())
                return button;
        }
        return null;
    }
}
