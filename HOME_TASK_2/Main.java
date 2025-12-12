package HOME_TASK_2;

import java.util.*;

class Student {
    private String id, name;
    private int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() { return id; }
    public int getMarks() { return marks; }

    public String getRole() { return "Undergrad"; }

    @Override
    public String toString() {
        return id + " " + name + " (" + marks + ") " + getRole();
    }
}

class GraduateStudent extends Student {
    private String area;

    public GraduateStudent(String id, String name, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }

    @Override
    public String getRole() {
        return "Grad(" + area + ")";
    }
}

class Teacher {
    private String id, name;

    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void printReport(Student s) {
        System.out.println("ID: " + s.getId());
        System.out.println("Marks: " + s.getMarks());
        System.out.println("Role: " + s.getRole());
    }
}

class Repository<T> {
    private Map<String, T> data = new HashMap<>();
    public void save(String id, T obj) { data.put(id, obj); }
    public T find(String id) { return data.get(id); }
    public void delete(String id) { data.remove(id); }
}

public class Main {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("123", "Rachit", 100));
        list.add(new Student("456", "Naman", 400));
        list.add(new Student("586", "Raghav", 900));
        list.add(new GraduateStudent("789", "Pooja", 750, "AI"));

        Repository<Student> repo = new Repository<>();
        for(Student s : list){
            repo.save(s.getId(), s);
        }

        System.out.println("ALL");
        list.forEach(System.out::println);

        Teacher t = new Teacher("T1", "Mr. Ansh");
        System.out.println("\nREPORT FOR 123:");
        t.printReport(repo.find("123"));
    }
}
