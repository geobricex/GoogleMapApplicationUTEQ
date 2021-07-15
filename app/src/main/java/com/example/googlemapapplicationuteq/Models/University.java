package com.example.googlemapapplicationuteq.Models;

import java.util.List;

public class University {
    //            "name": "Facultad de Ciencias Empresariales",
    //            "direction": "Campus 'Ingeniero Manuel Agustín Haz Álvarez', Av. Quito km. 1 1/2 vía a Santo Domingo de los Tsáchilas",
    //            "authority": "Ing. Mariela Susana Andrade Arias, PhD.",
    //            "contact": "facultadce@uteq.edu.ec",
    //            "logo": "https://www.uteq.edu.ec/images/about/logo_fce.jpg",
    //            "latitude": -1.0121740313099747,
    //            "length": -79.47007644954464
    String name = "";
    String direction = "";
    String authority = "";
    String contact = "";
    String logo = "";
    float latitude = 0;
    float longitude = 0;

    public University() {

    }

    public University(String name, String direction, String authority, String contact, String logo, float latitude, float longitude) {
        this.name = name;
        this.direction = direction;
        this.authority = authority;
        this.contact = contact;
        this.logo = logo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "  {\n" +
                "    \"name\":\"" + name + "\",\n" +
                "    \"direction\":\"" + direction + "\",\n" +
                "    \"authority\":\"" + authority + "\",\n" +
                "    \"contact\":\"" + contact + "\",\n" +
                "    \"logo\":\"" + logo + "\",\n" +
                "    \"latitude\":" + latitude + ",\n" +
                "    \"longitude\":" + longitude + "\n" +
                "  }";
    }
}
