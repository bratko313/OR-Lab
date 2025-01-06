package fer.or.api.dto;

import java.util.List;

public class BoardGameDTO {
    private int id;
    private String name;
    private int releaseYear;
    private String numberOfPlayers;
    private String age;
    private String playingTime;
    private int franchiseId;
    private int publisherId;
    private List<Integer> artistIds;
    private List<Integer> designerIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(String playingTime) {
        this.playingTime = playingTime;
    }

    public int getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(int franchiseId) {
        this.franchiseId = franchiseId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public List<Integer> getArtistIds() {
        return artistIds;
    }

    public void setArtistIds(List<Integer> artistIds) {
        this.artistIds = artistIds;
    }

    public List<Integer> getDesignerIds() {
        return designerIds;
    }

    public void setDesignerIds(List<Integer> designerIds) {
        this.designerIds = designerIds;
    }
}
