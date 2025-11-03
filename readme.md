# 🔍 Selenium Automation Testing Final Project

Project ini dibuat untuk melaksanakan final project **otomatisasi pengujian web** menggunakan Selenium Java.  
Tujuan utama dari project ini adalah untuk memastikan test berjalan secara otomatis dan sesuai dengan requirement yang sudah ditentukan.
---

## 📖 Daftar Isi

- [Tech Stack](#-tech-stack)
- [Struktur Project](#-struktur-project)
- [Web Testing](#-web-testing)
- [Instalasi](#-instalasi)
- [Menjalankan Test](#-menjalankan-test)
- [Demo Jenkins Pipeline](#demo-jenkins-pipeline)
- [Dependencies](#dependencies)
- [Support Me](#-support-me)
- [Author](#-author)

---

## 🛠 Tech Stack

- **Bahasa**: Java 21
- **Build Tool**: Maven
- **Testing Framework**: TestNG
- **Library Automation Testing**: Selenium
- **BDD Framework**: Cucumber
- **Dependency Injection**: Cucumber Pico Container

---

## 📂 Struktur Project

```bash
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── setianjay/
│   │           └── enums/
│   │               ├── CabinType.java
│   │               ├── PassengerType.java
│   │               └── TabLandingPage.java
│   └── resources/
│
└── test/
    ├── java/
    │   └── com/
    │       └── setianjay/
    │           ├── context/
    │           │   └── TestContext.java
    │           │
    │           ├── page/
    │           │   ├── base/
    │           │   │   └── BasePage.java
    │           │   ├── bookingpage/
    │           │   │   └── BookingPage.java
    │           │   ├── flightpage/
    │           │   │   └── FlightSearchPage.java
    │           │   └── landingpage/
    │           │       ├── LandingPage.java
    │           │       └── TabFlightPage.java
    │           │
    │           ├── repository/
    │           │   ├── bookingpage/
    │           │   │   └── BookingPageRepository.java
    │           │   ├── flightpage/
    │           │   │   └── FlightSearchPageRepository.java
    │           │   └── landingpage/
    │           │       ├── LandingPageRepository.java
    │           │       └── TabFlightPageRepository.java
    │           │
    │           ├── runner/
    │           │   └── TestRunner.java
    │           │
    │           └── steps/
    │               ├── BookingFlightSteps.java
    │               └── SampleTest.java
    │
    └── resources/
        └── features/
            └── booking_flight.feature
```

---

## 🌐 Web Testing

Project ini menggunakan website **Agoda** untuk pengimplementasiannya:

![Alt Text](src/main/resources/assets/images/readme/Agoda%20Homepage.png)

---

## ⚙️ Instalasi

1. Clone repository
   ```bash
   git clone https://github.com/setianjay/finalproject_selenium_automation.git
   cd finalproject_selenium_automation
   ```

2. Install dependencies
   ```bash
   mvn clean install
   ```

---

## ▶️ Menjalankan Test

### Jalankan semua test

```bash
mvn test
```

### Jalankan test berdasarkan suite `master_test.xml`

```bash
mvn clean test -Dtest=TestRunner
```

---

## Demo Jenkins Pipeline (On Going)

[![Watch the video](https://img.shields.io/badge/▶️-Watch%20Demo-red)]()

---

## 🧹Dependencies

- [Selenium](https://www.selenium.dev/)
- [Pico Container](http://picocontainer.com/)
- [TestNg](https://testng.org)
- [Jackson](https://github.com/FasterXML/jackson)
- [Lombok](https://projectlombok.org/)
- [Slf4j](https://www.slf4j.org/)
- [DotEnv](https://github.com/cdimascio/java-dotenv)

---

## 🤝 Support Me

Just **Give Star** for this repository or **Follow** my Github, you have **Supported Me**.

---

## 🧔 Author

Hari Setiaji - [setianjay](https://github.com/setianjay) on
Github, [Hari Setiaji](https://www.linkedin.com/in/hari-setiaji-3412ba189/) on Linkedin.