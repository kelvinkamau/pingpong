package app.kelvinkamau.pingpong;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private int fps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        uiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        decorView.setSystemUiVisibility(uiOptions);

        final UIParent uiParent = new UIParent(this);
        setContentView(uiParent);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                uiParent.invalidate();
                                fps++;
                            }
                        });
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    try {
                        Thread.sleep(1000);
                        log("FPS: " + fps);
                        fps = 0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
            }
        }).start();
    }

    public static void log(String message) {
        Log.i("PingPong", message);
    }

}
