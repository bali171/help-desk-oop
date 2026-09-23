package helpdesk;

import helpdesk.model.*;
import helpdesk.repository.TicketRepository;
import helpdesk.service.ConsoleNotificationService;
import helpdesk.service.NotificationService;
import helpdesk.service.TicketService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== HELP DESK ===\n");

        // 1. Клиент
        Customer anna = new Customer(1, "Анна Петрова", "anna@mail.ru");
        System.out.println("Клиент: " + anna.getName());

        // 2. Специалист
        SupportAgent sergey = new SupportAgent(2, "Сергей Иванов", "sergey@helpdesk.ru");

        // 3. Заявка
        Ticket ticket = new Ticket(1, "Не работает Wi-Fi",
                "После обновления пропало подключение", TicketPriority.HIGH);
        System.out.println("Заявка #" + ticket.getId() + ": " + ticket.getTitle()
                + " | " + ticket.getStatus() + "\n");

        // 4-7. Жизненный цикл
        NotificationService notifier = new ConsoleNotificationService();
        TicketService service = new TicketService(notifier);

        service.startTicket(ticket);
        System.out.println("Статус: " + ticket.getStatus() + "\n");

        service.resolveTicket(ticket);
        System.out.println("Статус: " + ticket.getStatus() + "\n");

        service.closeTicket(ticket);
        System.out.println("Статус: " + ticket.getStatus() + "\n");

        // 8. Репозиторий
        TicketRepository repo = new TicketRepository();
        repo.add(ticket);
        repo.add(new Ticket(2, "Ошибка приложения", "Падает при старте", TicketPriority.MEDIUM));
        repo.add(new Ticket(3, "Не печатает принтер", "Драйвер не найден", TicketPriority.LOW));

        System.out.println("--- Все заявки ---");
        for (Ticket t : repo.findAll()) {
            System.out.println(t);
        }

        // Полиморфизм
        System.out.println("\n--- Действия пользователей ---");
        List<User> users = List.of(
                new Customer(10, "Анна", "a@mail.ru"),
                new SupportAgent(11, "Сергей", "s@helpdesk.ru"),
                new Administrator(12, "Олег", "admin@helpdesk.ru")
        );
        for (User u : users) {
            u.performAction();
        }
    }
}