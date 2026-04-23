Heuristic 1:
Name:
Keep data and behavior together

Explanation:
This heuristic improves readability and maintainability by ensuring that the methods that operate on data are located in the same class as that data. In lecture, this was explained using examples where logic was separated from the objects it belonged to, making the code harder to follow and more difficult to update. Keeping data and behavior together reduces coupling and makes it easier to understand and modify a class without affecting unrelated parts of the system.

Heuristic 2:
Name:
All class data should be private

Explanation:
Making data private improves maintainability by preventing other classes from directly accessing or modifying internal state. In lecture, this heuristic was illustrated by showing how public fields allow uncontrolled changes, which can break class invariants. Using private data with public methods enforces encapsulation and ensures that changes to a class’s implementation do not ripple through the rest of the codebase.

Heuristic 3:
Name:
Minimize the number of public methods

Explanation:
Limiting public methods improves readability by reducing the external interface that developers need to understand. During lecture, this heuristic was discussed in the context of hiding helper methods and exposing only what clients need. Fewer public methods reduce misuse, make classes easier to reason about, and simplify future changes without impacting other classes.
