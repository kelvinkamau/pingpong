package app.kelvinkamau.pingpong;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;

public class Point extends DrawObject {

    private final UIParent.PLAYER player;
    private final TextPaint textPaint;
    protected int point = 0;

    public Point(Context context, UIParent.PLAYER player) {
        super(context);
        this.player = player;

        textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(20 * getDensity());
    }

    @Override
    public void draw(Canvas canvas) {
        float w = 0;
        switch (player) {
            case ONE:
                w = getDisplayWidth() / 4;
                break;
            case TWO:
                w = getDisplayWidth() - getDisplayWidth() / 4;
                break;
        }

        float textHeight = textPaint.descent() - textPaint.ascent();
        float textOffset = (textHeight / 2) - textPaint.descent();
        canvas.drawText(String.valueOf(point), w, getDisplayHeight() / 20 + textOffset, textPaint);
    }
}

