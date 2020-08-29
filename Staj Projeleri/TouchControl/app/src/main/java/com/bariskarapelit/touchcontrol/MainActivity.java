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
import android.widget.Toast;
import android.widget.VideoView;

import com.twilio.video.VideoTextureView;

public class MainActivity extends AppCompatActivity
{
    ImageButton circleButton,likeButton,dislikeButton;
    VideoView videoView;
    String videoPath;
    VideoTextureView videoTextureView;
    Uri uri;
    ImageView imageView ;


    int selectedImage = R.drawable.circlepng;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = new ImageView(this);






        //Burası defult resim. Butonlara tıklamadğında ilk olarak varsayılan olarka koaycağınız resim olacak.
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