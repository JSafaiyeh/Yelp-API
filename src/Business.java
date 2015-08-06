/**
 * Created by Jason Safaiyeh Github: JSafaiyeh
 */

import java.util.List;

public class Business {

    private String name;
    private String photoUrl;
    private String yelpMobileUrl;
    private String phoneNumber;
    private List<String> address;
    private int reviewCount;
    private double distance;
    private double rating;

    private String ratingSmallImgUrl;
    private String ratingMediumImgUrl;
    private String ratingLargeImgUrl;

    private String snippetText;

    public Business(String name, List<String> address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public List<String> getAddress() {
        return address;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getYelpMobileUrl() {
        return yelpMobileUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setYelpMobileUrl(String yelpMobileUrl) {
        this.yelpMobileUrl = yelpMobileUrl;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getRatingSmallImgUrl() {
        return ratingSmallImgUrl;
    }

    public void setRatingSmallImgUrl(String ratingSmallImgUrl) {
        this.ratingSmallImgUrl = ratingSmallImgUrl;
    }

    public String getRatingMediumImgUrl() {
        return ratingMediumImgUrl;
    }

    public void setRatingMediumImgUrl(String ratingMediumImgUrl) {
        this.ratingMediumImgUrl = ratingMediumImgUrl;
    }

    public String getRatingLargeImgUrl() {
        return ratingLargeImgUrl;
    }

    public void setRatingLargeImgUrl(String ratingLargeImgUrl) {
        this.ratingLargeImgUrl = ratingLargeImgUrl;
    }

    public String getSnippetText() {
        return snippetText;
    }

    public void setSnippetText(String snippetText) {
        this.snippetText = snippetText;
    }

    @Override
    public String toString() {
        return "Name:" + getName() + " Address:" + getAddress() + " Phone: " + getPhoneNumber();
    }
}
