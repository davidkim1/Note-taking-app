package com.example.notetaking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Awesome Pojo Generator
 * */
public class DocumentPojo {
    @SerializedName("id")
    @Expose
    private UUID id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("creation_date")
    @Expose
    private Long creation_date;
    @SerializedName("title")
    @Expose
    private String title;
    public void setId(String id){
        this.id=UUID.fromString(id);
    }
    public String getId(){
        return id.toString();
    }
    public void setText(String text){
        this.text=text;
    }
    public String getText(){
        return text;
    }
    public void setCreation_date(Long creation_date){
        this.creation_date=creation_date;
    }
    public Long getCreation_date(){
        return creation_date;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
}
