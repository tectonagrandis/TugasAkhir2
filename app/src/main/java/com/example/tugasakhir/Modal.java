package com.example.tugasakhir;

import android.os.Parcel;
import android.os.Parcelable;

public class Modal implements Parcelable {
    private String articleName;
    private String articleDescription;
    private String articlePrice;
    private String bestSuitedFor;
    private String articleImg;
    private String articleLink;
    private String articleId;


    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }


    public Modal() {

    }

    protected Modal(Parcel in) {
        articleName = in.readString();
        articleId = in.readString();
        articleDescription = in.readString();
        articlePrice = in.readString();
        bestSuitedFor = in.readString();
        articleImg = in.readString();
        articleLink = in.readString();
    }

    public static final Creator<Modal> CREATOR = new Creator<Modal>() {
        @Override
        public Modal createFromParcel(Parcel in) {
            return new Modal(in);
        }

        @Override
        public Modal[] newArray(int size) {
            return new Modal[size];
        }
    };

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(String articlePrice) {
        this.articlePrice = articlePrice;
    }

    public String getBestSuitedFor() {
        return bestSuitedFor;
    }

    public void setBestSuitedFor(String bestSuitedFor) {
        this.bestSuitedFor = bestSuitedFor;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }


    public Modal(String articleId, String articleName, String articleDescription, String articlePrice, String bestSuitedFor, String articleImg, String articleLink) {
        this.articleName = articleName;
        this.articleId = articleId;
        this.articleDescription = articleDescription;
        this.articlePrice = articlePrice;
        this.bestSuitedFor = bestSuitedFor;
        this.articleImg = articleImg;
        this.articleLink = articleLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(articleName);
        dest.writeString(articleId);
        dest.writeString(articleDescription);
        dest.writeString(articlePrice);
        dest.writeString(bestSuitedFor);
        dest.writeString(articleImg);
        dest.writeString(articleLink);
    }
}
