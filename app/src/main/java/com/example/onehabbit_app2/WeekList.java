package com.example.onehabbit_app2;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import android.content.Intent;
import android.os.Bundle;

public class WeekList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_list);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn1) {
            Snackbar.make(view, "月曜日が押されました", Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn2) {
            Snackbar.make(view, "火曜日が押されました", Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn3) {
            Snackbar.make(view, "水曜日が押されました", Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn4) {
            Snackbar.make(view, "木曜日が押されました", Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn5) {
            Snackbar.make(view, "金曜日が押されました", Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn6) {
            Snackbar.make(view, "土曜日が押されました", Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn7) {
            Snackbar.make(view, "日曜日が押されました", Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        }
    }
}