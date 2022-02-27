package Logic;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class represents a topic tha student needs or wants to reinforce.
 * A student is able to define a topic as he or she wishes to.
 * Only parameters title and subject are crucial.
 *
 * A topic group is constructed when a topic is created without an answer.
 * It is meant to work as a hierarchy construnction, which summarises topics to groups.
 *
 * @author Tim Freimann
 */
public class Topic {

    private String title;
    private String answer;
    private int priority;
    private Subject subject;
    private Topic topicGroup;
    private ArrayList<Topic> topicList;
    private ArrayList<Topic> topicPriorityUnset;
    private ArrayList<Topic> topicPriorityHard;
    private ArrayList<Topic> topicPriorityMedium;
    private ArrayList<Topic> topicPriorityEasy;
    private LocalDate startedStudying;
    private LocalDate stoppedStudying;
    private long timeStudied = 0;

    /**
     * Constructor with title, answer, priority, subject and topicGroup
     *
     * @param title name of topic or a question
     * @param answer answer to topic
     * @param priority starting priority
     * @param subject topic beloning to certain subject
     * @param topicGroup topic belonging to certain topic group
     */
    Topic(String title, String answer, int priority, Subject subject, Topic topicGroup) {
        this.title = title;
        this.answer = answer;
        this.priority = priority;
        this.subject = subject;
        this.topicGroup = topicGroup;

        // Add topic to subjects and topic groups ArrayList with all topics
        if (!subject.getAlltopicsList().contains(this)) {
            subject.getAlltopicsList().add(this);
            topicGroup.getTopicList().add(this);
        } else {
            System.out.println("Topic already exists!");
        }

        // sorting topic by priority
        sortByPriority(this, topicGroup.getTopicPriorityUnset(), topicGroup.getTopicPriorityHard(),
                topicGroup.getTopicPriorityMedium(), topicGroup.getTopicPriorityEasy());
    }

    /**
     * Constructor with title, answer, priority and subject
     *
     * @param title name of topic or a question
     * @param answer answer to topic
     * @param priority starting priority
     * @param subject topic belonging to a certain subject
     */
    Topic(String title, String answer, int priority, Subject subject) {
        this.title = title;
        this.answer = answer;
        this.priority = priority;
        this.subject = subject;
        this.topicGroup = null;

        // Add topic to subjects ArrayList with all topics
        if (!subject.getAlltopicsList().contains(this)) {
            subject.getAlltopicsList().add(this);
        } else {
            System.out.println("Topic already exists!");
        }

        // sorting topic by priority
        sortByPriority(this, subject.getTopicPriorityUnset(), subject.getTopicPriorityHard(),
                subject.getTopicPriorityMedium(), subject.getTopicPriorityEasy());
    }

    /**
     * Constructor with title, answer, subject and topicGroup
     *
     * @param title name of topic or a question
     * @param answer answer to topic
     * @param subject topic belonging to a certain subject
     * @param topicGroup topic can be sorted to a certain topic group
     */
    Topic(String title, String answer, Subject subject, Topic topicGroup) {
        this.title = title;
        this.answer = answer;
        this.priority = 0;
        this.subject = subject;
        this.topicGroup = topicGroup;

        // Add topic to subjects and topic groups ArrayList with all topics
        if (!subject.getAlltopicsList().contains(this)) {
            subject.getAlltopicsList().add(this);
            topicGroup.getTopicList().add(this);
        } else {
            System.out.println("Topic already exists!");
        }

        // sorting topic by priority
        sortByPriority(this, topicGroup.getTopicPriorityUnset(), topicGroup.getTopicPriorityHard(),
                topicGroup.getTopicPriorityMedium(), topicGroup.getTopicPriorityEasy());
    }

    /**
     * Constructor with title, answer and subject
     *
     * @param title name of topic or a question
     * @param answer answer to the topic
     * @param subject topic belonging to a certain subject
     */
    Topic(String title, String answer, Subject subject) {
        this.title = title;
        this.answer = answer;
        this.priority = 0;
        this.subject = subject;
        this.topicGroup = null;

        // Add topic to subjects ArrayList with all topics
        if (!subject.getAlltopicsList().contains(this)) {
            subject.getAlltopicsList().add(this);
        } else {
            System.out.println("Topic already exists!");
        }

        // sorting topic by priority
        sortByPriority(this, subject.getTopicPriorityUnset(), subject.getTopicPriorityHard(),
                subject.getTopicPriorityMedium(), subject.getTopicPriorityEasy());
    }

    /**
     * Constructor with title, priority and subject. (Topic Group Constructor)
     *
     * @param title name of topic or a question
     * @param priority starting priority
     * @param subject topic belonging to a certain subject
     */
    Topic(String title, int priority, Subject subject) {
        this.title = title;
        this.answer = null;
        this.priority = priority;
        this.subject = subject;
        this.topicGroup = null;
        // all topics of topic group
        this.topicList = new ArrayList<>();
        // sorting ArrayLists for topic groups to sort topics by priority
        this.topicPriorityUnset = new ArrayList<>();
        this.topicPriorityHard = new ArrayList<>();
        this.topicPriorityMedium = new ArrayList<>();
        this.topicPriorityEasy = new ArrayList<>();

        // Add topic group to subjects ArrayList with all topics
        if (!subject.getAllTopicGroupsList().contains(this)) {
            subject.getAllTopicGroupsList().add(this);
        } else {
            System.out.println("Topic Group already exists!");
        }

        // sorting topic group by priority
        sortByPriority(this, subject.getTopicGroupPriorityUnset(), subject.getTopicGroupPriorityHard(),
                subject.getTopicGroupPriorityMedium(), subject.getTopicGroupPriorityEasy());
    }

    /**
     * Constructor only with needed parameters title and subject. (Topic Group Constructor)
     *
     * @param title name of topic or a question
     * @param subject topic belonging to a certain subject
     */
    Topic(String title, Subject subject) {
        this.title = title;
        this.answer = null;
        this.priority = 0;
        this.subject = subject;
        this.topicGroup = null;
        // all topics of topic group
        this.topicList = new ArrayList<>();
        // sorting ArrayLists for topic groups to sort topics by priority
        this.topicPriorityUnset = new ArrayList<>();
        this.topicPriorityHard = new ArrayList<>();
        this.topicPriorityMedium = new ArrayList<>();
        this.topicPriorityEasy = new ArrayList<>();

        // Add topic group to subjects ArrayList with all topics
        if (!subject.getAllTopicGroupsList().contains(this)) {
            subject.getAllTopicGroupsList().add(this);
        } else {
            System.out.println("Topic Group already exists!");
        }

        // sorting topic group by priority
        sortByPriority(this, subject.getTopicPriorityUnset(), subject.getTopicGroupPriorityHard(),
                subject.getTopicPriorityMedium(), subject.getTopicGroupPriorityEasy());
    }

    public void calculateTimeStudied() {
        Duration duration = Duration.between(this.getStartedStudying(), this.getStoppedStudying());
        timeStudied += duration.getSeconds();
    }

    /**
     * This method sorts a Topic or topic group into the correct priority ArrayList.
     *
     * @param topicOrTopicGroup topic or topic group which shall be sorted
     * @param priorityUnset ArrayList for all topics / topic groups with 'unset' priority
     * @param priorityHard ArrayList for all topics / topic groups with 'hard' priority
     * @param priorityMedium ArrayList for all topics / topic groups with 'medium' priority
     * @param priorityEasy ArrayList for all topics / topic groups with 'easy' priority
     */
    public void sortByPriority(Topic topicOrTopicGroup, ArrayList<Topic> priorityUnset,
                               ArrayList<Topic> priorityHard, ArrayList<Topic> priorityMedium,
                               ArrayList<Topic> priorityEasy) {
        if (topicOrTopicGroup.getPriority() == 0) {
            priorityUnset.add(topicOrTopicGroup);
        } else if (topicOrTopicGroup.getPriority() == 1) {
            priorityHard.add(topicOrTopicGroup);
        } else if (topicOrTopicGroup.getPriority() == 2) {
            priorityMedium.add(topicOrTopicGroup);
        } else {
            priorityEasy.add(topicOrTopicGroup);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Topic getTopicGroup() {
        return topicGroup;
    }

    public void setTopicGroup(Topic topicGroup) {
        this.topicGroup = topicGroup;
    }

    public ArrayList<Topic> getTopicList() {
        return topicList;
    }

    public void setStartedStudying(LocalDate startedStudying) {
        this.startedStudying = startedStudying;
    }

    public void setStoppedStudying(LocalDate stoppedStudying) {
        this.stoppedStudying = stoppedStudying;
        calculateTimeStudied();
    }

    public LocalDate getStartedStudying() {
        return startedStudying;
    }

    public LocalDate getStoppedStudying() {
        return stoppedStudying;
    }

    public long getTimeStudied() {
        return timeStudied;
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
