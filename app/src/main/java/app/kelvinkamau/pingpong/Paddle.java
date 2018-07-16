package app.kelvinkamau.pingpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Paddle extends DrawObject {

    private final UIParent.PLAYER player;
    private final Paint paint;

    private Float height;
    private float length;
    private float thickness;

    public Paddle(Context context, UIParent.PLAYER player) {
        super(context);
        this.player = player;

        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    @Override
    public void draw(Canvas canvas) {
        if (height == null) height = getDisplayHeight() / 2;
        length = getDisplayHeight() / 4;
        thickness = getDisplayWidth() / 80;

        float w = 0;
        if (height > getDisplayHeight() / 2) {
            if (height + length / 2 > getDisplayHeight()) height = getDisplayHeight() - length / 2;
        } else {
            if (height - length / 2 < 0) height = length / 2;
        }
        float h = height - length / 2;
        switch (player) {
            case ONE:
                w = getDisplayWidth() / 13;
                break;
            case TWO:
                w = getDisplayWidth() - getDisplayWidth() / 13 - thickness;
                break;
        }

        canvas.drawRect(w, h, thickness + w, length + h, paint);
    }

    public void move(float height) {
        this.height = height;
    }

    public void reset() {
        height = null;
    }

    public float getHeight() {
        return height - length / 2;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return thickness + getDisplayWidth() / 13;
    }

    public float getThickness() {
        return thickness;
    }

}

