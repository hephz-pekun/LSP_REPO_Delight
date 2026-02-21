
Record of prompts used with a generative AI assistant and the assistant’s responses related to Assignment 3.  

This document records the prompts I used with an AI assistant and the assistant’s responses (summarized where appropriate). It shows how I leveraged AI to redesign a procedural ETL (Assignment 2) into an object-oriented solution (Assignment 3), debug compilation and IDE issues, and prepare submission documents.

> Note: Long code listings are kept in the repository source files. Here, responses are summarized for readability; where code was provided, the response explicitly states so.

1) Prompt
**User:** *“Ok, here's the assignment … Here's the ETL code:”*  
(Pasted Assignment 2 single‑class Java ETL and requirements to redesign using OO with one public class per file and Javadocs.)

**AI Response (summary):** Proposed a full OO redesign and supplied complete Java classes with Javadocs: `Product`, `Pipeline`, `PipelineMetrics`, `ProductSource`, `CsvProductSource`, `ProductSink`, `CsvProductSink`, `Transformation`, `PriceRule` + `ElectronicsDiscountRule`, `CategoryUpgradeRule`, `UppercaseNameTransformation`, `PriceRangeDeriver`, `Csv`, `Money`, `SummaryPrinter`, and `SkipRecordException`. Mapped every transformation and output to match Assignment 2 and included build/run steps and an AI‑usage statement.

2) Prompt
**User:** *“Kindly reduce the number of java doc in assignment 3 package.”*

**AI Response (summary):** Minimized Javadocs to concise one‑liners while meeting the requirement that each public class and method has Javadoc. Provided lean examples for all key classes showing the short‑form comment style.

3) Prompt
**User:** *“No, i meant java files (i.e., classes), be sure that java doc properly and efficiently explains inner workings of lines of code.”*

**AI Response (summary):** Consolidated the design into **6 files** using nested static classes to reduce file count while keeping one public class per file: `App`, `Pipeline` (with nested `Metrics` and `SkipRecordException`), `Product`, `CsvProductIO` (nested `Reader`/`Writer`), `Transformations` (interface + rules, including `PriceRule`), and `Util`. Enhanced Javadocs and inline comments explaining iterator behavior, IO lifecycle, and rounding.

4) Prompt
**User:** *“why is this an error in app.java  CsvProductIO.Writer sink = new CsvProductIO.Writer(Paths.get(outputPath));”*

**AI Response (summary):** Gave a diagnostic checklist: ensure `Writer` is a **public static** nested class; verify package/imports; add `import java.nio.file.Paths;`; ensure public constructor; compile all sources; match file/class names. Included correct usage examples.

5) Prompt
**User:** *“Explain error messages: … Unresolved compilation problems: CsvProductIO.Writer cannot be resolved to a type …”*

**AI Response (summary):** Explained typical root causes (non‑static inner class, visibility, package mismatch, file naming, or partial compile) and provided precise fixes and sample code. 

6) Prompt (code pasted with HTML entities)
**User:** Pasted `App.java` and `CsvProductIO.java` containing `&lt;`, `&gt;`, `&amp;`.

**AI Response (summary):** Identified HTML‑escaped entities as the root cause breaking generics/operators. Advised global replacements (`&lt;`→`<`, `&gt;`→`>`, `&amp;`→`&&`) and showed corrected snippets.

7) Prompt
**User:** *“Can you show the corrected full code?”*

**AI Response (summary):** Provided the **full, corrected 6-file implementation** (App, Pipeline, Product, CsvProductIO, Transformations, Util) with proper symbols and build instructions.

8) Prompt
**User:** *“Where can I find the error message for missing file?”*

**AI Response (summary):** Explained that Java would normally show `FileNotFoundException` in the console, but prior code suppressed exceptions. Pointed to where to surface errors and how to print the underlying exception.

9) Prompt
**User:** *“I want to say 'Error processing file due to the missing input file'.”*

**AI Response (summary):** Supplied a concrete change in `CsvProductIO.Reader.iterator()` to print the custom message and rethrow a `RuntimeException` when the input file is missing.

10) Prompt
**User:** *“This is the expected run message from A2 … A3 prints fewer rows read. Why doesn't it read all the rows?”*

**AI Response (summary):** Clarified metric semantics: A2 counted every physical line after the header; A3 counted only successful parses. Gave a patch to increment the read counter **before** calling `next()` so `read = transformed + skipped`, matching A2.

23) Prompt
**User:** *“for assignment three what's the error message when the input file is empty”*

**AI Response (summary):** Clarified that an **empty file** is treated as a valid input with **0 rows**; no error message is printed. The output CSV contains only the header, and the run summary shows zeros. Missing file prints the custom message if implemented.

24) Prompt
**User:** *“for empty file input what does the transformed_products.csv means”*

**AI Response (summary):** Explained that the output will contain **only the header row** because there are no data lines to transform; this is expected and correct.
