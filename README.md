# DSA521S Group Mini-Project 2026

## Project
**NUST Service Centre Simulation: Designing and Evaluating Data Structures and Algorithms**

This Java project demonstrates the data structures and algorithms required for the DSA521S mini-project. The integrated service-centre application uses a manually implemented Queue, Singly Linked List, array processing, and four sorting algorithms. The postfix Stack exercise is included as a separate program, as required by the project brief.

## Group Identification

**Group Number:** Not assigned

| Student Number | Full Name |
|---|---|
| 225001187 | Japhet Nekwiyu |
| 225132214 | Selma Nandago |
| 225097958 | Maryna Kashaka |
| 226141101 | Penda Haimene |
| 225072068 | Mervin Hukununa |

**Submitted by: 225001187 - Japhet Nekwiyu**

## Project Structure

```text
src/
  CampusServiceCentre.java   Main integrated Part D program
  Student.java               Student data model
  StudentQueue.java          Manual array-based Queue
  StudentLinkedList.java     Manual Singly Linked List
  DailyStatistics.java       Array-based daily statistics
  SortingAlgorithms.java     Sorting used by the integrated menu
  SortingExperiment.java     Part C experiment and comparison counters
  PartBSortingDemo.java      Part B traces and counter demonstration
  PostfixEvaluator.java      Separate manual Stack/postfix exercise

docs/
  DSA521S_Group_Project_Report_FINAL.pdf
```

## Requirements

- Java Development Kit (JDK) 8 or newer.
- No external libraries are required.

## Compile and Run

From the repository root:

```text
cd src
javac *.java
java CampusServiceCentre
```

## Run the Separate Postfix Stack Exercise

After compiling in `src`:

```text
java PostfixEvaluator
```

The demonstration evaluates:

```text
5 3 + 2 *
```

and produces the final result `16`, while displaying the stack after each major operation.

## Run the Part B Sorting Demonstration

```text
java PartBSortingDemo
```

This uses the required array:

```text
[17, 5, 23, 8, 14, 3, 11, 20, 6, 9]
```

and demonstrates Selection Sort, Insertion Sort, Merge Sort, and Quick Sort without Java's built-in sorting functions.

## Integrated Service-Centre Menu

The main program provides these options:

1. Add student to waiting queue
2. Serve next student
3. Display waiting students
4. Add student service record
5. Display student service records
6. Search for student record
7. Remove student record
8. Display daily statistics
9. Sort service times
10. Run sorting experiment
11. Exit

## Implementation Restrictions Followed

- No `Arrays.sort()` or equivalent built-in sorting function is used.
- No built-in Java `Stack`, `Queue`, or `LinkedList` class replaces the required manual implementations.
- Daily statistics are calculated by traversing the service-time array rather than using built-in `max`, `min`, or `sum` shortcuts.

## GitHub Repository Link

https://github.com/JaphetNekwiyu/DSA521S-Group-Mini-Project-2026

The same repository URL is included in the final PDF project report.
