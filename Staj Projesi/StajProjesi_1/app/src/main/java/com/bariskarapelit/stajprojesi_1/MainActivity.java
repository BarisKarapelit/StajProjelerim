package com.bariskarapelit.stajprojesi_1;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.twilio.video.CameraCapturer;
import com.twilio.video.LocalVideoTrack;
import com.twilio.video.VideoTextureView;

public class MainActivity extends AppCompatActivity
{
    ImageButton button,button1,button2;
    GridLayout gridLayout;
    ImageView circle,dislike,like;
    VideoView videoView;
    String videoPath;
    VideoTextureView  videoTextureView;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= findViewById(R.id.circle);
        button1=findViewById(R.id.dislike);
        button2=findViewById(R.id.like);
       // videoView=findViewById(R.id.vi)

        circle=findViewById(R.id.imageView);
        dislike=findViewById(R.id.imageView2);
        like=findViewById(R.id.imageView3);

        circle.setVisibility(View.INVISIBLE);
        dislike.setVisibility(View.INVISIBLE);
        like.setVisibility(View.INVISIBLE);
        CameraCapturer cameraCapturer = new CameraCapturer(MainActivity.this, CameraCapturer.CameraSource.FRONT_CAMERA);
        LocalVideoTrack localVideoTrack = LocalVideoTrack.create(MainActivity.this, true, cameraCapturer);
        videoView=findViewById(R.id.video_view_top_right);








        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Circle",Toast.LENGTH_LONG).show();
            }
        });
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Dislike",Toast.LENGTH_LONG).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Like",Toast.LENGTH_LONG).show();
                findViewById(R.id.imageView);

            }
        });





    }
}