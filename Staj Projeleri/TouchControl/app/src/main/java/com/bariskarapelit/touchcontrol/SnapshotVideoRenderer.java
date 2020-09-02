package com.bariskarapelit.touchcontrol;

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.twilio.video.I420Frame;
import com.twilio.video.VideoRenderer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SnapshotVideoRenderer implements VideoRenderer
{
    private final ImageView imageView;
    private final AtomicBoolean snapshotRequsted = new AtomicBoolean(false);
    private final Handler handler = new Handler(Looper.getMainLooper());
    public SnapshotVideoRenderer(ImageView imageView) {
        this.imageView = imageView;
    }
    public void takeSnapshot() {
        snapshotRequsted.set(true);
    }

    @Override
    public void renderFrame(@NonNull I420Frame frame) {

    }
}
