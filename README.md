# 🦁 Digital Zoo Manager

Project ini dibuat untuk memenuhi **UTS Mata Kuliah Implementasi PBO**.  
Aplikasi ini adalah **Digital Zoo Manager**, yaitu program berbasis Java dengan GUI (Swing) yang dapat digunakan untuk membuat hewan (Mammal atau Bird) dan menyimpannya dalam sebuah `ArrayList`.

---

## 📌 Fitur
- Input data hewan:
  - Nama (`String`)
  - Usia (`int`)
  - Jenis Hewan (`Mammal` / `Bird`)
  - Warna Bulu (`furColor`) khusus Mammal
  - Bisa Terbang (`canFly`) khusus Bird
- Tombol **Add Animal** untuk menambahkan hewan baru ke dalam Zoo
- Log (JTextArea) untuk menampilkan hasil input:
  - Informasi detail hewan (`getInfo()`)
  - Suara hewan (`makeSound()`)

---
## 🖼️ Tampilan GUI
Contoh saat aplikasi dijalankan:

<img width="1919" height="1005" alt="image" src="https://github.com/user-attachments/assets/c7df571a-baec-43c6-95ca-d3a36165e313" />

---

## ⚙️ Cara Menjalankan
1. Clone repository:
   ```bash
   git clone https://github.com/USERNAME/Digital-Zoo-Manager.git
Buka project di IntelliJ IDEA (atau IDE Java lain).

Pastikan JDK 17+ (di repo ini menggunakan JDK 24).

Run file Main.java.
## 📂 Struktur Project
Digital-Zoo-Manager/
│
├── src/
│ ├── Animal.java # Superclass (name, age, getInfo, makeSound)
├── Mammal.java # Subclass of Animal (furColor)
├── Bird.java # Subclass of Animal (canFly)
├── ZooFrame.java # GUI utama (form input, tombol, log area)
└── Main.java # Entry point, jalankan ZooFrame
│
└── README.md

---

## 👨‍💻 Author
Diky Firmansyah  
NIM: 2311510206  
Universitas Budi Luhur
