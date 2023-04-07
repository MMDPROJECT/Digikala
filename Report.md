![](./Images/SBU Icon.png)

# 																		Digikala Project

## 									   										Mohammad Hossein Basouli





















## Introduction:

- #### A brief description of the project :

  - This program should represent a very simple simulation of **"Digikala"** site.

- #### Objectives of the program :

  - Program should let the **sellers** signup and login easily and do add some **products** to the **shop** and other functionalities.

  - Program should let the **users** signup and login to their account and have a wide domain of  functionalities like : managing their **carts**, buying **products**, submitting comment about a **product** and so on.

  - Also some **admins** should be able to manage everything in the **shop** such as :  managing **accounts**, managing **wallet** **requests**, managing **orders** and so on.

- #### A high-level overview of my approach :

  1. Prompt the **user** to choose to navigate through menu options.
  2. Validate the **user** or **seller**.
  3. Prompt the **users** or **sellers** to choose what operations they want to be done.
  4. Confirmation of each operation by an **admin**.

 

## Design and implementation :

- #### A brief description of the design of the solution :

  1. First we declare some **category** and **subcategories** to cover a wide domain of **products** that exist in real life.

     Here you can see diagram of my **UML design** for **product**, **categories** and also **subcategories** : [UML Design of Products, Categories and Subcategories ](https://lucid.app/lucidchart/invitations/accept/inv_5ab079c4-ace8-4714-b1fa-925faf983473)(You can also see the PNG image from Images directory in the **GitHub**).

  2. Then we have to design classes for each entity **Admin**, **Seller** and **User**.

     Here you can see diagram of my **UML design** for **Admin**, **Seller** and **User** : [UML Design of Admin, Seller and User](https://lucid.app/lucidchart/invitations/accept/inv_8e4e1c0d-ef8a-4411-9a7f-69d1dc6ddb97) (You can also see the PNG image from Images directory in the **GitHub**).

  3. After declaration of class **user**, we have to define some entities related to **shopping**, such as **Order**, **Shopping** **Cart** and **Wallet** **Request** to track shopping processes of each **user**.

     Here you can see diagram of my **UML design** for **Order**, **Shopping** **Cart** and **Wallet** **Request** : [UML Design of Order, Shopping Cart and Wallet Request](https://lucid.app/lucidchart/invitations/accept/inv_831dead4-01eb-464b-bd62-bc062e41532b) (You can also see the PNG image from Images directory in the **GitHub**).

  4. At the end corresponding to functionality of each entity we define our Shop class.

     Here you can see diagram of my **UML design** for **Shop** : [UML Design of Shop](https://lucid.app/documents/view/110607ca-b8fc-4b0d-a897-39b1c7631b9a) (You can also see the PNG image from Images directory in the **GitHub**).

- #### Explanation of how the solution was implemented :

  - There is only a few things that I like to explain here in my report, for the others, you can go and look for your self in the code.

    1.  To optimize our design and reduce redundancy as much as we can, we should use the **UUID** to uniquely identify each **entity** in our program both in **time** and **space**.

       With **UUID**s we can easily have access to whatever we need or the **user** desires, no loop is gonna be needed in our work, with **UUID** we can directly have access to the **object** itself.

       Also to implement the **Database**, it's gonna be super useful to use **UUID**s in our program.

    2. To implement the **Database** we will have 3 stages upon us :

       1. **Data insertion to the Database**
          - After we have created an Object in the program, we have to insert that into the **Database**.
       2. **Updating Data in the Database**
          - After we have updated some data in the program, we will need to update data in the **Database** as well.

       3. **Loading Data from the Database**
          - After we close the program data should not be lost and has to correctly load up on the next run.

- #### Discussion about challenges encountered during the implementation :

  - As well as you can see I have declared almost 30 **subcategories**, each has many **attributes** and also using **Enums** as well, This make my work really hard because overall we will have 120 **attributes**, and have to handle each one of them when adding a **product** to **shop** or inserting that to the **Database** and so on. This was the only part of the project that was hard and needed so much time to handle and check. rest of it was easy peazy ;-).



## Testing and evaluation :

- I have already declared some default **entities** in the **database** and worked with it so far. Also I have tested every single segment of the program using these **entities** as you can see in the **database**, and everything worked fine also any bug has been resolved. You can go on and test it your self





## Conclusion :

- #### Key findings of the project :

  1. I have learnt **UUID**s to uniquely identify **entities**.
  2. I have learnt working with the **Databases**.

- #### Reflect the weaknesses of the program :

  1. Bad **version controlling** using **git**.
  2. Bad naming for some **packages**, **classes** and **attributes**, both in the **code** and the **database**.
