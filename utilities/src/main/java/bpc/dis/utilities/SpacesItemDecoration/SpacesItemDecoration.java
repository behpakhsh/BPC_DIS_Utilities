package bpc.dis.utilities.SpacesItemDecoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        boolean isLast = position == state.getItemCount() - 1;
        if (isLast) {
            outRect.bottom = space;
            outRect.top = 0;
        }
        if (position == 0) {
            outRect.top = space;
            if (!isLast) {
                outRect.bottom = 0;
            }
        }
    }

}