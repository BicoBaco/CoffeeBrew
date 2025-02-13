# CoffeeBrew (Sept 2022)

University project for the "Web Systems Design and Architecture" course (MS in Computer Engineering, Università degli Studi di Palermo @unipa)

Contributors: Vincenzo Messina and Giorgio Tocco

## General Description

CoffeeBrew is a web application designed to manage a smart vending machine system based on the existing "coffee cApp" system at UniPa.

## Installation

- Web Application: Import the project using the "CoffeeBrew.war" file (FIle > Import > WAR-file in Eclipse) and deploy it on Apache Tomcat.
- Database: Import the database schema executing the query found in the "coffeebrew_database.sql" file.

## Technologies Used

- Java Servlets - Handles HTTP requests and processes data validation and client-side input processing.
- AJAX - Manages vending machine communication.
- JSP (Java Server Pages) - Implements the MVC model and manages views.
- HTML, CSS (Bootstrap) and JavaScript - Build the user interface.
- Apache Tomcat - Middleware for deploying the web application.
- MySQL DBMS - Manages relational database storage.

## General System Function

1. Customer:
    - Account management (Registration and Login)
    - Credit card management
    - Credit recharge
    - Product purchasing through the vending machine interface

2. Vending Machine:
    - User authentication (customers or technicians)
    - Displaying and selling products

3. Technician:
    - Vending machine status display
    - Restock products in vending machines

5. Administrator:
    - Register vending machines and technicians
    - Manage users and system statistics
    - Add and manage products

The vending machines operate following these steps:
  - The user enters the vending machine code.
  - The server checks the vending machine status:
    - If the machine is available, it waits for a connection request.
    - If the machine is occupied, the user sees a "Machine in Use" message.
  - Once a request is received, the server sends user data (profile and credit information) and marks the vending machine as "occupied."
  - The user selects a product to purchase.
  - The vending machine dispenses the product and sends the transaction details to the server.
  - The server updates the user's remaining credit and sets the vending machine status to "available."

## Data Model

![coffeebrew_datamodel](https://github.com/user-attachments/assets/35737aba-7036-4df4-992a-2ddc03b5434b)

## In-Depth System Function

### Client-Side Views 
Starting from src/main/webapp

Landing page (/landing.jsp): Displays login and registration buttons for users, technicians, and administrators.

User Views: (/WEB-INF)
  - /accessoUtente.jsp: User login screen.
  - /registrazioneUtente.jsp: User registration screen.
  - /home.jsp: Main user interface allowing vending machine access and credit card management.
  - /gestioneCarteDiCredito.jsp: Manages credit cards (insertion/removal) and credit recharging.

Vending Machine Views: (/WEB-INF)
 - /distributoreAutomatico.jsp: Vending machine interface.
   - After entering the vending machine code, it starts polling the server (via GET requests) for connections from users or technicians. If a user is connected, it shows the purchasing interface. If a technician is connected, it shows product interface with the product reload function. It dynamically loads products for sale.

Technician Views: (/WEB-INF)
- /accessoTecnico.jsp: Technician login screen.
- /pannelloDiControlloTecnico.jsp: Technician dashboard displaying vending machines status and allowing connection to a vending machine.

Administrator Views: (/WEB-INF)
- /accessoAmministratore.jsp: Administrator login screen.
- /pannelloDiControlloAmministratore.jsp: Administrator dashboard with the following functionalities:
    - Manage vending machines (view machine status, insertion and removal).
    - Manage technicians (displaying, insertion and removal).
    - Manage products (displaying, insertion and removal).
    - View statistics on product sales.

Error Page (/WEB-INF/error.jsp): Displays dynamically generated error messages.
Navigation Bar (includes/navbar.jsp): Common navigation component across all pages.

### Server-Side Controllers
(src/main/java/com/mvc/controller)

User Management:
  - /RegistrazioneUtenteController.java: Validates the data required for user registration and provides them to the appropriate model component.
  - /AccessoUtenteController.java: Validates the data required for user login and handles session creation.
  - /HomeUtenteController.java: Loads the main user interface.
  - /CarteDiCreditoController.java: Loads the credit card interface and manages credit card-related operations.
  - /InserimentoCartaDiCreditoController.java: Validates the data required for credit card insertion and provides them to the appropriate model component.
  - /RimozioneCartaDiCreditoController.java: Handles credit cards removal.
  - /RicaricaCreditoController.java: Handles user credit recharge.

Vending Machine Management:
  - /DistributoreAutomaticoController.java: Handles the following polling requests:
      - GET request: Returns JSON object with: User data (if a user is connected) / Technician data (if a technician is connected) / Empty object (if no one is connected)
      - POST request: Processes purchases by updating user credit, logging transactions and releasing the vending machine.
  - /ConnessioneDistributoreAutomaticoController.java: Handles the following polling requests:
      - GET request: Displays vending machine page with a product list (for users only).
      - POST request: Handles vending machine connection requests from users or technicians.

Technician Management:
  - /AccessoTecnicoController.java: Handles technician login.
  - /PannelloDiControlloTecnicoController.java: Loads the technicians dashboard.

Administrator Management:
  - /AccessoAmministratoreController.java: Validates the data required for administrator login and handles session creation.
  - /PannelloDiControlloAmministratoreController.java: Loads the administrator's main dashboard.
  - /RegistrazioneDistributoreAutomaticoController.java: Registers a new vending machine.
  - /RegistrazioneTecnicoController.java: Registers a new technician.
  - /InserimentoProdottoController.java: Adds new products to the system.
  - /RimozioneUtenteController.java: Removes a user.
  - /RimozioneDistributoreAutomaticoController.java: Removes a vending machine.
  - /RimozioneTecnicoController.java: Removes a technician.
  - /RimozioneProdottoController.java: Removes a product from the system.

Other:
  - /LogoutController.java: Logs out users.
  - /ErrorHandler.java: Handles exceptions and errors.
