package bean;

public class user {
    public String openid;
    public String nickname;
    public String latitude;
    public String longitude;
    public String type;
    public user (String openid,String nickname,String latitude,String longitude,String type){
        this.openid = openid;
        this.nickname = nickname;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }
    public user (String latitude,String longitude,String type){
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }
    public user (String nickname,String latitude,String longitude,String type){
        this.nickname = nickname;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }
    public user (String nickname,String type){
        this.nickname = nickname;
        this.type = type;
    }
    public user (){}
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpenid() {
        return openid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getType() {
        return type;
    }
    @Override

    public String toString() {
        return "User [username=" + nickname + ", password=" + type + "]";
    }
}
