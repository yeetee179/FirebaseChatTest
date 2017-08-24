package y.t.x.firebasechattest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText resetPasswordEmail;
    private Button resetPasswordBtn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        resetPasswordEmail = (EditText) findViewById(R.id.reset_password_email_field);
        resetPasswordBtn = (Button) findViewById(R.id.reset_password_email_button);

        auth = FirebaseAuth.getInstance();

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = resetPasswordEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(ResetPasswordActivity.this, "enter registerd email id", Toast.LENGTH_LONG).show();
                }

                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ResetPasswordActivity.this, "password reset email is sent", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ResetPasswordActivity.this, "failed to send password reset email", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
