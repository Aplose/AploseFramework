package fr.aplose.aploseframework.dto.google;

import com.google.api.client.json.webtoken.JsonWebSignature;

public class GoogleAuthResultDto {
    private String googleToken;
    private String email;
    private String firstname;
    private String lastname;
    private String uniqueId;
    private String locale;
    private String pictureUrl;
    private String phone;


    public GoogleAuthResultDto(String googleToken, JsonWebSignature.Payload payload){
        this.setGoogleToken(googleToken);
        this.setEmail((String) payload.get("email"));
        this.setFirstname((String) payload.get("given_name"));
        this.setLastname((String) payload.get("family_name"));
        this.setUniqueId((String) payload.get("sub"));
        this.setLocale((String) payload.get("locale"));
        this.setPictureUrl((String) payload.get("picture"));
        this.setPhone((String) payload.get("phone"));
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstname() {
        return firstname;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getUniqueId() {
        return uniqueId;
    }


    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }


    public String getLocale() {
        return locale;
    }


    public void setLocale(String locale) {
        this.locale = locale;
    }


    public String getPictureUrl() {
        return pictureUrl;
    }


    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getGoogleToken() {
        return googleToken;
    }


    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }
}
