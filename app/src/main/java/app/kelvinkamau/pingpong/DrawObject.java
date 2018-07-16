package app.kelvinkamau.pingpong;

import android.content.Context;
import android.graphics.Canvas;
public abstract class DrawObject {

    private final float displayWidth;
    private final float displayHeight;
    private final float density;

    public DrawObject(Context context) {
        displayWidth = context.getResources().getDisplayMetrics().widthPixels;
        displayHeight = context.getResources().getDisplayMetrics().heightPixels;
        density = context.getResources().getDisplayMetrics().density;
    }

    public abstract void draw(Canvas canvas);

    protected float getDisplayWidth() {
        return displayWidth;
    }

    protected float getDisplayHeight() {
        return displayHeight;
    }

    protected float getDensity() {
        return density;
    }

}
