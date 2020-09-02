package com.bariskarapelit.touchcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.twilio.video.CameraCapturer;
import com.twilio.video.LocalVideoTrack;
import com.twilio.video.VideoTextureView;

public class MainActivity extends AppCompatActivity
{



    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    private VideoTextureView localVideoView;
    private ImageView snapshotImageView;
    private TextView tapForSnapshotTextView;
    private SnapshotVideoRenderer snapshotVideoRenderer;
    private LocalVideoTrack localVideoTrack;



    ImageButton circleButton,likeButton,dislikeButton;

    VideoTextureView videoTextureView;

    ImageView imageView ;


    int selectedImage = R.drawable.circlepng;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = new ImageView(this);







        imageView.setImageResource(R.drawable.circlepng);



        final FrameLayout frameLayout = findViewById(R.id.frame_layout);
        LinearLayout linearLayout= findViewById(R.id.linearlayout);

        circleButton=findViewById(R.id.circle);
        dislikeButton=findViewById(R.id.dislike);
        likeButton=findViewById(R.id.like);

        videoTextureView=findViewById(R.id.video_view_top_right);




        //Burdaki hangi viewi dinleyeceği hocam.
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
    }
    private void addVideo() {
        localVideoTrack = LocalVideoTrack.create(this, true, new CameraCapturer(this,
                CameraCapturer.CameraSource.FRONT_CAMERA, null));
        snapshotVideoRenderer = new SnapshotVideoRenderer(snapshotImageView);
        localVideoTrack.addRenderer(localVideoView);
        localVideoTrack.addRenderer(snapshotVideoRenderer);
        localVideoView.setOnClickListener(v -> {
            tapForSnapshotTextView.setVisibility(View.GONE);
            snapshotVideoRenderer.takeSnapshot();
        });
    }

    private void setupButtons(){
        circleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Circle",Toast.LENGTH_LONG).show();
              selectedImage = R.drawable.circlepng;


            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Dislike",Toast.LENGTH_LONG).show();
                selectedImage = R.drawable.dislike;



            }
        });
        likeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Like",Toast.LENGTH_LONG).show();

                selectedImage = R.drawable.like;


            }
        });
    }


}