package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Message {
    @JsonProperty
    private String type;

    @JsonProperty
    private String time;

    @JsonProperty
    private int id;

    public Message(String type, String time, int id) {
        this.type = type;
        this.time = time;
        this.id = id;
    }

    public Message() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                Objects.equals(type, message.type) &&
                Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, time, id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", id=" + id +
                '}';
    }
}


