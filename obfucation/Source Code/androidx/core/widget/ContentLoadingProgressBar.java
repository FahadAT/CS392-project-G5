package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;

public class ContentLoadingProgressBar extends ProgressBar {
    private static final int MIN_DELAY_MS = 500;
    private static final int MIN_SHOW_TIME_MS = 500;
    private final Runnable mDelayedHide;
    private final Runnable mDelayedShow;
    boolean mDismissed;
    boolean mPostedHide;
    boolean mPostedShow;
    long mStartTime;

    public static /* synthetic */ void lambda$new$0(ContentLoadingProgressBar contentLoadingProgressBar) {
        contentLoadingProgressBar.mPostedHide = false;
        contentLoadingProgressBar.mStartTime = -1;
        contentLoadingProgressBar.setVisibility(8);
    }

    public static /* synthetic */ void lambda$new$1(ContentLoadingProgressBar contentLoadingProgressBar) {
        contentLoadingProgressBar.mPostedShow = false;
        if (!contentLoadingProgressBar.mDismissed) {
            contentLoadingProgressBar.mStartTime = System.currentTimeMillis();
            contentLoadingProgressBar.setVisibility(0);
        }
    }

    public ContentLoadingProgressBar(@NonNull Context context) {
        this(context, null);
    }

    public ContentLoadingProgressBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mStartTime = -1;
        this.mPostedHide = false;
        this.mPostedShow = false;
        this.mDismissed = false;
        this.mDelayedHide = new Runnable() {
            /* class androidx.core.widget.$$Lambda$ContentLoadingProgressBar$aW9csiS0dCdsR2nrqov9CuXAmGo */

            public final void run() {
                ContentLoadingProgressBar.lambda$new$0(ContentLoadingProgressBar.this);
            }
        };
        this.mDelayedShow = new Runnable() {
            /* class androidx.core.widget.$$Lambda$ContentLoadingProgressBar$o6JtaSRcipUt7wQgtZoEeLlTyXE */

            public final void run() {
                ContentLoadingProgressBar.lambda$new$1(ContentLoadingProgressBar.this);
            }
        };
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallbacks();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    private void removeCallbacks() {
        removeCallbacks(this.mDelayedHide);
        removeCallbacks(this.mDelayedShow);
    }

    public void hide() {
        post(new Runnable() {
            /* class androidx.core.widget.$$Lambda$ContentLoadingProgressBar$sKUdpe5w2n1AvcCiQWHq34vJNZg */

            public final void run() {
                ContentLoadingProgressBar.lambda$sKUdpe5w2n1AvcCiQWHq34vJNZg(ContentLoadingProgressBar.this);
            }
        });
    }

    /* access modifiers changed from: private */
    @UiThread
    public void hideOnUiThread() {
        this.mDismissed = true;
        removeCallbacks(this.mDelayedShow);
        this.mPostedShow = false;
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.mStartTime;
        long j2 = currentTimeMillis - j;
        if (j2 >= 500 || j == -1) {
            setVisibility(8);
        } else if (!this.mPostedHide) {
            postDelayed(this.mDelayedHide, 500 - j2);
            this.mPostedHide = true;
        }
    }

    public void show() {
        post(new Runnable() {
            /* class androidx.core.widget.$$Lambda$ContentLoadingProgressBar$kZvB_uNUZRE2fd9TBZnBWymih7M */

            public final void run() {
                ContentLoadingProgressBar.lambda$kZvB_uNUZRE2fd9TBZnBWymih7M(ContentLoadingProgressBar.this);
            }
        });
    }

    /* access modifiers changed from: private */
    @UiThread
    public void showOnUiThread() {
        this.mStartTime = -1;
        this.mDismissed = false;
        removeCallbacks(this.mDelayedHide);
        this.mPostedHide = false;
        if (!this.mPostedShow) {
            postDelayed(this.mDelayedShow, 500);
            this.mPostedShow = true;
        }
    }
}
