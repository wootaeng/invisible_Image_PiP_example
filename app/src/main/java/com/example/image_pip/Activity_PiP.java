package com.example.image_pip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.os.Build;
import android.os.Bundle;
import android.util.Rational;
import android.view.View;
import android.widget.ImageView;

public class Activity_PiP extends AppCompatActivity {

    private ImageView imageView;
    private boolean minimize;
    private final Object pictureInPictureParamsBuilder;
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pictureInPictureParamsBuilder = new PictureInPictureParams.Builder();
        } else {
            pictureInPictureParamsBuilder = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip);

        imageView = findViewById(R.id.ImgV);

        View actionPIP = findViewById(R.id.actionPIP);
        actionPIP.setVisibility(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? View.VISIBLE : View.GONE);
        actionPIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minimize();
            }
        });
    }



    private void minimize() {
        if (imageView == null){
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (pictureInPictureParamsBuilder instanceof PictureInPictureParams.Builder) {
                Rational aspectRatio = new Rational(2,1);
                PictureInPictureParams build = ((PictureInPictureParams.Builder) pictureInPictureParamsBuilder).setAspectRatio(aspectRatio).build();
                minimize = true;
                enterPictureInPictureMode(build);
            }
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        if (isInPictureInPictureMode) {

        } else {
            minimize = false;
        }
    }

    @Override
    public void onUserLeaveHint () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (pictureInPictureParamsBuilder instanceof PictureInPictureParams.Builder) {
                Rational aspectRatio = new Rational(1,1);
                PictureInPictureParams build = ((PictureInPictureParams.Builder) pictureInPictureParamsBuilder).setAspectRatio(aspectRatio).build();
                minimize = true;
                enterPictureInPictureMode(build);
            }
        }
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//    }
}