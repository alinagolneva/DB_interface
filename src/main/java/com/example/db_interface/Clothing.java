package com.example.db_interface;

public class Clothing {
    private String name;
    private String size;
    private int season_id_season;
    private int material_id_material;
    private int color_id_color;
    private int price_cl;
    private int rating;


    public int getPrice_cl() {
        return price_cl;
    }

    public void setPrice_cl(int price_cl) {
        this.price_cl = price_cl;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Clothing() {
    }

    public Clothing(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSeason_id_season() {
        return season_id_season;
    }

    public void setSeason_id_season(int season_id_season) {
        this.season_id_season = season_id_season;
    }

    public int getMaterial_id_material() {
        return material_id_material;
    }

    public void setMaterial_id_material(int material_id_material) {
        this.material_id_material = material_id_material;
    }

    public int getColor_id_color() {
        return color_id_color;
    }

    public void setColor_id_color(int color_id_color) {
        this.color_id_color = color_id_color;
    }
}
