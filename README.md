# 											  The God We Trust

​																					![img](./Images/SBU.png) 





# 												  Digikala Project

## 														 Mohammad Hossein Basouli















# Introduction:

- ### Briefly describe on the project:

  - The project is about building a **CLI app**, that simulates some functionalities of **Digikala** (A very popular **e-commerce** website).

- ### State the objectives of the assignment:

  1. This program should provide an easy a user-friendly interface for all three objects (**Sellers, Buyers & Admins**), that are using **Digikala**.
  2. The program should be able to handle all the shopping processes like **Wallet Charging, Managing Shopping Cart** and so much more...
  3. After shopping, the money should be automatically deposited to the **Shop's wallet** and also the **Seller's wallet**.
  4. And all that you can except from a **CLI Digikala app** :). 

- ### Provide a high-level overview of the approach taken to complete the assignment:

  1. At first it's good to have an intuition on the functionalities of **Digikala**, and how things should work under the hoods.
  2. The second part is to try to implement objects and interfaces to work with to let the program and shopping processes work, Of course this is a really important part of programming to see what you have to do and decide how to implement the stuff .
  3. Last but not least is about linking the **UI** to the code bases and functionalities.

- ### Categories and Sub-Categories that are supported in this App:

  1. Beauty
     - Eye Brow Make Up
     - Eye Make Up
  2. Books
     - Children Book
     - Fiction Book
     - Poetry Book
  3. Clothes
     - Coat
     - Jean
     - Sweater
  4. Electronics
     - Laptop
     - Smart Phone
     - Smart Watch

  5. Home
     - Air Conditioner
     - Refrigreator
     - TV
  6. Sports
     - Ball
     - Gloves
     - Rackets

  7. Super Market

     - Dairy
     - Drinks
     - Proteins

  8. Tools

     - Drill
     - Soldering System
     - Spanner

  9. Toys & Games

     - Board Games
     - Card Games
     - Puzzles

  10. Vehicle

      - Car

      - Motor Cycle

      - Truck



# Entity Abilities & Features:

- ### Seller:

  - #### **Account Management:**

    - Sign Up
    - Sign In
    - Logout

  - #### **Product Management**:

    -  Add a new Product

  - #### **Wallet Management**:

    - View Wallet



- ### User:

  - #### **Search & Show Products:**

    - Search through categories
    - Show purchased products

  - #### Cart Management

    - Add a new cart
    - Switch cart
    - Update cart
    - View cart
    - Checkout cart

  - #### **Wallet Management**:

    - Submit a wallet request
    - Show wallet requests
    - Show wallet

  - #### **Submit a comment**

  - #### **Update Personal Information**:

    - Update Password
    - Update Email
    - Update Phone number
    - Update Address

  - #### **Logout**



- ### **Admin**:

  - #### Wallets Management

    - Show all wallet requests
    - Show all confirmed wallet requests
    - Show all unconfirmed wallet requests
    - Confirm a wallet request by ID

  - #### Checkouts & Orders Management

    - Show all orders
    - Show all confirmed orders
    - Show all unconfirmed orders
    - Confirm an order by ID

  - #### Admins Management:

    - Add an admin

  - ####  Users Management:

    - Show all user wallet requests by userID
    - Show user's confirmed wallet requests by userID
    - Show user's unconfirmed wallet requests by userID
    - Show user's all orders by userID
    - Show user's confirmed orders by userID
    - Show user's unconfirmed orders by userID
    - Watch all profile screens
    - Watch an specific profile screen by userID

  - ####  Sellers Management

    - Show all unauthorized sellers
    - Authorize a seller by sellerID

  - #### Logout

# Design and implementation :

- ### A brief description of the design of the solution :

  1. First we declare some **category** and **subcategories** to cover a wide domain of **products** that exist in real life.

     Here you can see diagram of my **UML design** for **product**, **categories** and also **subcategories** : [UML Design of Products, Categories and Subcategories ](https://lucid.app/lucidchart/invitations/accept/inv_5ab079c4-ace8-4714-b1fa-925faf983473)

  2. Then we have to design classes for each entity **Admin**, **Seller** and **User**.

     Here you can see diagram of my **UML design** for **Admin**, **Seller** and **User** : [UML Design of Admin, Seller and User](https://lucid.app/lucidchart/invitations/accept/inv_8e4e1c0d-ef8a-4411-9a7f-69d1dc6ddb97) .

  3. After declaration of class **user**, we have to define some entities related to **shopping**, such as **Order**, **Shopping** **Cart** and **Wallet** **Request** to track shopping processes of each **user**.

     Here you can see diagram of my **UML design** for **Order**, **Shopping** **Cart** and **Wallet** **Request** : [UML Design of Order, Shopping Cart and Wallet Request](https://lucid.app/lucidchart/invitations/accept/inv_831dead4-01eb-464b-bd62-bc062e41532b) .

  4. At the end corresponding to functionality of each entity we define our Shop class.

     Here you can see diagram of my **UML design** for **Shop** : [UML Design of Shop](https://lucid.app/documents/view/110607ca-b8fc-4b0d-a897-39b1c7631b9a).

- ### Technologies, Libraries & framworks:

  1. Java

  2. UUID

  3. Database (SQlite)

  

# Testing and Evaluation:

### 