package Logic;

import java.time.LocalDate;
import java.util.ArrayList;

public class Subject {

    private String title;
    private int priority;
    private LocalDate dateOfExam;
    private static ArrayList<Subject> subjectList = new ArrayList<>();
    private ArrayList<Topic> topicList;
    private ArrayList<Topic> topicGroupList;

    /**
     * Constructor with title, priority and date of exam.
     *
     * @param title name of subject
     * @param priority starting priority
     * @param dateOfExam date of exam
     */
    Subject(String title, int priority, LocalDate dateOfExam) {
        this.title = title;
        this.priority = priority;
        this.dateOfExam = dateOfExam;
        this.topicGroupList = new ArrayList<>();
        this.topicList = new ArrayList<>();
        subjectList.add(this);
    }

    /**
     * Constructor with title and date of exam.
     *
     * @param title name of subject
     * @param dateOfExam date of exam
     */
    Subject(String title, LocalDate dateOfExam) {
        this.title = title;
        this.priority = 0;
        this.dateOfExam = dateOfExam;
        this.topicGroupList = new ArrayList<>();
        this.topicList = new ArrayList<>();
        subjectList.add(this);
    }

    /**
     * Constructor with title and priority
     *
     * @param title name of subject
     * @param priority starting priority
     */
    Subject(String title, int priority) {
        this.title = title;
        this.priority = priority;
        this.dateOfExam = null;
        this.topicGroupList = new ArrayList<>();
        this.topicList = new ArrayList<>();
        subjectList.add(this);
    }

    Subject(String title) {
        this.title = title;
        this.priority = 0;
        this.dateOfExam = null;
        this.topicGroupList = new ArrayList<>();
        this.topicList = new ArrayList<>();
        subjectList.add(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDateOfExam() {
        return dateOfExam;
    }

    public void setDateOfExam(LocalDate dateOfExam) {
        this.dateOfExam = dateOfExam;
    }

    public static ArrayList<Subject> getSubjectList() {
        return subjectList;
    }

    public static void setSubjectList(ArrayList<Subject> subjectList) {
        Subject.subjectList = subjectList;
    }

    public ArrayList<Topic> getTopicList() {
        return topicList;
    }

    public ArrayList<Topic> getTopicGroupList() {
        return topicGroupList;
    }
}
