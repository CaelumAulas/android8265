package br.com.caelum.casadocodigo.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.caelum.casadocodigo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_email) TextView campoEmail;
    @BindView(R.id.login_senha) TextView campoSenha;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.login_novo)
    public void novoUsuario() {
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();
        //cadastrar
        mAuth.createUserWithEmailAndPassword(email, senha)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Usuário criado!", Toast.LENGTH_LONG).show();
                    logaUsuario();
                } else {
                    //mostrar toast de erro
                    Toast.makeText(LoginActivity.this, "erro: "+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    Log.i("LOGIN", "erro ao logar", task.getException());
                }
            }
        });
    }

    @OnClick(R.id.login_logar)
    public void entraComUsuario() {
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Usuário logado!", Toast.LENGTH_LONG).show();
                            logaUsuario();
                        } else {
                            //mostrar toast de erro
                            Toast.makeText(LoginActivity.this, "erro: "+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            Log.i("LOGIN", "erro ao logar", task.getException());
                        }
                    }
                });
    }

    private void logaUsuario() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
