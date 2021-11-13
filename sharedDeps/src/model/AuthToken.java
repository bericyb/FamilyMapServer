package model;


import java.util.Date;
import java.util.Objects;


/**
 * An AuthToken
 */
public class AuthToken {

    /**
     * The unique token used for authorization.
     */
    private String token;


    /**
     * The username that the AuthToken belongs to.
     */
    private String username;

    /**
     * The date when the AuthToken was created.
     */
    private String life;

    /**
     * Creates an AuthToken for a user.
     * @param username the username that the token belongs to.
     */
    public AuthToken(String username) {

        this.token = IdGen.generateID();

        this.username = username;
        this.life = new Date().toString();
    }

    /**
     * Creates an AuthToken with existing data
     * @param token the authtoken string to be used.
     * @param username the username the token belongs to.
     */
    public AuthToken(String token, String username, String life) {
        this.token = token;
        this.username = username;
        this.life = life;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(token, authToken.token) && Objects.equals(username, authToken.username) && Objects.equals(life, authToken.life);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, username);
    }
}
