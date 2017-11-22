package com.syzible.boop.fragments.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Facing;
import com.syzible.boop.R;
import com.syzible.boop.activities.MainActivity;

/**
 * Created by ed on 22/11/2017.
 */

public class CallFragment extends Fragment {

    private CameraView cameraView;
    private boolean isFront = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, container, false);

        FloatingActionButton endCallButton = view.findViewById(R.id.end_call);
        endCallButton.setOnClickListener(v -> MainActivity.setFragment(getFragmentManager(), new ContactsFragment()));

        FloatingActionButton microphoneButton = view.findViewById(R.id.toggle_microphone);
        microphoneButton.setOnClickListener(v ->  {

        });

        FloatingActionButton cameraButton = view.findViewById(R.id.toggle_video);
        cameraButton.setOnClickListener(v -> {
            isFront = !isFront;
            cameraView.setFacing(isFront ? Facing.FRONT : Facing.BACK);
        });

        cameraButton.setOnLongClickListener(videoViewButton -> {
            isFront = !isFront;
            cameraView.setFacing(isFront ? Facing.FRONT : Facing.BACK);
            return false;
        });

        cameraView = view.findViewById(R.id.stream_holder);
        cameraView.setFacing(Facing.FRONT);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        cameraView.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cameraView.destroy();
    }
}
