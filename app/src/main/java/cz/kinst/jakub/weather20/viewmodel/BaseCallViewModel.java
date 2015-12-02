package cz.kinst.jakub.weather20.viewmodel;

import java.util.HashMap;
import java.util.Map;

import cz.kinst.jakub.viewmodelbinding.BaseViewModel;
import retrofit.Call;
import retrofit.Callback;

/**
 * Created by jakubkinst on 02/12/15.
 */
public abstract class BaseCallViewModel<S> extends BaseViewModel<S> {

    private Map<String, Call> mCalls = new HashMap<>();

    protected <T> void enqueueCall(String callId, Call<T> call, Callback<T> callback) {
        Call existingCall = mCalls.get(callId);
        if (existingCall != null)
            existingCall.cancel();
        call.enqueue(callback);
        mCalls.put(callId, call);
    }

    protected void clearCalls() {
        for (String callId : mCalls.keySet()) {
            mCalls.get(callId).cancel();
        }
    }

    @Override
    public void onModelRemoved() {
        super.onModelRemoved();
        clearCalls();
    }
}
