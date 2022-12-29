package com.example.tugasakhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddArticleActivity extends AppCompatActivity {

    private Button addCourseBtn;
    private TextInputEditText articleNameEdt, articleDescEdt, articlePriceEdt, bestSuitedEdt, articleImgEdt, articleLinkEdt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ProgressBar loadingPB;
    private String articleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        addCourseBtn = findViewById(R.id.idBtnAddArticle);
        articleNameEdt = findViewById(R.id.idEdtArticleName);
        articleDescEdt = findViewById(R.id.idEdtArticleDescription);
        articlePriceEdt = findViewById(R.id.idEdtArticlePrice);
        bestSuitedEdt = findViewById(R.id.idEdtSuitedFor);
        articleImgEdt = findViewById(R.id.idEdtArticleImageLink);
        articleLinkEdt = findViewById(R.id.idEdtArticleLink);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Article");
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                String articleName = articleNameEdt.getText().toString();
                String articleDesc = articleDescEdt.getText().toString();
                String articlePrice = articlePriceEdt.getText().toString();
                String bestSuited = bestSuitedEdt.getText().toString();
                String articleImg = articleImgEdt.getText().toString();
                String articleLink = articleLinkEdt.getText().toString();
                articleID = articleName;
                Modal modal = new Modal(articleID, articleName, articleDesc, articlePrice, bestSuited, articleImg, articleLink);
                databaseReference.child(articleID).setValue(modal).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddArticleActivity.this, "Article successly added!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddArticleActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

//                databaseReference.addValueEventListener(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NonNull DataSnapshot snapshot) {
////                        Toast.makeText(AddArticleActivity.this, "Article Added..", Toast.LENGTH_SHORT).show();
////                        startActivity(new Intent(AddArticleActivity.this, MainActivity.class));
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError error) {
////                        Toast.makeText(AddArticleActivity.this, "Fail to add Article..", Toast.LENGTH_SHORT).show();
////                    }
//                });
            }
        });
    }
}