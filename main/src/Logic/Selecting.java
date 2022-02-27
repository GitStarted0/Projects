package Logic;

import java.util.ArrayList;

/**
 * This class represents the selection during the studying process
 *
 * @author Tim Freimann
 */
public class Selecting {

    private static ArrayList<Subject> subjectPriorityUnset = new ArrayList<>();
    private static ArrayList<Subject> subjectPriorityHard = new ArrayList<>();
    private static ArrayList<Subject> subjectPriorityMedium = new ArrayList<>();
    private static ArrayList<Subject> subjectPriorityEasy = new ArrayList<>();
    private static int subjectCounter = 0;
    private static int topicGroupCounter = 0;
    private static int topicCounter = 0;
    private static Subject selectedSub;
    private static Topic selectedTG;
    private static Topic selectedTopic;

    /**
     * This method is used when all subjects are being studied
     *
     * @return selected topic
     */
    public static Topic selectFromAll() {
        selectedSub = selectSubject();
        selection();
        return selectedTopic;
    }

    /**
     * This method is used to select a topic when a certain subject is being studied.
     *
     * @param subject subject that is being studied
     * @return selected topic
     */
    public static Topic selectFromSubject(Subject subject) {
        selectedSub = subject;
        selection();
        return selectedTopic;
    }

    /**
     * This method is used, when a certain topic group is studied.
     *
     * @param topicGroup topic group that is being studied.
     * @return selected topic
     */
    public static Topic selectFromTopicGroup(Topic topicGroup) {
        selectedTG = topicGroup;
        selectedTopic = selectTopicFromTopicGroup(topicGroup);
        return selectedTopic;
    }

    /**
     * This method selects a topic during the studying process and saves it in 'selectedTopic'.
     */
    public static void selection() {
        int topics = selectedSub.getAlltopicsList().size();
        int topicsInTopicGroups = 0;
        // Check if there are topics that have no topic group...
        for (Topic topicGroup : selectedSub.getAllTopicGroupsList()) {
            topicsInTopicGroups += topicGroup.getTopicPriorityUnset().size()
                    + topicGroup.getTopicPriorityEasy().size() + topicGroup.getTopicPriorityMedium().size()
                    + topicGroup.getTopicPriorityHard().size();
            System.out.println(topics + " = " + topicsInTopicGroups);
        }
        // ... if there are the selection of a topic group shall be skipped, so all topics are selectable
        if (topics > topicsInTopicGroups) {
            selectedTopic = selectTopicFromSubject(selectedSub);
        // ... if not, the priority of the topic groups must be considered
        } else {
            selectedTG = selectTopicGroup(selectedSub);
            System.out.println("selected TG Prio: " + selectedTG.getPriority());
            selectedTopic = selectTopicFromTopicGroup(selectedTG);
        }
        System.out.println("selected Topic Prio: " + selectedTopic.getPriority());
    }

    /**
     * This method was implemented to test different stages of the selection process.
     * In this case it is needed to check if the correct subject was selected.
     *
     * @return selected subject
     */
    public static Subject selectSubFromAll() {
        selectedSub = selectSubject();
        System.out.println("selected Sub Prio: " + selectedSub.getPriority());
        return selectedSub;
    }

    /**
     * This method was implemented to test different stages of the selection process.
     * In this case it is needed to test if the correct topic group was selected
     *
     * @return selected topic group
     */
    public static Topic selectTGFromAll() {
        selectedSub = selectSubject();
        System.out.println("selected Sub Prio: " + selectedSub.getPriority());
        selectedTG = selectTopicGroup(selectedSub);
        System.out.println("selected TG Prio: " + selectedTG.getPriority());
        return selectedTG;
    }

    /**
     * This method was implemented to test different stages of the selection process.
     * In this case it is needed to check if the correct Topic was selected.
     *
     * @return selected topic
     */
    public static Topic selectTopicFromAll() {
        selectedSub = selectSubject();
        System.out.println("selected Sub Prio: " + selectedSub.getPriority());
        selection();
        return selectedTopic;
    }

    /**
     * This method selects a subject depending on its priority.
     *
     * @return selected Subject
     */
    public static Subject selectSubject() {
        Subject selectedSubject = null;
        if (!subjectPriorityUnset.isEmpty()) {
            selectedSubject = subjectPriorityUnset.get(0);
//            subjectPriorityUnset.remove(0);
        } else if (subjectCounter < 3 && !subjectPriorityHard.isEmpty()) {
            selectedSubject = subjectPriorityHard.get(0);
//            subjectPriorityHard.remove(0);
        } else if (subjectCounter < 5 && !subjectPriorityMedium.isEmpty()) {
            selectedSubject = subjectPriorityMedium.get(0);
//            subjectPriorityMedium.remove(0);
        } else if (!subjectPriorityEasy.isEmpty()) {
            selectedSubject = subjectPriorityEasy.get(0);
//            subjectPriorityEasy.remove(0);
        }
        if (selectedSubject != null && selectedSubject.getPriority() != 0) {
            subjectCounter++;
        }
        if (subjectCounter > 6) {
            subjectCounter = 0;
        }
        if (selectedSubject != null) {
            System.out.println("selected Sub: " + selectedSubject.getTitle());
        }
        System.out.println("subjectCounter: " + subjectCounter);
        return selectedSubject;
    }

    /**
     * This method selects a topic group depending on its priority.
     * Therefore a subject from which the topic group can be selected.
     *
     * @param subject subject from which topic group can be selected
     * @return selected topic group
     */
    public static Topic selectTopicGroup(Subject subject) {
        Topic selectedTopicGroup = null;
        System.out.println(subject.getAlltopicsList());
        // If there is a topic which priority is unset, select first
        if (!subject.getTopicGroupPriorityUnset().isEmpty()) {
            selectedTopicGroup = subject.getTopicGroupPriorityUnset().get(0);
        // select topic with priority 'hard' when there is one, three times
        } else if (topicGroupCounter < 3 && !subject.getTopicGroupPriorityHard().isEmpty()) {
            selectedTopicGroup = subject.getTopicGroupPriorityHard().get(0);
        // select topic with priority 'medium' when there is one, two times
        } else if (topicGroupCounter < 5 && !subject.getTopicGroupPriorityMedium().isEmpty()) {
            selectedTopicGroup = subject.getTopicGroupPriorityMedium().get(0);
        // select topic with priority 'easy' when there is one, once
        } else if (!subject.getTopicGroupPriorityEasy().isEmpty()) {
            selectedTopicGroup = subject.getTopicGroupPriorityEasy().get(0);
        }

        // If a topic was selected and its priority is not 'unset', count
        if (selectedTopicGroup != null && selectedTopicGroup.getPriority() != 0) {
            topicGroupCounter++;
        }

        // reset counter to be able to loop in 3-2-1-time-displaying manner
        if (topicGroupCounter > 6) {
            topicGroupCounter = 0;
        }

        if (selectedTopicGroup != null) {
            System.out.println("selected TG: " + selectedTopicGroup.getTitle());
        }
        System.out.println("topicGroupCounter: " + topicGroupCounter);
        return selectedTopicGroup;
    }

    /**
     * This method selects a topic from a topic group depending on its priority.
     * Therefore a topic group is needed from which a topic can be selected.
     *
     * @param topicGroup topic group from which topic can be selected
     * @return selected topic
     */
    public static Topic selectTopicFromTopicGroup(Topic topicGroup) {
        Topic selectedTopic = null;
        System.out.println(topicGroup.getTopicPriorityEasy());
        // If there is a topic which priority is unset, select first
        if (!topicGroup.getTopicPriorityUnset().isEmpty()) {
            selectedTopic = topicGroup.getTopicPriorityUnset().get(0);
        // select topic with priority 'hard' when there is one, three times
        } else if (topicCounter < 3 && !topicGroup.getTopicPriorityHard().isEmpty()) {
            selectedTopic = topicGroup.getTopicPriorityHard().get(0);
        // select topic with priority 'medium' when there is one, two times
        } else if (topicCounter < 5 && !topicGroup.getTopicPriorityMedium().isEmpty()) {
            selectedTopic = topicGroup.getTopicPriorityMedium().get(0);
        // select topic with priority 'easy' when there is one, once
        } else if (!topicGroup.getTopicPriorityEasy().isEmpty()) {
            selectedTopic = topicGroup.getTopicPriorityEasy().get(0);
        }

        // If a topic was selected and its priority is not 'unset', count
        if (selectedTopic != null && selectedTopic.getPriority() != 0) {
            topicCounter++;
        }

        // reset counter to be able to loop in 3-2-1-time-displaying manner
        if (topicCounter > 6) {
            topicCounter = 0;
        }
        if (selectedTopic != null) {
            System.out.println("selected Topic: " + selectedTopic.getTitle());
        }
        System.out.println("topicCounter: " + topicCounter);
        return selectedTopic;
    }

    /**
     * This method selects a topic from a subject (without considering the topic groups priorities)
     * depending on its priority.
     * Therefore a subject is needed from which a topic can be selected.
     *
     * @param subject subject from which topic can be selected
     * @return selected topic
     */
    public static Topic selectTopicFromSubject(Subject subject) {
        Topic selectedTopic = null;
        System.out.println(subject.getTopicPriorityEasy());
        // If there is a topic which priority is unset, select first
        if (!subject.getTopicPriorityUnset().isEmpty()) {
            selectedTopic = subject.getTopicPriorityUnset().get(0);
        // select topic with priority 'hard' when there is one, three times
        } else if (topicCounter < 3 && !subject.getTopicPriorityHard().isEmpty()) {
            selectedTopic = subject.getTopicPriorityHard().get(0);
        // select topic with priority 'medium' when there is one, two times
        } else if (topicCounter < 5 && !subject.getTopicPriorityMedium().isEmpty()) {
            selectedTopic = subject.getTopicPriorityMedium().get(0);
        // select topic with priority 'easy' when there is one, once
        } else if (!subject.getTopicPriorityEasy().isEmpty()) {
            selectedTopic = subject.getTopicPriorityEasy().get(0);
        }

        if (selectedTopic != null && selectedTopic.getPriority() != 0) {
            topicCounter++;
        }
        if (topicCounter > 6) {
            topicCounter = 0;
        }
        if (selectedTopic != null) {
            System.out.println("selected Topic: " + selectedTopic.getTitle());
        }
        System.out.println("topicCounter: " + topicCounter);
        return selectedTopic;
    }

    public static ArrayList<Subject> getSubjectPriorityUnset() {
        return subjectPriorityUnset;
    }

    public static ArrayList<Subject> getSubjectPriorityHard() {
        return subjectPriorityHard;
    }

    public static ArrayList<Subject> getSubjectPriorityMedium() {
        return subjectPriorityMedium;
    }

    public static ArrayList<Subject> getSubjectPriorityEasy() {
        return subjectPriorityEasy;
    }

    public static int getSubjectCounter() {
        return subjectCounter;
    }

    public static int getTopicGroupCounter() {
        return topicGroupCounter;
    }

    public static int getTopicCounter() {
        return topicCounter;
    }

    public static void setSubjectCounter(int subjectCounter) {
        Selecting.subjectCounter = subjectCounter;
    }

    public static void setTopicGroupCounter(int topicGroupCounter) {
        Selecting.topicGroupCounter = topicGroupCounter;
    }

    public static void setTopicCounter(int topicCounter) {
        Selecting.topicCounter = topicCounter;
    }
}
