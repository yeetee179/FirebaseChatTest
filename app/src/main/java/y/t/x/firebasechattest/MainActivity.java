package y.t.x.firebasechattest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button signOutBtn, deleteBtn;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signOutBtn = (Button) findViewById(R.id.sign_out_button);
        deleteBtn = (Button) findViewById(R.id.delete_account_button);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        signOutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                auth.signOut();
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }
}
