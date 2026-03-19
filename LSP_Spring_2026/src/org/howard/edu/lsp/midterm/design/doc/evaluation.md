## Evaluation of the `OrderProcessor` Class

The `OrderProcessor` class violates several key object‑oriented design principles and multiple heuristics from Arthur Riel’s *Object‑Oriented Design Heuristics*. These issues undermine maintainability, extensibility, and clarity. Below are the major problems.

### 1. Violation of the Single Responsibility Principle (SRP)
The class performs many unrelated tasks: tax calculation, discount logic, file writing, printing output, sending emails, and logging. Riel states that *a class should have one reason to change*, but this class has several. Because of this, any update to one concern (e.g., storage format or business rules) forces modification of the same class, creating fragility and poor extensibility.

### 2. Public Attributes and Poor Encapsulation
All fields (`customerName`, `email`, `item`, `price`) are public. This violates Riel’s heuristic that *all data should be hidden within its class*. Public attributes remove control over state, allow invalid updates from outside the class, and break fundamental encapsulation principles. This makes debugging difficult and the codebase less reliable.

### 3. Low Cohesion
The class bundles together unrelated functionalities—computation, persistence, user interaction, and system logging. Riel warns that *low cohesion increases complexity and reduces reusability*. Because the responsibilities are scattered and unrelated, the class becomes harder to understand and maintain. Logical updates require combing through large, monolithic code.

### 4. Excessive Coupling to External Systems
`OrderProcessor` directly depends on `FileWriter`, console output, hardcoded file names, business rules, and email behavior. Riel’s heuristic *minimize coupling between classes* is violated. High coupling makes substitution impossible—changing storage mechanisms, formatting output, or email delivery requires rewriting the class. This makes the system brittle and limits future growth.

### 5. Lack of Abstraction and Poor Separation of Concerns
The class contains concrete implementations instead of delegating tasks to dedicated components. This violates Riel’s guidance to *avoid god classes that know too much or do too much*. Without abstraction layers (repositories, calculators, printers, etc.), the system cannot evolve easily, and logic cannot be reused by other components.

### 6. A God Method (`processOrder()`)
The `processOrder()` method is excessively large and handles multiple unrelated tasks sequentially. Riel emphasizes that *methods should do one thing and do it well*. A God Method makes the system more error‑prone and nearly impossible to test in isolation. Updating one step risks breaking others.

### 7. Poor Reusability and Extensibility
Due to the tight coupling, lack of abstraction, and monolithic design, the class cannot adapt to new requirements. Riel notes that *class design should anticipate change*, but this design does not. Adding a database, modifying discount rules, or supporting different receipt formats requires rewriting core logic.

## Summary
The `OrderProcessor` class demonstrates poor adherence to object-oriented design principles and Riel’s heuristics. It suffers from poor encapsulation, low cohesion, high coupling, lack of abstraction, and monolithic behavior. These issues make the system fragile, hard to maintain, and resistant to extension. A proper redesign should distribute responsibilities across smaller, cohesive classes and introduce abstraction layers to reduce coupling.
