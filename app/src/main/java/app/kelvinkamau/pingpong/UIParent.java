package app.kelvinkamau.pingpong;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class UIParent extends View {

    public enum PLAYER {
        ONE, TWO
    }

    private final Point pointOne;
    private final Point pointTwo;
    private final Ball ball;
    private final Paddle paddleOne;
    private final Paddle paddleTwo;

    public UIParent(Context context) {
        this(context, null);
    }

    public UIParent(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIParent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setBackgroundColor(getResources().getColor(android.R.color.black));

        pointOne = new Point(context, PLAYER.ONE);
        pointTwo = new Point(context, PLAYER.TWO);
        paddleOne = new Paddle(context, PLAYER.ONE);
        paddleTwo = new Paddle(context, PLAYER.TWO);
        ball = new Ball(context, paddleOne, paddleTwo, new Ball.OnPointListener() {
            @Override
            public void playerOne() {
                pointOne.point++;
                reset();
            }

            @Override
            public void playerTwo() {
                pointTwo.point++;
                reset();
            }

            private void reset() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            ball.reset();
                            paddleOne.reset();
                            paddleTwo.reset();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        pointOne.draw(canvas);
        pointTwo.draw(canvas);
        paddleOne.draw(canvas);
        paddleTwo.draw(canvas);
        ball.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            int num = event.getPointerCount();
            for (int a = 0; a < num; a++) {
                int x = (int) event.getX(event.getPointerId(a));
                int y = (int) event.getY(event.getPointerId(a));
                if (x < getMeasuredWidth() / 2) paddleOne.move(y);
                else paddleTwo.move(y);
            }
        } catch (Exception ignored) {
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int desiredWidth = getResources().getDisplayMetrics().widthPixels;
        int desiredHeight = getResources().getDisplayMetrics().heightPixels;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) width = widthSize;
        else if (widthMode == MeasureSpec.AT_MOST) width = Math.min(desiredWidth, widthSize);
        else width = desiredWidth;

        if (heightMode == MeasureSpec.EXACTLY) height = heightSize;
        else if (heightMode == MeasureSpec.AT_MOST) height = Math.min(desiredHeight, heightSize);
        else height = desiredHeight;

        setMeasuredDimension(width, height);
    }

}

