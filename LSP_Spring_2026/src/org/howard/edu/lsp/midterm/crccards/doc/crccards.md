In 2-3 sentences, explain why TaskManager collaborates with Task, but Task does not collaborate with TaskManager. Your explanation should reference the responsibilities listed in the CRC cards.

TaskManager collaborates with Task because its responsibilities include storing tasks, adding new tasks, finding tasks by ID, and filtering tasks by status. To perform these responsibilities, TaskManager must interact with the Task objects it manages.

Task does not collaborate with TaskManager because its responsibilities are limited to storing its own data and updating its own status. A Task has no need to know how it is stored, managed, or retrieved; therefore, no collaboration with TaskManager is required.