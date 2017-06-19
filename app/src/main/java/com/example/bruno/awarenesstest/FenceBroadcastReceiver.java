package com.example.bruno.awarenesstest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.awareness.fence.FenceState;


/**
 * Created by Artur on 14/06/2017.
 */
public class FenceBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "FenceBroadcastReceiver";
    boolean onDriving = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        FenceState fenceState = FenceState.extract(intent);

        Log.d(TAG, "Received a Fence Broadcast");

        if (TextUtils.equals(fenceState.getFenceKey(), MapsActivity.DRIVING_FENCE_KEY)) {
            switch (fenceState.getCurrentState()) {
                case FenceState.TRUE:
                    Log.i(TAG, "Received a FenceUpdate -  Driving mode detected.");
                    Toast.makeText(context, "Your driving mode detected.",
                            Toast.LENGTH_LONG).show();
                    onDriving = true;
                    break;
                case FenceState.FALSE:
                    Log.i(TAG, "Received a FenceUpdate -  Driving mode are not detected.");
                    Toast.makeText(context, "Your headphones are NOT plugged in",
                            Toast.LENGTH_LONG).show();
                    onDriving = false;
                    break;
                case FenceState.UNKNOWN:
                    Log.i(TAG, "Received a FenceUpdate -  The driving fence is in an unknown state.");
                    onDriving = false;
                    break;
            }
        }

    }

    public boolean getOnDriving() {
        return this.onDriving;
    }
}
