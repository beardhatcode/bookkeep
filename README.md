# Bookkeep



## Parts needed

-   Database storage
-   Source reader/writer
-   Webinterface for API
    -   Secured
    -   Shared secret



## Database

### Main entities

#### Bank statements

| Field       | Type         | Description                            |
| ----------- | ------------ | -------------------------------------- |
| **amount**  | decimal(9,2) | The amount of the transaction (in EUR) |
| **from**    | IBAN nr      | The sender                             |
| **to**      | IBAN nr      | The receiver                           |
| **comment** | String       | The bank description                   |
| **date**    | datetime     | When the transaction was made          |
| **raw**     | JSON         | JSON of the raw input data             |
| **notes**   | String       | Textual user note                      |
| **source**  | source id    |                                        |

#### Invoices/receit

| Field            | Type                     | Description                              |
| ---------------- | ------------------------ | ---------------------------------------- |
| **name**         | string                   | The name of the invoice                  |
| **organisation** | NULL \| organisation id+ |                                          |
| **type**         | NULL \| type id+         |                                          |
| **amount**       | decimal(9,2)             | Amount in EUR                            |
| **raw**          | JSON                     | The raw data form the inport for future reterence and agregarion |
| **notes**        | string                   | Aditional user notes                     |
| **filename**     | path                     | Path to the file that contains the receit |
| **content**      | String                   | Text from the PDF, user editable         |
| **paid**         | NULL \| invoice id+      |                                          |

#### Organisations



| Field          | Type   | Description |
| -------------- | ------ | ----------- |
| **name**       | String |             |
| **accountno**  | IBAN+  |             |
| **CustomerID** | String |             |

#### Sources

| Field    | Type   | Description                 |
| -------- | ------ | --------------------------- |
| **name** | String | Name of the datasource      |
| **type** | String | Name of the modifying class |


## Requirements

The application should be able to

-   Store bank information
    -   Supplied as CSV
    -   Match Paypal payments and the autmatic payment to paypall
-   Strore invoices
    -   Match to payments by amount and name
-   Store warrantties
    -   Link to invoice and payment
    -   Expirery date
-   Support for payback, 
    -   Combine payments to only keep the net remainder of all transactions belonging to it
-   Mail
    -   Read mails and treat them as input

