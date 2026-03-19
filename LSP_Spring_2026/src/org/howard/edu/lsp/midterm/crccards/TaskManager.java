package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The TaskManager class stores and manages Task objects.
 * It supports adding tasks, searching by ID, and retrieving tasks by status.
 * 
 * Name: Iyanuoluwa Hephzibah Olanipekun
 */
public class TaskManager {

    // Internal storage for tasks; ensures quick lookup and prevents duplicates
    private Map<String, Task> tasks = new HashMap<>();

    /**
     * Adds a new Task to the manager.
     * Duplicate task IDs are not allowed.
     *
     * @param task The Task to add.
     * @throws IllegalArgumentException if a task with the same ID already exists.
     */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Duplicate task ID");
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Searches for a Task by its task ID.
     *
     * @param taskId The ID of the task to find.
     * @return the Task if found, or null if not found.
     */
    public Task findTask(String taskId) {
        return tasks.get(taskId);
    }

    /**
     * Retrieves all tasks matching a specific status.
     *
     * @param status The status to filter by (case-sensitive).
     * @return a List of tasks with the given status.
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks.values()) {
            if (task.getStatus().equals(status)) {
                result.add(task);
            }
        }

        return result;
    }
}