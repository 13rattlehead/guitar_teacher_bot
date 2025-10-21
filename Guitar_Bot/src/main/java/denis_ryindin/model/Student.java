package denis_ryindin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "chat_id", unique = true)
    private String chatId;

    public Student() {}
    public Student(String name, String chatId) {
        this.name = name;
        this.chatId = chatId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getChatId() {
        return chatId;
    }
    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return name + " (chatId= " + chatId + ")";
    }
}