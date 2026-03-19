CRC Cards for Improved Design

Class: Order

Responsibilities:

*   Store order-related data (customer, item, price).
*   Provide accessors/mutators to maintain encapsulation.
*   Compute order subtotal.

Collaborators:

*   Customer
*   Item
*   OrderCalculator


Class: Customer

Responsibilities:

*   Store customer information (name, email).
*   Provide access to customer contact details.

Collaborators:

*   Order
*   EmailService


Class: Item

Responsibilities:

*   Represent an item being purchased (name, base price).
*   Provide price information.

Collaborators:

*   Order

Class: OrderCalculator

Responsibilities:

*   Compute tax for an order.
*   Apply discounts based on business rules.
*   Compute final total.

Collaborators:

*   Order

Class: ReceiptPrinter

Responsibilities:

*   Format and print the order receipt.
*   Display receipt data without performing business logic.

Collaborators:

*   Order
*   Customer
*   Item

Class: OrderRepository

Responsibilities:

*   Persist completed orders to storage (file, database, etc.).
*   Handle read/write operations through an abstraction.

Collaborators:

*   Order

Class: EmailService

Responsibilities:

*   Send confirmation emails to customers.
*   Format and deliver email messages.

Collaborators:

*   Customer
*   Order

Class: Logger

Responsibilities:

*   Log system activity (order processed timestamps, errors).
*   Manage output for audit or debugging purposes.

Collaborators:

*   OrderProcessor

Class: OrderProcessor

Responsibilities:

*   Coordinate the steps involved in processing an order.
*   Delegate tasks to specialized classes (calculation, storage, printing, emailing).
*   Serve as the workflow controller of the order processing lifecycle.

Collaborators:

*   Order
*   OrderCalculator
*   ReceiptPrinter
*   OrderRepository
*   EmailService
*   Logger
