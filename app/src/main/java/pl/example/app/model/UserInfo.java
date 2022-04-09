package pl.example.app.model;

/*
    Interfejs za pamocą którego będziemy z bazy wyciągać
    dane użytkownika, pomijając hasło
 */
public interface UserInfo {
    Integer getId();
    String getUsername();
}
