package ch.bbw.lm.automat.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Ticket {

    //
    // TicketType
    //

    private List<String> ticketTypeValues = new ArrayList<>(Arrays.asList(
            "Tageskarte",
            "Einzelfahrt",
            "Hin und Retour"
    ));

    @NotEmpty
    private String ticketType;

    //
    // DayCard
    //

    @NotEmpty
    private String transport;

    private List<String> ticketTransportValues = new ArrayList<>(Arrays.asList(
            "Zug",
            "Tram",
            "Bus",
            "Schiff"
    ));

    @Min(1000)
    @Max(9658)
    private Integer plz;

    //
    // NormalTicket
    //

    @NotEmpty
    @Size(min=2, max=25)
    private String start;

    @NotEmpty
    @Size(min=2, max=25)
    private String goal;

    //
    // GeneralInfos
    //

    @NotNull
    //@Future
    private String date;

    @NotNull
    private String time;



    public List<String> getTicketTypeValues() {
        return ticketTypeValues;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTransport() { return transport; }

    public void setTransport(String transport) { this.transport = transport; }

    public List<String> getTicketTransportValues() { return ticketTransportValues; }

    public Integer getPlz() { return plz; }

    public void setPlz(Integer plz) { this.plz = plz; }

    public String getStart() { return start; }

    public void setStart(String start) { this.start = start; }

    public String getGoal() { return goal; }

    public void setGoal(String goal) { this.goal = goal; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "TicketType='" + ticketType + '\'' +
                "Transport='" + transport + '\'' +
                "PLZ='" + plz + '\'' +
                "Start='" + start + '\'' +
                "Goal='" + goal + '\'' +
                "Date='" + date + '\'' +
                "Time='" + time + '\'' +
                '}';
    }
}

