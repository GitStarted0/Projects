package Logic;

import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;


public class Tests {

    @Test
    public void subjectCreation() {
        // Arrange
        String title = "Software Development I";
        int prio = 2;
        LocalDate date = LocalDate.of(2022, 3, 14);
        // Act
        Subject subject = new Subject(title, prio, date);
        // Assert
        Assertions.assertNotNull(subject);
        Assertions.assertEquals(title, subject.getTitle());
        Assertions.assertEquals(prio, subject.getPriority());
        Assertions.assertEquals(date, subject.getDateOfExam());
    }

    @Test
    public void subjectCreationWithoutPriority() {
        // Arrange
        String title = "Software Development I";
        LocalDate date = LocalDate.of(2022, 3, 14);
        // Act
        Subject subject = new Subject(title, date);
        // Assert
        Assertions.assertNotNull(subject);
        Assertions.assertEquals(title, subject.getTitle());
        Assertions.assertEquals(0, subject.getPriority());
        Assertions.assertEquals(date, subject.getDateOfExam());
    }

    @Test
    public void subjectCreationWithoutDateOfExam() {
        // Arrange
        String title = "Software Development I";
        int prio = 2;
        // Act
        Subject subject = new Subject(title, prio);
        // Assert
        Assertions.assertNotNull(subject);
        Assertions.assertEquals(title, subject.getTitle());
        Assertions.assertEquals(prio, subject.getPriority());
        Assertions.assertNull(subject.getDateOfExam());
    }

    @Test
    public void subjectCreationTitleOnly() {
        // Act
        Subject subject = new Subject("Software Development I");
        // Assert
        Assertions.assertNotNull(subject);
        Assertions.assertNull(subject.getDateOfExam());
    }

    @Test
    public void subjectSetPriority() {
        // Arrange
        Subject subject = new Subject("Software Development I", 2, LocalDate.now());
        // Act
        subject.setPriority(1);
        // Assert
        Assertions.assertEquals(1, subject.getPriority());
    }

    @Test
    public void subjectSetTitle() {
        // Arrange
        Subject subject = new Subject("Software Development I", 2, LocalDate.now());
        // Act
        subject.setTitle("physics");
        // Assert
        Assertions.assertEquals("physics", subject.getTitle());
    }

    @Test
    public void subjectSetDate() {
        // Arrange
        LocalDate date = LocalDate.of(2022, 5, 12);
        Subject subject = new Subject("Software Development I", 2, LocalDate.now());
        // Act
        subject.setDateOfExam(date);
        // Assert
        Assertions.assertEquals(date, subject.getDateOfExam());
    }

    @Test
    public void subjectList() {
        // Arrange
        int year = 2022;
        int month = 10;
        int day = 10;
        // Act
        for (int i = 0; i < 5; i++) {
            new Subject("subject" + (i + 1), LocalDate.of(year - i, month - i, day - i));
        }
        // Assert
        System.out.println(Subject.getSubjectList().toString());
        Assertions.assertNotNull(Subject.getSubjectList());
        Assertions.assertEquals(5, Subject.getSubjectList().size());

    }

    @Test
    public void topicCreation() {
        // Arrange
        String title = "String";
        String answer = "A character string, protected in quotation marks\nexample:\nexample = 'example';";
        int priority = 3;
        Subject subject = new Subject("Software Development I");
        Topic topicGroup = new Topic("Primitive Data Types", subject);
        // Act
        Topic topic = new Topic(title, answer, priority, subject, topicGroup);
        // Assert
        Assertions.assertNotNull(topic);
        Assertions.assertEquals(title, topic.getTitle());
        Assertions.assertEquals(answer, topic.getAnswer());
        Assertions.assertEquals(priority, topic.getPriority());
        Assertions.assertEquals(subject, topic.getSubject());
        Assertions.assertEquals(topicGroup, topic.getTopicGroup());
    }

    @Test
    public void topicCreationWithoutPriority() {
        // Arrange
        String title = "String";
        String answer = "A character string, protected in quotation marks\nexample:\nexample = 'example';";
        Subject subject = new Subject("Software Development I");
        Topic topicGroup = new Topic("Primitive Data Types", subject);
        // Act
        Topic topic = new Topic(title, answer, subject, topicGroup);
        // Assert
        Assertions.assertNotNull(topic);
        Assertions.assertEquals(title, topic.getTitle());
        Assertions.assertEquals(answer, topic.getAnswer());
        Assertions.assertEquals(0, topic.getPriority());
        Assertions.assertEquals(subject, topic.getSubject());
        Assertions.assertEquals(topicGroup, topic.getTopicGroup());
    }

    @Test
    public void topicCreationWithoutTopicGroup() {
        // Arrange
        String title = "String";
        String answer = "A character string, protected in quotation marks\nexample:\nexample = 'example';";
        int priority = 3;
        Subject subject = new Subject("SE-II");
        // Act
        Topic topic = new Topic(title, answer, priority, subject);
        // Assert
        Assertions.assertNotNull(topic);
        Assertions.assertEquals(title, topic.getTitle());
        Assertions.assertEquals(answer, topic.getAnswer());
        Assertions.assertEquals(priority, topic.getPriority());
        Assertions.assertEquals(subject, topic.getSubject());
        Assertions.assertNull(topic.getTopicGroup());
    }

    @Test
    public void topicCreationWithoutAnswerButWithTopicGroup() {
        // Arrange
        Subject test = new Subject("Software Development I");
        Topic test2 = new Topic("Topic Group", test);
        String title = "Primitive Data Types";
        int priority = 3;
        Subject subject = test;
        Topic topicGroup = test2;
        // Act
        Topic topic = new Topic(title, priority, subject, topicGroup);
        // Assert
        Assertions.assertNotNull(topic);
        Assertions.assertEquals(title, topic.getTitle());
        Assertions.assertNull(topic.getAnswer());
        Assertions.assertEquals(priority, topic.getPriority());
        Assertions.assertEquals(subject.getTitle(), topic.getSubject().getTitle());
        Assertions.assertEquals(topicGroup.getTitle(), topic.getTopicGroup().getTitle());
    }

    @Test
    public void topicList() {
        // Arrange
        Subject subject = new Subject("Software Development I");
        Topic topicGroup = new Topic("Topic Group", subject);
        for (int i = 0; i < 5; i++) {
            new Topic("Topic " + (i + 1), subject, topicGroup);
        }
        // Assert
        System.out.println(subject.getTopicList().toString());
        Assertions.assertNotNull(subject.getTopicList());
        Assertions.assertEquals(5, subject.getTopicList().size());
    }

    @Test
    public void setterMethodsTopic() {
        // Arrange
        Subject atFirst = new Subject("Software Development I", 0, LocalDate.now());
        Subject then = new Subject("New Title");
        String title = "String";
        String answer = "A character string, protected in quotation marks\nexample:\nexample = 'example';";
        int priority = 3;
        Topic tG1 = new Topic("Primitive Data Types", atFirst);
        Topic tG2 = new Topic("New Topic Group", then);
        Topic topic = new Topic(title, answer, priority, atFirst, tG1);
        // Act
        topic.setTitle("New Title");
        topic.setAnswer("New Answer");
        topic.setPriority(1);
        topic.setSubject(then);
        topic.setTopicGroup(tG2);
        // Assert
        Assertions.assertEquals("New Title", topic.getTitle());
        Assertions.assertEquals("New Answer", topic.getAnswer());
        Assertions.assertEquals(1, topic.getPriority());
        Assertions.assertEquals("New Subject", topic.getSubject().getTitle());
        Assertions.assertEquals("New Topic Group", topic.getTopicGroup().getTitle());
    }

    @Test
    public void dataTypesCreatedCorrectly() {
        // Arrange
        Subject subject = new Subject("Software Development I", 2, LocalDate.of(2022, 3, 14));
        // Act
        String[] titles = new String[] {"String", "Integer", "Boolean", "Primitive Data Types"};
        String[] answers = new String[] {"Text", "Number", "True/False"};
        for (int i = 0; i < titles.length; i++) {
            if (i < answers.length){
                new Topic(titles[i], answers[i], 2, subject);
            } else {
                new Topic(titles[i], 2, subject);
            }
        }
        // Assert
        Assertions.assertEquals(subject.getTopicList().get(0).getTitle(), titles[0]);
        Assertions.assertEquals(subject.getTopicList().get(1).getTitle(), titles[1]);
        Assertions.assertEquals(subject.getTopicList().get(2).getTitle(), titles[2]);
        Assertions.assertEquals(subject.getTopicGroupList().get(0).getTitle(), titles[3]);
        System.out.println("Subjects:");
        for (Subject sub : Subject.getSubjectList()) {
            System.out.println(sub.getDateOfExam());
        }
        System.out.println("Topic Groups:");
        for (Topic topicGroup : subject.getTopicGroupList()) {
            System.out.println(topicGroup.getTitle());
        }
        System.out.println("Topics:");
        for (Topic topic : subject.getTopicList()) {
            System.out.println(topic.getTitle());
        }
        String[] contents = new String[] {subject.getTitle(), Integer.toString(subject.getPriority()),
                subject.getDateOfExam().toString()};
    }

    @Test
    public void writeToFileCorrectly() {
        // Arrange
        setupObjects();
        // Assert
        FileManagement.printFile(FileManagement.getSubjectsPath());
        FileManagement.printFile(FileManagement.getTopicGroupsPath());
        FileManagement.printFile(FileManagement.getTopicsPath());
//        clearFiles();
    }

    @Test
    public void createObjectsFromFile() {
        // Arrange
        // Act
        FileManagement.createSubjectsFromFile();
        FileManagement.createTopicGroupsFromFile();
        FileManagement.createTopicsFromFile();
        // Assert
        // Two subjects were created from subjects.txt
        for (Subject s : Subject.getSubjectList()) {
            System.out.println("Subject: " + s.getTitle() + ", " + s.getPriority() + ", " + s.getDateOfExam());
        }
        Subject s1 = Subject.getSubjectList().get(0);
        Subject s2 = Subject.getSubjectList().get(1);
        System.out.println("Topic Group: " + s1.getTopicGroupList().get(0).getTitle()
                + ", " + s1.getTopicGroupList().get(0).getPriority() + ", "
                + s1.getTopicGroupList().get(0).getSubject().getTitle());
        System.out.println("Topic Group: " + s2.getTopicGroupList().get(0).getTitle()
                + ", " + s2.getTopicGroupList().get(0).getPriority() + ", "
                + s2.getTopicGroupList().get(0).getSubject().getTitle());
        System.out.println("Topic: " + s1.getTopicList().get(0).getTitle() + ", "
                + s1.getTopicList().get(0).getAnswer() + ", "
                + s1.getTopicList().get(0).getPriority() + ", "
                + s1.getTopicList().get(0).getSubject().getTitle() + ", "
                + s1.getTopicList().get(0).getTopicGroup().getTitle());
        System.out.println("Topic: " + s2.getTopicList().get(0).getTitle() + ", "
                + s2.getTopicList().get(0).getAnswer() + ", "
                + s2.getTopicList().get(0).getPriority() + ", "
                + s2.getTopicList().get(0).getSubject().getTitle() + ", "
                + s2.getTopicList().get(0).getTopicGroup().getTitle());
        Assertions.assertEquals(2, Subject.getSubjectList().size());
    }

    public void clearFiles() {
        try {
            File contents = new File("C:\\Users\\user\\Documents\\Projekt-Semesterferien\\files");
            File[] files = contents.listFiles();
            for (File file : files) {
                List<String> read = Files.readAllLines(Path.of(file.getPath()));
                read.clear();
                Files.write(Path.of(file.getPath()), read, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupObjects() {
        Subject s1 = new Subject("SE-I", LocalDate.of(2022, 3, 14));
        Subject s2 = new Subject("SE-II", LocalDate.of(2021, 1, 3));
        Topic tg1 = new Topic("Topic Group 1", s1);
        Topic tg2 = new Topic("Topic Group 2", s2);
        Topic t1 = new Topic("Topic 1", "Answer 1", s1, tg1);
        Topic t2 = new Topic("Topic 2", "Answer 2", s2, tg2);
        FileManagement.writeSubject(s1);
        FileManagement.writeSubject(s2);
        FileManagement.writeTopic(tg1);
        FileManagement.writeTopic(tg2);
        FileManagement.writeTopic(t1);
        FileManagement.writeTopic(t2);
    }
}
