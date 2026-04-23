package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract Report class implementing the Template Method pattern.
 * Defines the fixed workflow for generating reports.
 */
public abstract class Report {

    /**
     * Template method defining report generation steps.
     * This method should not be overridden.
     */
    public final void generateReport() {
        loadData();
        formatHeader();
        formatBody();
        formatFooter();
    }

    /** Loads report-specific data */
    protected abstract void loadData();

    /** Prints the report header */
    protected abstract void formatHeader();

    /** Prints the report body */
    protected abstract void formatBody();

    /** Prints the report footer */
    protected abstract void formatFooter();
}