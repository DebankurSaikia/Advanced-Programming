import java.util.*;
import java.util.stream.*;

public class StudentAnalyzer {

    static class Student {
        private int id;
        private String name;
        private List<String> courses;
        private Map<String, Integer> scores;
        private double averageScore;

        
        public Student(int id, String name, List<String> courses, Map<String, Integer> scores) {
            this.id = id;
            this.name = name;
            this.courses = new ArrayList<>(courses); 
            this.scores = new HashMap<>(scores); 
            this.averageScore = calculateAverageScore(); 
        }

        
        private double calculateAverageScore() {
            if (courses.isEmpty()) return 0.0;

            return courses.stream()
                    .mapToInt(course -> scores.getOrDefault(course, 0))
                    .average()
                    .orElse(0.0);
        }

        
        public double getAverageScore() { return averageScore; }
        public List<String> getCourses() { return courses; }
        public Map<String, Integer> getScores() { return scores; }

        
        @Override
        public String toString() {
            return id + " - " + name + " | Avg: " + String.format("%.2f", averageScore);
        }
    }


    
    static class StudentPerformanceAnalyzer {

        
        public static List<Student> getTopNStudents(List<Student> students, int n) {
            return students.stream()
                    .sorted(Comparator.comparingDouble(Student::getAverageScore).reversed())
                    .limit(n)
                    .collect(Collectors.toList());
        }

        
        public static Map<String, Double> getAverageScorePerCourse(List<Student> students) {

            Map<String, double[]> courseStats = new HashMap<>();
           

            for (Student student : students) {
                for (String course : student.getCourses()) {

                    
                    courseStats.putIfAbsent(course, new double[]{0.0, 0.0});

                    double[] stats = courseStats.get(course);
                    stats[0] += student.getScores().getOrDefault(course, 0);
                    stats[1] += 1;
                }
            }

            
            Map<String, Double> averages = new HashMap<>();

            for (Map.Entry<String, double[]> entry : courseStats.entrySet()) {
                averages.put(entry.getKey(), entry.getValue()[0] / entry.getValue()[1]);
            }

            return averages;
        }

        
        public static Set<String> getAllUniqueCourses(List<Student> students) {
            return students.stream()
                    .flatMap(s -> s.getCourses().stream())
                    .collect(Collectors.toCollection(HashSet::new));
        }
    }


    
    private static List<Student> generateStudents(int n) {

        List<Student> students = new ArrayList<>();
        Random random = new Random();

        String[] allCourses = {"Math", "DSA", "Java", "OS", "DBMS"};

        for (int i = 1; i <= n; i++) {

            List<String> studentCourses = new ArrayList<>();
            Map<String, Integer> studentScores = new HashMap<>();

            int numCourses = random.nextInt(3) + 3;

            List<String> coursePool = new ArrayList<>(Arrays.asList(allCourses));
            Collections.shuffle(coursePool);

            for (int j = 0; j < numCourses; j++) {
                String course = coursePool.get(j);
                studentCourses.add(course);
                studentScores.put(course, random.nextInt(41) + 60);
            }

            
            students.add(new Student(i, "Student" + i, studentCourses, studentScores));
        }

        return students;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

  
        List<Student> students = generateStudents(n);

        long start1 = System.nanoTime();
        List<Student> topStudents = StudentPerformanceAnalyzer.getTopNStudents(students, 5);
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        Map<String, Double> averages = StudentPerformanceAnalyzer.getAverageScorePerCourse(students);
        long end2 = System.nanoTime();

        long start3 = System.nanoTime();
        Set<String> uniqueCourses = StudentPerformanceAnalyzer.getAllUniqueCourses(students);
        long end3 = System.nanoTime();


        System.out.println("\nTop 5 Students:");
        topStudents.forEach(System.out::println);

        System.out.println("\nCourse Averages:");
        System.out.println(averages);

        System.out.println("\nUnique Courses:");
        System.out.println(uniqueCourses);

        System.out.println("\nExecution Time:");
        System.out.println("Top N Students: " + (end1 - start1) + " ns");
        System.out.println("Course Averages: " + (end2 - start2) + " ns");
        System.out.println("Unique Courses: " + (end3 - start3) + " ns");

        sc.close();
    }
}