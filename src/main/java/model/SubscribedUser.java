package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SubscribedUSer")
public class SubscribedUser extends User{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uuid;
    private String nickname;
    private String email;
    private String password;
    private Channel profile;
    private List<Channel> followedChannel;

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Channel getProfile() {
        return profile;
    }

    public List<Channel> getFollowedChannel() {
        return followedChannel;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
