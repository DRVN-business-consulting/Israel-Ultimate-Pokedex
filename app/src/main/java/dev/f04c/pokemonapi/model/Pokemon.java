package dev.f04c.pokemonapi.model;

import java.util.List;

public class Pokemon {
    private int id;
    private Name name;
    private List<String> type;
    private Base base;
    private String species;
    private String description;
    private Profile profile;
    private Image image;

    // Getters and Setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public static class Name {
        private String english;
        private String japanese;
        private String chinese;
        private String french;

        // Getters and Setters...

        public String getEnglish() {
            return english;
        }

        public void setEnglish(String english) {
            this.english = english;
        }

        public String getJapanese() {
            return japanese;
        }

        public void setJapanese(String japanese) {
            this.japanese = japanese;
        }

        public String getChinese() {
            return chinese;
        }

        public void setChinese(String chinese) {
            this.chinese = chinese;
        }

        public String getFrench() {
            return french;
        }

        public void setFrench(String french) {
            this.french = french;
        }
    }

    public static class Base {
        private int HP;
        private int Attack;
        private int Defense;
        private int SpAttack;
        private int SpDefense;
        private int Speed;

        // Getters and Setters...

        public int getHP() {
            return HP;
        }

        public void setHP(int HP) {
            this.HP = HP;
        }

        public int getAttack() {
            return Attack;
        }

        public void setAttack(int attack) {
            this.Attack = attack;
        }

        public int getDefense() {
            return Defense;
        }

        public void setDefense(int defense) {
            this.Defense = defense;
        }

        public int getSpAttack() {
            return SpAttack;
        }

        public void setSpAttack(int spAttack) {
            this.SpAttack = spAttack;
        }

        public int getSpDefense() {
            return SpDefense;
        }

        public void setSpDefense(int spDefense) {
            this.SpDefense = spDefense;
        }

        public int getSpeed() {
            return Speed;
        }

        public void setSpeed(int speed) {
            this.Speed = speed;
        }
    }

    public static class Profile {
        private String height;
        private String weight;
        private List<String> egg;
        private List<List<String>> ability;
        private String gender;

        // Getters and Setters...

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public List<String> getEgg() {
            return egg;
        }

        public void setEgg(List<String> egg) {
            this.egg = egg;
        }

        public List<List<String>> getAbility() {
            return ability;
        }

        public void setAbility(List<List<String>> ability) {
            this.ability = ability;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

    public static class Image {
        private String sprite;
        private String thumbnail;
        private String hiRes;

        // Getters and Setters...

        public String getSprite() {
            return sprite;
        }

        public void setSprite(String sprite) {
            this.sprite = sprite;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getHiRes() {
            return hiRes;
        }

        public void setHiRes(String hiRes) {
            this.hiRes = hiRes;
        }
    }
}