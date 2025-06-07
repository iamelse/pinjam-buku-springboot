package com.iamelse.iamelse.service;

import com.iamelse.iamelse.model.User;
import com.iamelse.iamelse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Menyimpan token yang valid
    private final ConcurrentHashMap<String, String> tokenStore = new ConcurrentHashMap<>();

    /**
     * Login user dan mengembalikan token jika berhasil.
     * @param username nama pengguna
     * @param password password (plaintext, harap dihash di produksi)
     * @return token jika berhasil login, null jika gagal
     */
    public String login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Perbandingan plaintext password (untuk contoh; gunakan hash di produksi)
            if (user.getPassword().equals(password)) {
                String token = UUID.randomUUID().toString();
                tokenStore.put(token, username);
                return token;
            }
        }
        return null;
    }

    /**
     * Validasi token autentikasi.
     * @param token token yang dikirim oleh client
     * @return true jika token valid, false jika tidak
     */
    public boolean isValidToken(String token) {
        return token != null && tokenStore.containsKey(token);
    }

    /**
     * Logout user dengan menghapus token-nya.
     * @param token token autentikasi
     */
    public void logout(String token) {
        if (token != null) {
            tokenStore.remove(token);
        }
    }
}