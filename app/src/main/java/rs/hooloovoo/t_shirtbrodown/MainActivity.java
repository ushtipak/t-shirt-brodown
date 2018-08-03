package rs.hooloovoo.t_shirtbrodown;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.hooloovoo.t_shirtbrodown.api.ApiService;
import rs.hooloovoo.t_shirtbrodown.api.ApiUtils;
import rs.hooloovoo.t_shirtbrodown.api.Vote;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    Button btnBlue;
    Button btnYellow;
    Button btnCountdown;
    ApiService apiService;
    String authorization = "x-tshirtbrodown-auth-token1:a2408868-3f0a-45b2-ad4b-25652899a9b2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Log.d(TAG, "-> " + methodName);

        setContentView(R.layout.activity_main);
        hideSystemUI();
        initViews();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Log.d(TAG, "-> " + methodName);

        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Log.d(TAG, "-> " + methodName);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void initViews() {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Log.d(TAG, "-> " + methodName);

        btnBlue = findViewById(R.id.btnBlue);
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "go team blue :)");
                voteForColor("blue");
            }
        });

        btnYellow = findViewById(R.id.btnYellow);
        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "we all live in a yellow submarine :)");
                voteForColor("yellow");
            }
        });

        btnCountdown = findViewById(R.id.btnCountdown);
        apiService = ApiUtils.getAPIService();
    }

    public void voteForColor(String color) {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Log.d(TAG, "-> " + methodName);

        pauseVote();
        String authHeader = "Basic " + Base64.encodeToString(authorization.getBytes(), Base64.NO_WRAP);
        apiService.voteForColor(color, authHeader).enqueue(new Callback<Vote>() {
            @Override
            public void onResponse(@NonNull Call<Vote> call, @NonNull Response<Vote> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + Objects.requireNonNull(response.body()).toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Vote> call, @NonNull Throwable throwable) {
                Log.d(TAG, "onFailure: " + throwable);
            }
        });
        resumeVote();
    }

    private void pauseVote() {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Log.d(TAG, "-> " + methodName);

        btnBlue.setEnabled(false);
        btnYellow.setEnabled(false);
    }

    private void resumeVote() {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Log.d(TAG, "-> " + methodName);

        btnCountdown.setVisibility(View.VISIBLE);
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                btnCountdown.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                btnCountdown.setVisibility(View.INVISIBLE);
                btnBlue.setEnabled(true);
                btnYellow.setEnabled(true);
            }
        }.start();
    }

}
