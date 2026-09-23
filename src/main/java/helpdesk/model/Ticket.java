package helpdesk.model;

import java.time.LocalDateTime;

public class Ticket {
    private final long id;
    private final String title;
    private final String description;
    private TicketStatus status;
    private final TicketPriority priority;
    private final LocalDateTime createdAt;

    public Ticket(long id, String title, String description, TicketPriority priority) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название заявки не может быть пустым");
        }
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TicketStatus.NEW;
        this.createdAt = LocalDateTime.now();

    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TicketStatus getStatus() { return status; }
    public TicketPriority getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void startProcessing() {
        if (status != TicketStatus.NEW) {
            System.out.println("Ошибка: в работу можно взять только новую заявку");

            return;
        }
        status = TicketStatus.IN_PROGRESS;
    }

    public void resolve() {
        if (status != TicketStatus.IN_PROGRESS) {
            System.out.println("Ошибка: решить можно только заявку в работе");
            return;
        
        }
        status = TicketStatus.RESOLVED;
    }

    public void close() {
        if (status != TicketStatus.RESOLVED) {
            System.out.println("Ошибка: закрыть можно только решённую заявку");
            return;

        }
        status = TicketStatus.CLOSED;
    }

    public void cancel() {
        if (status == TicketStatus.CLOSED) {
            System.out.println("Ошибка: нельзя отменить закрытую заявку");
            return;
        }
        status = TicketStatus.CANCELLED;
    }

    @Override 
    public String toString() {
        return String.format("#%d %s | %s | %s | %s",
            id, title, priority, status, createdAt.toLocalDate());
    }
}