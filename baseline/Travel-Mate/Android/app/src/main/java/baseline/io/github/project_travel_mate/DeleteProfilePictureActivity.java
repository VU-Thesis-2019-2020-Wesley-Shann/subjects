package baseline.io.github.project_travel_mate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteProfilePictureActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("WESLEY_GRAPH_ACTIVITY", this.getClass().getSimpleName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("remove_image", true);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
