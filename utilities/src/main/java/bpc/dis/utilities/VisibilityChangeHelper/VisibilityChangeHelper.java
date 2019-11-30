package bpc.dis.utilities.VisibilityChangeHelper;

import android.view.View;

public class VisibilityChangeHelper {

    private int visibilityDuration = 500;

    public void changeVisibility(View view, int visibility) {
        switch (visibility) {
            case View.GONE:
                view.setAlpha(0.0f);
                view.setVisibility(View.GONE);
                break;
            case View.INVISIBLE:
                view.setAlpha(0.0f);
                view.setVisibility(View.INVISIBLE);
                break;
            case View.VISIBLE:
                view.setVisibility(View.VISIBLE);
                view.animate()
                        .alpha(1.0f)
                        .setDuration(visibilityDuration).start();
                break;
        }
    }

    public void setVisibilityDuration(int visibilityDuration) {
        this.visibilityDuration = visibilityDuration;
    }

}