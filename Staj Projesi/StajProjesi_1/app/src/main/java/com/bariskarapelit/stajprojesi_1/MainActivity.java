package com.bariskarapelit.stajprojesi_1;

import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import com.twilio.video.CameraCapturer;
import com.twilio.video.LocalVideoTrack;
import com.twilio.video.VideoTextureView;

import pl.droidsonroids.gif.GifImageView;
import android.view.MotionEvent;
import static androidx.core.view.ViewCompat.getX;

public class MainActivity extends AppCompatActivity
{
    ImageButton button,button1,button2;
    GridLayout gridLayout;
    ImageView circle,dislike,like;
    VideoView videoView;
    String videoPath;
    VideoTextureView  videoTextureView;
    Uri uri;
    GifImageView gifImageView;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= findViewById(R.id.circle);
        button1=findViewById(R.id.dislike);
        button2=findViewById(R.id.like);




        circle=findViewById(R.id.imageView);
        dislike=findViewById(R.id.imageView2);
        like=findViewById(R.id.imageView3);

        gifImageView= findViewById(R.id.gift);

        circle.setVisibility(View.INVISIBLE);
        dislike.setVisibility(View.INVISIBLE);
        like.setVisibility(View.INVISIBLE);

        //videoView=findViewById(R.id.video_view_top_right);
        //Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);
        //videoView.setVideoURI(uri);

        







        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Circle",Toast.LENGTH_LONG).show();

                gifImageView.setImageResource(R.drawable.daire);
            }
        });
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Dislike",Toast.LENGTH_LONG).show();

                gifImageView.setImageResource(R.drawable.dislike);

            }
        });
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"Like",Toast.LENGTH_LONG).show();

                gifImageView.setImageResource(R.drawable.like);

            }
        });





    }
}