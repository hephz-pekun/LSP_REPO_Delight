# **Design Evaluation**

The current implementation of the `PriceCalculator` class suffers from multiple design issues that make it difficult to maintain, extend, and reuse as the system grows. The major problems include:

***

## **1. Violation of the Open/Closed Principle (OCP)**

The class uses a chain of `if` statements to check the `customerType` and apply discounts.  
If a new customer type or discount rule needs to be added, the class must be modified directly.  
This makes the class *closed* to extension and *open* to modification—opposite of what OCP recommends.

Example problem:

*   Adding "STUDENT", "SENIOR", or "BLACK\_FRIDAY" customers requires editing the method.
*   The logic becomes longer and increasingly error‑prone.

***

## **2. Tight Coupling Between Price Calculation and Customer Type Logic**

All discount rules are implemented inside a single method. This makes the class responsible for multiple behaviors, violating the Single Responsibility Principle (SRP).  
If discount formulas change frequently, developers must repeatedly modify the same class.

This design couples:

*   discount policy logic
*   customer type handling
*   price calculation

Such coupling reduces flexibility and hurts testability.

***

## **3. Poor Scalability as the System Evolves**

As more discount types are added, the number of conditional checks grows. The method becomes cluttered and difficult to read.

If statements like:

```java
if (customerType.equals("HOLIDAY"))
```

will eventually become dozens of conditions—hard to manage and easy to break.

***

## **4. Increased Risk of Bugs & Duplicate Code**

Each conditional block performs similar operations (price \* discount). Duplicating this logic in multiple branches increases the chance of mistakes.  
Additionally, the lack of structure makes debugging more difficult, especially in large systems.

***

## **5. No Use of Polymorphism**

The class ignores object‑oriented design principles like polymorphism and abstraction. Instead of giving each discount its own behavior, all logic lives in a single function.  
This prevents the system from being easily extensible and reusable.

***

## **Conclusion**

The `PriceCalculator` class becomes fragile, hard to extend, and difficult to maintain as new customer types and discount rules are introduced.  
A better design is to apply the **Strategy Pattern**, which encapsulates each discount rule in its own class and allows new strategies to be added without modifying existing code.
