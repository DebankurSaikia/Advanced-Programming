# Assignment 10: Student Management System with Composition

## Technology Used

- Python

## Question

Design a student system in Python with:

### Classes

#### Address

Create an `Address` class containing:

- `street`
- `city`
- `zipCode`

#### Student

Create a `Student` class containing:

- `name`
- `age`
- `Address`
- `course list`

Requirements:

- Store `age` as a protected attribute
- Control access to `age` using `@property`
- Implement the following methods:
  - `add_course()`
  - `display()`

### Derived Class

#### ScholarshipStudent

Extend the `Student` class by:

- Adding a `scholarshipAmount` attribute
- Overriding the `display()` method

### Requirements

Your implementation should clearly demonstrate:

- Composition (`Student` HAS-A `Address`)
- Proper data validation using `@property` (age must be valid)
- Inheritance and method overriding using `super()` in `display()`
- Understanding of mutable behavior (course list updates persist)

## Folder Structure

```text
Assignment10_CSB24008/
├── Source-Code/
└── Sample-Output/
```

## Contents

- **Source-Code/** – Contains the Python implementation of the student management system using composition, inheritance, and property-based validation.
- **Sample-Output/** – Contains execution outputs and screenshots demonstrating the functionality of the system.

## Notes

- The implementation demonstrates composition by associating an `Address` object with each `Student`.
- The `age` attribute is protected and validated through Python properties.
- The `ScholarshipStudent` class extends the functionality of the `Student` class through inheritance.
- Method overriding is implemented using `super()` to reuse and extend base class behavior.
- Course management demonstrates the behavior of mutable objects in Python.
- Sample outputs are included for verification and demonstration purposes.
