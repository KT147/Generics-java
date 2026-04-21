package genericsextra;

import util.QueryItem;
import util.QueryList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

record Employee(String name) implements QueryItem{

    @Override
    public boolean matchFieldValue(String fieldName, String val) {
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        int studentCount = 25;
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            students.add(new Student());
        }
        students.add(new LPAStudent());
//        printMoreList(students);

        List<LPAStudent> lpaStudents = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());
        }

//        printMoreList(lpaStudents);

        testList(new ArrayList<String>(List.of("Able", "Barry")));
        testList(new ArrayList<Integer>(List.of(1,2)));

//        var queryList = new QueryList<>(lpaStudents);
//        var matches = queryList.getMatches("Course", "Python");
//        printMoreList(matches);

        var students2021 = QueryList.getMatches(students, "YearStarted", "2021");
//        printMoreList(students2021);

        var matches = QueryList.getMatches(students, "Course", "Java");
        printMoreList(matches);

        compareById(students);
        compareByPercent(lpaStudents);
        lessThan50PercentComplete(lpaStudents);

    }

//    public static <T extends Student> void printList(List<T> students) {
//        for (var student : students) {
//            System.out.println(student.getYearStarted() + ": " + student);
//        }
//        System.out.println();
//    }

    public static void printMoreList(List<? extends Student> students) {
        for (var student : students) {
            System.out.println(student.getYearStarted() + ": " + student);
        }
        System.out.println();
    }

    public static void compareById(List<? extends Student> students) {
        students.sort(Comparator.comparing(Student::getId));
        for (var student : students) {
            System.out.println(student.getName() + "'s id is " + student.getId());
        }
        System.out.println();
    }

    public static void compareByPercent(List<? extends LPAStudent> students){
        students.sort(Comparator.comparing(LPAStudent::getPercentComplete));
        for (var student : students) {
            System.out.println(student.getName() + "percentage is " + student.getPercentComplete());
        }
        System.out.println();
    }

    public static void lessThan50PercentComplete(List<? extends LPAStudent> students) {
        students.sort(Comparator.comparing(LPAStudent::getPercentComplete));
        for (var student : students) {
            var completion = student.getPercentComplete();
            if (completion <= 50) {
                System.out.println(student.getName() + "percentage is " + completion);
            }
        }
        System.out.println();
    }

//    public static void testList(List<String> list) {
//        for (var element : list) {
//            System.out.println("String: " + element.toUpperCase());
//        }
//    }
//
//    public static void testList(List<Integer> list) {
//        for (var element : list) {
//            System.out.println("Integer: " + element.floatValue());
//        }
//    }

    public static void testList(List<?> list) {
        for (var element : list) {
            if (element instanceof String s) {
                System.out.println("String: " + s.toUpperCase());
            } else if (element instanceof Integer i) {
                System.out.println("Integer: " + i.floatValue());
            }
        }
    }

}


