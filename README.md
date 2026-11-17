# 🚗 Car Sales Analytics & AI Advisor

An AI-powered car sales analytics application built using **Java Spring Boot, Python, Spring AI, and Ollama**. The project combines backend development, data analysis, data visualization, and AI-powered recommendations to help users understand car sales data and extract meaningful business insights.

## 🚀 Key Features

- **CSV Data Import:** Upload car sales datasets through REST APIs and process structured data using Apache Commons CSV.
- **Data Validation:** Validate uploaded files, extract records, and handle CSV headers with case-insensitive matching and whitespace trimming.
- **Python Data Analytics:** Analyze car sales datasets using Python to identify sales trends, evaluate performance, and generate meaningful insights.
- **Data Visualization:** Generate graphical representations of sales data using Python's Matplotlib library.
- **AI-Powered Advisor:** Integrate Spring AI with Ollama to generate intelligent insights and recommendations based on sales information.
- **Authentication & Authorization:** Implement token-based authentication, stateless security, and role-based access control.
- **REST API Architecture:** Expose backend functionality through REST APIs with structured JSON responses.

## 🛠️ Tech Stack

| Category | Technologies |
|---|---|
| Programming Languages | Java, Python |
| Backend Framework | Spring Boot |
| AI Integration | Spring AI, Ollama |
| Database | MySQL |
| Data Analysis | Python |
| Data Visualization | Matplotlib |
| CSV Processing | Apache Commons CSV |
| API Development | Spring REST |
| Security | Spring Security, Token-Based Authentication |

## 🏗️ Project Architecture

The application follows a modular architecture consisting of three major components:

1. **Java Backend:** Handles REST APIs, authentication, CSV uploads, data validation, business logic, and database operations.
2. **Python Analytics:** Processes car sales datasets, performs data analysis, identifies trends, and generates visualizations.
3. **AI Advisor:** Uses Spring AI and Ollama to provide intelligent analysis and recommendations.

## ⚙️ Application Workflow

1. User uploads a car sales CSV file.
2. Spring Boot receives and validates the uploaded file.
3. Apache Commons CSV extracts and processes the records.
4. Processed sales data is stored in MySQL.
5. Python performs data analysis and generates visualizations.
6. Spring AI communicates with Ollama to generate AI-powered insights and recommendations.

## 📋 Prerequisites

Make sure you have installed:

- Java Development Kit (JDK)
- Maven
- Python 3.x
- MySQL Server
- Ollama
- Git

## 🔧 Installation & Setup

**1. Clone the repository**

```bash
git clone https://github.com/your-username/your-repository.git
cd your-repository
```

**2. Configure MySQL**

Create a database in MySQL and configure your database credentials in `application.yml`.

**3. Configure Ollama**

Install Ollama and download the model you intend to use.

```bash
ollama pull llama3
```

**4. Configure Python**

Install the required Python dependencies using your project's requirements file.

```bash
pip install -r requirements.txt
```

**5. Run the Spring Boot application**

```bash
mvn spring-boot:run
```

## 🎯 Project Objectives

- Combine Java backend development with Python-based data analytics.
- Explore practical implementation of Large Language Models (LLMs).
- Understand AI integration using Spring AI and Ollama.
- Convert raw sales datasets into meaningful business insights.
- Build a secure and scalable REST API-based application.

## 👨‍💻 Author

**Tushar Verma**

- GitHub: [Tusharverma9981](https://github.com/Tusharverma9981)

---

⭐ If you find this project interesting, consider giving the repository a star!
