import { useState, useEffect } from "react";

type Student = {
  id: number;
  name: string;
  enrolledCourses: Set<string>;
  gpa: number;
};

export default function App() {

  const [studentsMap, setStudentsMap] = useState<Map<number, Student>>(() => {
    const saved = localStorage.getItem("students");
    if (!saved) return new Map();

    const parsed: any[] = JSON.parse(saved);
    const restored = new Map<number, Student>();

    parsed.forEach((student) => {
      restored.set(student.id, {
        ...student,
        enrolledCourses: new Set(student.enrolledCourses),
      });
    });

    return restored;
  });

  useEffect(() => {
    const arrayVersion = [...studentsMap.values()].map((student) => ({
      ...student,
      enrolledCourses: [...student.enrolledCourses],
    }));

    localStorage.setItem("students", JSON.stringify(arrayVersion));
  }, [studentsMap]);

  const [name, setName] = useState<string>("");
  const [gpa, setGpa] = useState<string>("");
  const [courses, setCourses] = useState<string>("");


  const [filterInput, setFilterInput] = useState<string>("");
  const [filterCourse, setFilterCourse] = useState<string>("");


  const [errors, setErrors] = useState<Record<string, string>>({});


  const validateForm = (): boolean => {
    const newErrors: Record<string, string> = {};

    if (!name.trim()) newErrors.name = "Name is required";

    if (!gpa) newErrors.gpa = "GPA is required";
    else if (isNaN(Number(gpa)))
      newErrors.gpa = "GPA must be numeric";
    else if (Number(gpa) < 0 || Number(gpa) > 10)
      newErrors.gpa = "GPA must be between 0 and 10";

    if (!courses.trim())
      newErrors.courses = "At least one course required";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };


  const addStudent = () => {
    if (!validateForm()) return;

    const id = Date.now();


    const courseArray = courses
      .split(",")
      .map((c) => c.trim())
      .filter((c) => c !== "");


    const newStudent: Student = {
      id,
      name: name.trim(),
      enrolledCourses: new Set(courseArray),
      gpa: parseFloat(gpa),
    };


    setStudentsMap((prev) => {
      const newMap = new Map(prev);
      newMap.set(id, newStudent);
      return newMap;
    });


    setName("");
    setGpa("");
    setCourses("");
    setErrors({});
  };


  const removeStudent = (id: number) => {
    setStudentsMap((prev) => {
      const newMap = new Map(prev);
      newMap.delete(id);
      return newMap;
    });
  };


  const applyFilter = () => {
    setFilterCourse(filterInput.trim());
  };


  const studentsArray = [...studentsMap.values()];


  const sortedStudents = [...studentsArray].sort(
    (a, b) => b.gpa - a.gpa
  );


  const allCourses = studentsArray.reduce((acc, student) => {
    student.enrolledCourses.forEach((course) => acc.add(course));
    return acc;
  }, new Set<string>());


  const filteredStudents = filterCourse
    ? sortedStudents.filter((student) =>
        student.enrolledCourses.has(filterCourse)
      )
    : sortedStudents;

  return (
    <div className="container">
      <h1 className="title">Course Enrollment Dashboard</h1>

      <div className="section">
        <h2>Add Student</h2>

        <input
          className="input"
          type="text"
          placeholder="Student Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        {errors.name && <p className="error">{errors.name}</p>}

        <input
          className="input"
          type="number"
          placeholder="GPA (0-10)"
          value={gpa}
          onChange={(e) => setGpa(e.target.value)}
        />
        {errors.gpa && <p className="error">{errors.gpa}</p>}

        <input
          className="input"
          type="text"
          placeholder="Courses (comma separated)"
          value={courses}
          onChange={(e) => setCourses(e.target.value)}
        />
        {errors.courses && <p className="error">{errors.courses}</p>}

        <button className="add-btn" onClick={addStudent}>
          Add Student
        </button>
      </div>


      <div className="section">
        <h2>Filter By Course</h2>

        <div className="filter-container">
          <input
            className="input"
            type="text"
            placeholder="Enter course"
            value={filterInput}
            onChange={(e) => setFilterInput(e.target.value)}
          />

          <button className="filter-btn" onClick={applyFilter}>
            <i className="fa-solid fa-arrow-right"></i>
          </button>
        </div>
      </div>


      <div className="section">
        <h2>All Unique Courses</h2>
        <ul className="course-list">
          {[...allCourses].map((course, index) => (
            <li key={index}>{course}</li>
          ))}
        </ul>
      </div>


      <div className="section">
        <h2>Students (Sorted by GPA Desc)</h2>

        {filteredStudents.length === 0 && <p>No students found.</p>}

        {filteredStudents.map((student) => (
          <div key={student.id} className="card">
            <p><strong>ID:</strong> {student.id}</p>
            <p><strong>Name:</strong> {student.name}</p>
            <p><strong>GPA:</strong> {student.gpa}</p>
            <p>
              <strong>Courses:</strong>{" "}
              {[...student.enrolledCourses].join(", ")}
            </p>

            <button
              className="remove-btn"
              onClick={() => removeStudent(student.id)}
            >
              Remove
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}