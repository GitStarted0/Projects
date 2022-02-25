package Logic;

/**
 * This class represents a topic tha student needs or wants to reinforce.
 * A student is able to define a topic as he or she wishes to.
 * Only parameters title and subject are crucial.
 *
 * A topic group is constructed when a topic is created without a answer.
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

        if (subject.getTopicList().contains(this)) {
            System.out.println("Topic already exists. Still add topic?");
        } else {
            subject.getTopicList().add(this);
        }
    }

    /**
     * Constructor with title, priority, subject and topicGroup. This constructs a topic group
     *
     * @param title name of topic or a question
     * @param priority starting priority
     * @param subject topic belonging to certain subject
     * @param topicGroup topic can be sorted to a certain topic group
     */
    Topic(String title, int priority, Subject subject, Topic topicGroup) {
        this.title = title;
        this.answer = null;
        this.priority = priority;
        this.subject = subject;
        this.topicGroup = topicGroup;

        if (!subject.getTopicGroupList().contains(this)) {
            subject.getTopicGroupList().add(this);
        } else {
            System.out.println("Topic Group already exists. Still add Topic Group?");
        }
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

        if (subject.getTopicList().contains(this)) {
            System.out.println("Topic already exists. Still add topic?");
        } else {
            subject.getTopicList().add(this);
        }
    }

    // title, answer, subject, topicGroup

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

        if (subject.getTopicList().contains(this)) {
            System.out.println("Topic already exists. Still add topic?");
        } else {
            subject.getTopicList().add(this);
        }
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

        if (subject.getTopicList().contains(this)) {
            System.out.println("Topic already exists. Still add topic?");
        } else {
            subject.getTopicList().add(this);
        }
    }

    /**
     * Constructor with title, subject and topic group. (Topic Group Constructor)
     *
     * @param title name of topic or a question
     * @param subject topic belonging to a certain subject
     * @param topicGroup topic can be sorted to a certain topic group
     */
    Topic(String title, Subject subject, Topic topicGroup) {
        this.title = title;
        this.answer = null;
        this.priority = 0;
        this.subject = subject;
        this.topicGroup = topicGroup;

        if (subject.getTopicGroupList().contains(this)) {
            System.out.println("Topic already exists. Still add topic?");
        } else {
            subject.getTopicGroupList().add(this);
        }
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

        if (!subject.getTopicGroupList().contains(this)) {
            subject.getTopicGroupList().add(this);
        } else {
            System.out.println("Topic Group already exists. Still add Topic Group?");
        }
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

        if (!subject.getTopicGroupList().contains(this)) {
            subject.getTopicGroupList().add(this);
        } else {
            System.out.println("Topic Group already exists. Still add Topic Group?");
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

}
