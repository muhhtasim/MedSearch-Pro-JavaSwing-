💊 MedSearch Pro
MedSearch Pro is a high-performance Java desktop application designed to provide instantaneous access to a vast medicinal database. In modern healthcare, where rapid data retrieval is critical, this tool bridges the gap between complex raw datasets and a user-friendly digital interface. Built with Java Swing, the app features a sleek dark-themed aesthetic and a robust backend capable of processing over 50,000 medicine entries with zero latency.

📺 Project Demo & Socials
Stay updated with the project development and view the live demo here:

✨ Features
O(1) Search Speed: Utilizes an in-memory HashMap architecture for instant results regardless of database size.

Smart Data Aggregation: Automatically groups multiple variations (different strengths, forms, or manufacturers) under a single medicine name.

CSV Database Integration: Dynamically parses and loads 50,000+ records from the medicine_dataset.csv on startup.

Modern UI: A custom dark-mode interface designed with a "Content-First" philosophy to maximize readability and reduce eye strain.

🛠️ Technical Architecture
The core innovation of MedSearch Pro lies in its efficient data-handling strategy.

Data Loading: On initialization, the system utilizes a BufferedReader to stream the dataset.

Regex Parsing: Employs specialized Regular Expressions to handle complex CSV formatting, ensuring data integrity even when fields contain internal commas (e.g., manufacturer names like "Gilead Sciences, Inc.").

Memory Management: Maps unique medicine identifiers to formatted information blocks, eliminating the need for slow, repetitive disk I/O during active use.

🚀 Getting Started
Prerequisites
Java Development Kit (JDK) 8 or higher.

medicine_dataset.csv (Ensure this is placed in the project root directory).

Installation & Run
Clone the repository:

Bash

git clone https://github.com/your-username/MedSearch-Pro.git
Compile the application:

Bash

javac MedicineInfoApp.java
Run the application:

Bash

java MedicineInfoApp
📊 Dataset Structure
The application is optimized for datasets containing:

Name: Generic/Brand name.

Category: Therapeutic class (Antiviral, Antibiotic, etc.).

Dosage Form & Strength: Delivery method and concentration.

Manufacturer: Producing pharmaceutical company.

Indication: Primary use or condition treated.

⚖️ License
Distributed under the MIT License. See LICENSE for more information.

Developed by Muhtasim Ahmed Connect with me on LinkedIn
