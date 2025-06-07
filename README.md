# 📚 Borrow Book API

API sederhana untuk sistem peminjaman buku menggunakan Spring Boot. Aplikasi ini memungkinkan pengguna (peminjam) untuk meminjam buku berdasarkan nama tanpa login, serta melihat riwayat peminjaman.

---

## 🚀 Fitur

- 📖 Tambah data buku
- 🙋 Peminjam meminjam buku hanya dengan mencantumkan nama
- 📦 Otomatis mengurangi stok buku saat dipinjam
- 📜 Lihat daftar semua peminjaman
- 🔁 (Opsional) Pengembalian buku
- 📑 Dokumentasi otomatis dengan Swagger UI

---

## 🛠️ Teknologi

- Java 17
- Spring Boot 3.5.0
- Spring Data JPA
- MySQL
- Hibernate
- Swagger (SpringDoc OpenAPI)
- Maven

---

## 🏗️ Struktur Proyek

```bash
src/
└── main/
    ├── java/
    │   └── com/
    │       └── iamelse/
    │           └── iamelse/
    │               ├── config/          # Konfigurasi global seperti SwaggerConfig, SecurityConfig
    │               ├── controller/      # REST Controllers (endpoint)
    │               ├── dto/             # DTO untuk request & response
    │               ├── exception/       # Custom exception & handler
    │               ├── model/           # Entity kelas (Book, BorrowTransaction, dll.)
    │               ├── repository/      # Interface untuk JPA Repository
    │               ├── security/        # Kelas security (jika digunakan di masa depan, seperti JWT)
    │               └── service/         # Business logic (BorrowService, BookService, dll.)
    └── resources/
        ├── application.properties       # Konfigurasi Spring
        └── data.sql                     # Inisialisasi data
```
---
## 🔐 Swagger UI

Swagger tersedia di URL berikut (jika berhasil):

http://localhost:8080/swagger-ui.html

---

## ⚙️ Konfigurasi

Di `src/main/resources/application.properties`:

```properties
spring.application.name=Borrow Book API

spring.datasource.url=jdbc:mysql://localhost:3306/{NAMA-DATABASE}?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080

springdoc.swagger-ui.path=/swagger-ui.html


---
```

## 🧪 Menjalankan Proyek

```bash
Clone repo ini:

git clone https://github.com/username/borrow-book-api.git
cd borrow-book-api

Build proyek:

./mvnw clean install

Jalankan:

./mvnw spring-boot:run

Buka Swagger:

http://localhost:8080/swagger-ui.html
```

## ✍️ Kontributor
@iamelse: https://github.com/iamelse