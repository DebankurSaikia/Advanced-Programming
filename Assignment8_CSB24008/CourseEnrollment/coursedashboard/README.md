# Course Enrollment Dashboard

A React application for managing student records and course enrollments using React state management and modern JavaScript data structures.

## Features

- Add new students
- Remove students by ID
- Display students sorted by GPA (descending)
- Display all unique enrolled courses
- Filter students by course
- Dynamic state updates without direct mutation
- Course management using `Set`
- Student mapping using `Map`

## Technologies Used

- React
- TypeScript
- Vite
- React Hooks (`useState`)

## Project Structure

```text
CourseEnrollment/
└── coursedashboard/
    ├── src/
    ├── public/
    ├── package.json
    └── ...
```

## Installation

Install dependencies:

```bash
npm install
```

## Run the Application

Start the development server:

```bash
npm run dev
```

## Implementation Details

- State management using `useState`
- Student data management
- Add and remove student functionality
- GPA-based sorting
- Course-based filtering
- Unique course extraction using `Set`
- Student lookup using `Map`
- Use of `map`, `filter`, and `reduce`
- Immutable state updates using the spread operator

## Output

Screenshots of the application output are available in the `Sample-Output` directory of the assignment folder.
