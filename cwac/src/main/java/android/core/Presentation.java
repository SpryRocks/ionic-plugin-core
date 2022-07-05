package android.core;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

public class Presentation extends android.app.Presentation {
    public Presentation(Context outerContext, Display display, int theme) {
        super(outerContext, display, theme);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            getWindow().setType(getWindowType());
        }
    }

    /**
     * Returns the window type to use. The default implementation will use:
     *
     * - TYPE_TOAST on Android 7.0 and lower
     * - TYPE_SYSTEM_ALERT on Android 7.1
     * - TYPE_APPLICATION_OVERLAY on Android 8.0+
     *
     * If you are using Cast Remote Display, override this and return
     * TYPE_PRIVATE_PRESENTATION (note: untested).
     *
     * @return a window type (e.g., TYPE_TOAST)
     */
    @TargetApi(Build.VERSION_CODES.O)
    private int getWindowType() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            return WindowManager.LayoutParams.TYPE_TOAST;
        }
        else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

        return WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
    }
}
