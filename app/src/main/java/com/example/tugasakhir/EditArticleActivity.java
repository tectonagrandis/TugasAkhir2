package com.example.tugasakhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditArticleActivity extends AppCompatActivity {

    private TextInputEditText articleNameEdt, articleDescEdt, articlePriceEdt, bestSuitedEdt, articleImgEdt, articleLinkEdt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Modal modal;
    private ProgressBar loadingPB;
    private String articleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);
        Button addCourseBtn = findViewById(R.id.idBtnAddArticle);
        articleNameEdt = findViewById(R.id.idEdtArticleName);
        articleDescEdt = findViewById(R.id.idEdtArticleDescription);
        articlePriceEdt = findViewById(R.id.idEdtArticlePrice);
        bestSuitedEdt = findViewById(R.id.idEdtSuitedFor);
        articleImgEdt = findViewById(R.id.idEdtArticleImageLink);
        articleLinkEdt = findViewById(R.id.idEdtArticleLink);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        modal = getIntent().getParcelableExtra("course");
        Button deleteCourseBtn = findViewById(R.id.idBtnDeleteArticle);

        if (modal != null) {
            articleNameEdt.setText(modal.getArticleName());
            articlePriceEdt.setText(modal.getArticlePrice());
            bestSuitedEdt.setText(modal.getBestSuitedFor());
            articleImgEdt.setText(modal.getArticleImg());
            articleLinkEdt.setText(modal.getArticleLink());
            articleDescEdt.setText(modal.getArticleDescription());
            articleID = modal.getArticleId();
        }

        databaseReference = firebaseDatabase.getReference("Courses").child(articleID);
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
                Map<String, Object> map = new HashMap<>();
                map.put("articleName", articleName);
                map.put("courseDescription", articleDesc);
                map.put("articlePrice", articlePrice);
                map.put("bestSuitedFor", bestSuited);
                map.put("articleImg", articleImg);
                map.put("articleLink", articleLink);
                map.put("articleId", articleID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditArticleActivity.this, "Article Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditArticleActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditArticleActivity.this, "Fail to update article..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourse();
            }
        });

    }

    private void deleteCourse() {
        databaseReference.removeValue();
        Toast.makeText(this, "Article Deleted..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditArticleActivity.this, MainActivity.class));
    }
}