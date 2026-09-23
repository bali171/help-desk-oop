package helpdesk.model;

import java.util.ArrayList;
import java.util.List;

public class SupportAgent extends User {
    private final List<Ticket> assignedTickets = new ArrayList<>();

    public SupportAgent(long id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public void performAction() {
        System.out.println(getName() + " обрабатывает заявку");
    }

    public void assignTicket(Ticket ticket) {
        if (ticket.getStatus() != TicketStatus.NEW) {
            System.out.println("Можно назначить только новую заявку");
            return;
        }
        ticket.startProcessing();
        assignedTickets.add(ticket);
        System.out.println(getName() + " взял в работу заявку #" + ticket.getId());
    }

    public List<Ticket> getAssignedTickets() {
        return new ArrayList<>(assignedTickets);
    }
}