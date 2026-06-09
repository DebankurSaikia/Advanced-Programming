# Assignment 08: Course Enrollment Dashboard

## Technology Used

- React

## Question

Develop a Course Enrollment Dashboard in ReactJS.

You are building a React component that displays enrolled students.

Each student:

```javascript
{
  id: number,
  name: string,
  enrolledCourses: Set<string>,
  gpa: number
}
```

### Tasks

1. Maintain students in state.

2. Implement the following features:

a. Add a new student  
b. Remove a student by ID  
c. Display students sorted by GPA (descending)  
d. Display all unique courses across students  
e. Filter students enrolled in a specific course

### Requirements

Use the following:

a. `useState` for state management  
b. `Map` internally for ID-to-student mapping  
c. `Set` for course uniqueness  
d. `map()`, `filter()`, and `reduce()`  
e. Do not mutate state directly  
f. Use the spread operator for state updates  
g. Convert `Set` to an array before rendering

### Complexity Analysis

Compute the time complexity of filtering students by course.

## Folder Structure

```text
Assignment8_CSB24008/
├── CourseEnrollment/
│   ├── coursedashboard/
│   │   ├── public/
│   │   ├── src/
│   │   └── ...
├── Documentation/
└── Sample-Output/
```

## Contents

- **CourseEnrollment/** – Contains the React project implementation of the Course Enrollment Dashboard.
- **coursedashboard/** – Contains the React application source code and project configuration files.
- **public/** – Contains static assets used by the application.
- **src/** – Contains React components, styling, and application logic.
- **Documentation/** – Contains the complexity analysis and supporting explanations.
- **Sample-Output/** – Contains screenshots and execution outputs demonstrating the functionality of the dashboard.

## Notes

- The implementation uses React's `useState` hook for state management.
- Student records are managed using appropriate JavaScript data structures such as `Map` and `Set`.
- The dashboard supports adding and removing students dynamically.
- Students are displayed in descending order of GPA.
- Unique enrolled courses are extracted and displayed using a `Set`.
- Filtering functionality is provided to display students enrolled in a specific course.
- Complexity analysis for course-based filtering is included in the documentation.
- Sample outputs are included for verification and demonstration purposes.
