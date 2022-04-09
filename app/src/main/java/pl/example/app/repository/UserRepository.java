package pl.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.example.app.model.CustomUser;
import pl.example.app.model.UserInfo;


public interface UserRepository extends JpaRepository<CustomUser, Integer> {
    CustomUser findUserById(int id);

    CustomUser findUserByUsername(String username);

    @Query(value = "SELECT id, username FROM users WHERE id = :id", nativeQuery = true)
    UserInfo findUserInfoById(@Param("id") Integer id);
}
