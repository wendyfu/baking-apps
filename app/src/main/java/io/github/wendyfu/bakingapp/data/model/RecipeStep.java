package io.github.wendyfu.bakingapp.data.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * @author wendy
 * @since Aug 29, 2017.
 */

@Parcel
public class RecipeStep {

    @SerializedName("id") private int id;
    @SerializedName("shortDescription") private String shortDescription;
    @SerializedName("description") private String description;
    @SerializedName("videoURL") private String videoUrl;
    @SerializedName("thumbnailURL") private String thumbnailUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    ;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
