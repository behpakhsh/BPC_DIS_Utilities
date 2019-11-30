package bpc.dis.utilities.PointHelper;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

public class PointHelper {

    public Point getBottomAndLeftPointOfView(ViewGroup parentViewGroup, View view) {
        Rect offsetViewBounds = new Rect();
        view.getDrawingRect(offsetViewBounds);
        parentViewGroup.offsetDescendantRectToMyCoords(view, offsetViewBounds);
        int relativeBottom = offsetViewBounds.bottom;
        int relativeLeft = offsetViewBounds.left;
        return new Point(relativeLeft, relativeBottom);
    }

    public Point getTopAndLeftPointOfView(ViewGroup parentViewGroup, View view) {
        Rect offsetViewBounds = new Rect();
        view.getDrawingRect(offsetViewBounds);
        parentViewGroup.offsetDescendantRectToMyCoords(view, offsetViewBounds);
        int relativeTop = offsetViewBounds.top;
        int relativeLeft = offsetViewBounds.left;
        return new Point(relativeLeft, relativeTop);
    }

    public Point getTopAndRightPointOfView(ViewGroup parentViewGroup, View view) {
        Rect offsetViewBounds = new Rect();
        view.getDrawingRect(offsetViewBounds);
        parentViewGroup.offsetDescendantRectToMyCoords(view, offsetViewBounds);
        int relativeTop = offsetViewBounds.top;
        int relativeRight = offsetViewBounds.right;
        return new Point(relativeRight, relativeTop);
    }

    public Point getLocationInWindowPointOfView(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return new Point(location[0], location[1]);
    }

}
