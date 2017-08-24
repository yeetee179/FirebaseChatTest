package y.t.x.firebasechattest;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText newEmail, newPassword;
    private Button signOutBtn, deleteBtn, newEmailBtn, newPasswordBtn;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newEmail = (EditText) findViewById(R.id.new_email_field);
        newPassword = (EditText) findViewById(R.id.new_password_field);
        signOutBtn = (Button) findViewById(R.id.sign_out_button);
        deleteBtn = (Button) findViewById(R.id.delete_account_button);
        newEmailBtn = (Button) findViewById(R.id.new_email_button);
        newPasswordBtn = (Button) findViewById(R.id.new_password_button);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //reset email
        newEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                user.updateEmail(newEmail.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Email address updated",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Email address update failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //reset password
        newPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                user.updatePassword(newPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Password updated",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Password update failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //signout
        signOutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                auth.signOut();
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        //delete account
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                if (user != null){
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Your Profile has been deleted",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Your Profile has been deleted",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }
}
