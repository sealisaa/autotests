package utils;

public class Post {

    private String author;
    private String text;
    private String time;
    private String music;

    public Post(String author, String time, String music, String text) {
        this.author = author;
        this.text = text;
        this.time = time;
        this.music = music;
    }

    public Post() {
    }

    public static class PostBuilder {

        private final Post post;

        public PostBuilder() {
            this.post = new Post();
        }

        public Post build() {
            return post;
        }

        public PostBuilder setAuthor(String author) {
            post.author = author;
            return this;
        }

        public PostBuilder setText(String text) {
            post.text = text;
            return this;
        }

        public PostBuilder setTime(String time) {
            post.time = time;
            return this;
        }

        public PostBuilder setMusic(String music) {
            post.music = music;
            return this;
        }
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public String getMusic() {
        return music;
    }
}
