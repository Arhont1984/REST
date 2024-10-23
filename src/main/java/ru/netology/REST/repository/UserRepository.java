package ru.netology.REST.repository;

import org.springframework.stereotype.Repository;
import ru.netology.REST.serviсe.Authorities;

import java.util.*;

@Repository
public class UserRepository {

    private Map<String, UserCredentials> userStore = new HashMap<>();

    // Инициализация тестовыми данными
    public UserRepository() {
        userStore.put("user1", new UserCredentials("password1", Arrays.asList(Authorities.READ, Authorities.WRITE)));
        userStore.put("user2", new UserCredentials("password2", Arrays.asList(Authorities.READ)));
        userStore.put("admin", new UserCredentials("adminPass", Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE)));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        UserCredentials credentials = userStore.get(user);
        if (credentials != null && credentials.getPassword().equals(password)) {
            return credentials.getAuthorities();
        } else {
            return new ArrayList<>(); // Возврат пустого списка, если пользователь не найден или пароль неверный
        }
    }

    // Вспомогательный класс для хранения логина, пароля и разрешений
    private static class UserCredentials {
        private String password;
        private List<Authorities> authorities;

        public UserCredentials(String password, List<Authorities> authorities) {
            this.password = password;
            this.authorities = authorities;
        }

        public String getPassword() {
            return password;
        }

        public List<Authorities> getAuthorities() {
            return authorities;
        }
    }
}
