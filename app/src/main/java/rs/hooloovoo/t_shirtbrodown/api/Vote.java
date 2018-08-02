package rs.hooloovoo.t_shirtbrodown.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vote {
    @SerializedName("color")
    @Expose
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vote {" + "color='" + color +'}';
    }
}