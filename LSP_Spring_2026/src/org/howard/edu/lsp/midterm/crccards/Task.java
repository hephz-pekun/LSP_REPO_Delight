package org.howard.edu.lsp.midterm.crccards;

/**
 * The Task class represents a task with an ID, description, and status.
 * It provides functionality to store and update task information.
 * 
 * Name: Iyanuoluwa Hephzibah Olanipekun
 */
public class Task {
    private String taskId;
    private String description;
    private String status;

    /**
     * Constructor for creating a new Task.
     * The default status is set to "OPEN".
     *
     * @param taskId The unique ID of the task.
     * @param description A short description of the task.
     */
    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";   // Default status
    }

    /**
     * Returns the task ID.
     *
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Returns the task description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the status of the task.
     * Valid statuses are: OPEN, IN_PROGRESS, COMPLETE.
     * If the provided status is not valid, it is set to "UNKNOWN".
     *
     * @param status The new status value
     */
    public void setStatus(String status) {
        if (status.equals("OPEN") || status.equals("IN_PROGRESS") || status.equals("COMPLETE")) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }

    /**
     * Returns the string representation of the task in the format:
     * taskId description [status]
     *
     * @return formatted task details as a string
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}