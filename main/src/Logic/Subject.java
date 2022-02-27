package Logic;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Subject {

    private String title;
    private int priority;
    private LocalDate dateOfExam;
    private static ArrayList<Subject> subjectList = new ArrayList<>();
    // overall topics and topic groups
    private ArrayList<Topic> alltopicsList;
    private ArrayList<Topic> allTopicGroupsList;
    // sorting ArrayLists for topics and topic groups of subject by priority
    private ArrayList<Topic> topicGroupPriorityUnset;
    private ArrayList<Topic> topicGroupPriorityHard;
    private ArrayList<Topic> topicGroupPriorityMedium;
    private ArrayList<Topic> topicGroupPriorityEasy;
    private ArrayList<Topic> topicPriorityUnset;
    private ArrayList<Topic> topicPriorityHard;
    private ArrayList<Topic> topicPriorityMedium;
    private ArrayList<Topic> topicPriorityEasy;
    // started studying subject
    private LocalDateTime startedStudying;
    // stopped studing subject
    private LocalDateTime stoppedStudying;
    // total time subject was studied
    private long timeStudied;

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
        // overall topics and topic groups
        this.allTopicGroupsList = new ArrayList<>();
        this.alltopicsList = new ArrayList<>();
        // sorting ArrayLists for topics and topic groups of subject by priority
        this.topicGroupPriorityUnset = new ArrayList<>();
        this.topicGroupPriorityHard = new ArrayList<>();
        this.topicGroupPriorityMedium = new ArrayList<>();
        this.topicGroupPriorityEasy = new ArrayList<>();
        this.topicPriorityUnset = new ArrayList<>();
        this.topicPriorityHard = new ArrayList<>();
        this.topicPriorityMedium = new ArrayList<>();
        this.topicPriorityEasy = new ArrayList<>();
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
        // overall topics and topic groups
        this.allTopicGroupsList = new ArrayList<>();
        this.alltopicsList = new ArrayList<>();
        // sorting ArrayLists for topics and topic groups of subject by priority
        this.topicGroupPriorityUnset = new ArrayList<>();
        this.topicGroupPriorityHard = new ArrayList<>();
        this.topicGroupPriorityMedium = new ArrayList<>();
        this.topicGroupPriorityEasy = new ArrayList<>();
        this.topicPriorityUnset = new ArrayList<>();
        this.topicPriorityHard = new ArrayList<>();
        this.topicPriorityMedium = new ArrayList<>();
        this.topicPriorityEasy = new ArrayList<>();

        subjectList.add(this);

        // sorting subjects by priority
        sortByPriority(this, Selecting.getSubjectPriorityUnset(), Selecting.getSubjectPriorityHard(),
                Selecting.getSubjectPriorityMedium(), Selecting.getSubjectPriorityEasy());
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
        // overall topics and topic groups
        this.allTopicGroupsList = new ArrayList<>();
        this.alltopicsList = new ArrayList<>();
        // sorting ArrayLists for topics and topic groups of subject by priority
        this.topicGroupPriorityUnset = new ArrayList<>();
        this.topicGroupPriorityHard = new ArrayList<>();
        this.topicGroupPriorityMedium = new ArrayList<>();
        this.topicGroupPriorityEasy = new ArrayList<>();
        this.topicPriorityUnset = new ArrayList<>();
        this.topicPriorityHard = new ArrayList<>();
        this.topicPriorityMedium = new ArrayList<>();
        this.topicPriorityEasy = new ArrayList<>();
        subjectList.add(this);

        // sorting subjects by priority
        sortByPriority(this, Selecting.getSubjectPriorityUnset(), Selecting.getSubjectPriorityHard(),
                Selecting.getSubjectPriorityMedium(), Selecting.getSubjectPriorityEasy());
    }

    Subject(String title) {
        this.title = title;
        this.priority = 0;
        this.dateOfExam = null;
        this.allTopicGroupsList = new ArrayList<>();
        this.alltopicsList = new ArrayList<>();
        this.topicGroupPriorityUnset = new ArrayList<>();
        this.topicGroupPriorityHard = new ArrayList<>();
        this.topicGroupPriorityMedium = new ArrayList<>();
        this.topicGroupPriorityEasy = new ArrayList<>();
        this.topicPriorityUnset = new ArrayList<>();
        this.topicPriorityHard = new ArrayList<>();
        this.topicPriorityMedium = new ArrayList<>();
        this.topicPriorityEasy = new ArrayList<>();
        subjectList.add(this);
        sortByPriority(this, Selecting.getSubjectPriorityUnset(), Selecting.getSubjectPriorityHard(),
                Selecting.getSubjectPriorityMedium(), Selecting.getSubjectPriorityEasy());
    }

    public void calculateTimeStudied() {
        Duration duration = Duration.between(this.getStartedStudying(), this.getStoppedStudying());
        timeStudied += duration.getSeconds();
    }

    public void sortByPriority(Subject subject, ArrayList<Subject> priorityUnset,
                               ArrayList<Subject> priorityHard, ArrayList<Subject> priorityMedium,
                               ArrayList<Subject> priorityEasy) {
        if (subject.getPriority() == 0) {
            priorityUnset.add(subject);
        } else if (subject.getPriority() == 1) {
            priorityHard.add(subject);
        } else if (subject.getPriority() == 2) {
            priorityMedium.add(subject);
        } else {
            priorityEasy.add(subject);
        }
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

    public ArrayList<Topic> getAlltopicsList() {
        return alltopicsList;
    }

    public ArrayList<Topic> getAllTopicGroupsList() {
        return allTopicGroupsList;
    }

    public void setStartedStudying(LocalDateTime startedStudying) {
        this.startedStudying = startedStudying;
    }

    public void setStoppedStudying(LocalDateTime stoppedStudying) {
        this.stoppedStudying = stoppedStudying;
        calculateTimeStudied();
    }

    public LocalDateTime getStartedStudying() {
        return startedStudying;
    }

    public LocalDateTime getStoppedStudying() {
        return stoppedStudying;
    }

    public long getTimeStudied() {
        return timeStudied;
    }

    public ArrayList<Topic> getTopicGroupPriorityUnset() {
        return topicGroupPriorityUnset;
    }

    public ArrayList<Topic> getTopicGroupPriorityHard() {
        return topicGroupPriorityHard;
    }

    public ArrayList<Topic> getTopicGroupPriorityMedium() {
        return topicGroupPriorityMedium;
    }

    public ArrayList<Topic> getTopicGroupPriorityEasy() {
        return topicGroupPriorityEasy;
    }

    public ArrayList<Topic> getTopicPriorityUnset() {
        return topicPriorityUnset;
    }

    public ArrayList<Topic> getTopicPriorityHard() {
        return topicPriorityHard;
    }

    public ArrayList<Topic> getTopicPriorityMedium() {
        return topicPriorityMedium;
    }

    public ArrayList<Topic> getTopicPriorityEasy() {
        return topicPriorityEasy;
    }
}
