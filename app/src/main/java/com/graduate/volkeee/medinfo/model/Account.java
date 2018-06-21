package com.graduate.volkeee.medinfo.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Account implements Serializable {
    public static final String KEY_id = "id";
    public static final String KEY_firstName = "first_name";
    public static final String KEY_lastName = "last_name";
    public static final String KEY_midName = "mid_name";
    public static final String KEY_email = "email";
    public static final String KEY_password = "password";
    public static final String KEY_adress = "adress";
    public static final String KEY_dateOfBirth = "date_of_birth";
    public static final String KEY_phone = "phone";
    public static final String KEY_sex = "sex";
    public static final String KEY_utype = "utype";
    public static final String KEY_photoPath = "photoPath";


    private Integer id;
    private String firstName;
    private String lastName;
    private String midName;
    private String email;
    private String password;
    private String adress;
    private String dateOfBirth;
    private String phone;
    private String sex;
    private String utype;
    private String photoPath;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5434866305268191867L;

    /**
     * No args constructor for use in serialization
     */
    public Account() {
    }

    /**
     * @param id          An id of the account
     * @param dateOfBirth User's date of birth
     * @param lastName    User's last name
     * @param sex         User's sex
     * @param phone       User's phone number
     * @param address     User's address
     * @param email       User's email
     * @param midName     User's middlename
     * @param utype       Type of user's account
     * @param photoPath   A link to user's profile photo
     * @param firstName   User's first name
     */
    public Account(String address, String dateOfBirth, String email, String password, String firstName, Integer id, String lastName, String midName, String phone, String photoPath, String sex, String utype) {
        this.adress = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.midName = midName;
        this.phone = phone;
        this.photoPath = photoPath;
        this.sex = sex;
        this.utype = utype;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void fromJson(JSONObject jsonObject) throws JSONException {
        JSONObject accountJson = jsonObject.getJSONObject("account");

        this.setId(accountJson.getInt("id"));
        this.setFirstName(accountJson.getString("first_name"));
        this.setLastName(accountJson.getString("last_name"));
        this.setMidName(accountJson.getString("mid_name"));
        this.setAdress(accountJson.getString("adress"));
        this.setDateOfBirth(accountJson.getString("date_of_birth"));
        this.setEmail(accountJson.getString("email"));
        this.setPhone(accountJson.getString("phone"));
        this.setPhotoPath(accountJson.getString("photo_path"));
        this.setSex(accountJson.getString("sex"));
        this.setUtype(accountJson.getString("utype"));
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", midName='" + midName + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", utype='" + utype + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}