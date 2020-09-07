package com.bariskarapelit.touchcontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twilio.video.CameraCapturer.CameraSource;
import com.twilio.video.VideoView;

import com.twilio.video.CameraCapturer;
import com.twilio.video.LocalVideoTrack;
import com.twilio.video.VideoRenderer;
import com.twilio.video.VideoTextureView;

import static com.twilio.video.CameraCapturer.CameraSource.BACK_CAMERA;
import static com.twilio.video.CameraCapturer.CameraSource.FRONT_CAMERA;

public class MainActivity extends AppCompatActivity
{



    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    private VideoView localVideoView;
    private ImageView snapshotImageView;
    private TextView tapForSnapshotTextView;
    private SnapshotVideoRenderer snapshotVideoRenderer;
    private LocalVideoTrack localVideoTrack;
    ImageButton cameraChange;
    CameraSource cameraCapturer ;



    ImageButton circleButton,likeButton,dislikeButton;

    VideoTextureView videoTextureView;

    ImageView imageView ;


    int selectedImage = R.drawable.ic_circle;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = new ImageView(this);

        imageView.setImageResource(R.drawable.circlepng);
        localVideoView = findViewById(R.id.local_video);
        cameraCapturer= BACK_CAMERA;


        final FrameLayout frameLayout = findViewById(R.id.frame_layout1);
        LinearLayout linearLayout= findViewById(R.id.linearlayout);

        circleButton=findViewById(R.id.circle);
        dislikeButton=findViewById(R.id.dislike);
        likeButton=findViewById(R.id.like);
        cameraChange=findViewById(R.id.cameraChange);

        if (!checkPermissionForCamera()) {
            requestPermissionForCamera();
        } else {
            addVideo();
        }



        MultiTouchControl multiTouchControl = new MultiTouchControl(frameLayout, new MultiTouchControl.ComponentView() {
            @Override
            public View onCreateComponent() {
                ImageView image = new ImageView(frameLayout.getContext());
                image.setImageResource(selectedImage);

                return image;
            }
        });

        multiTouchControl.startListener();

        setupButtons();
        setCameraChange();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            boolean cameraPermissionGranted = true;

            for (int grantResult : grantResults) {
                cameraPermissionGranted &= grantResult == PackageManager.PERMISSION_GRANTED;
            }

            if (cameraPermissionGranted) {
                addVideo();
            } else {
                Toast.makeText(this,
                        R.string.permissions_needed,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        localVideoTrack.removeRenderer((VideoRenderer) localVideoView);
        localVideoTrack.removeRenderer(snapshotVideoRenderer);
        localVideoTrack.release();
        localVideoTrack = null;
        super.onDestroy();
    }
    private void addVideo() {
        localVideoTrack = LocalVideoTrack.create(this, true, new CameraCapturer(this,
                cameraCapturer, null));
        snapshotVideoRenderer = new SnapshotVideoRenderer(snapshotImageView);
        localVideoTrack.addRenderer((VideoRenderer) localVideoView);
        localVideoTrack.addRenderer(snapshotVideoRenderer);
        localVideoView.setOnClickListener(v -> {

        });
    }


    private void setCameraChange()
    {
        cameraChange.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (cameraCapturer== FRONT_CAMERA) {
                    Toast.makeText(MainActivity.this, "Camera Change BACK_CAMERA", Toast.LENGTH_LONG).show();
                    cameraCapturer = BACK_CAMERA;
                } else if (cameraCapturer== BACK_CAMERA)
                {
                    Toast.makeText(MainActivity.this, "Camera Change FRONT_CAMERA", Toast.LENGTH_LONG).show();
                    cameraCapturer = FRONT_CAMERA;
                }

            }
        });
    }
    private void setupButtons(){
        circleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Circle",Toast.LENGTH_LONG).show();
              selectedImage = R.drawable.ic_circle;


            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Dislike",Toast.LENGTH_LONG).show();
                selectedImage = R.drawable.ic_dislike;



            }
        });
        likeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Like",Toast.LENGTH_LONG).show();

                selectedImage = R.drawable.ic_mavilike;


            }
        });
    }

    private boolean checkPermissionForCamera(){
        int resultCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        return resultCamera == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermissionForCamera(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                CAMERA_PERMISSION_REQUEST_CODE);
    }
}