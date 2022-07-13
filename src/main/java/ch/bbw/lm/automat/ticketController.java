package ch.bbw.lm.automat;

import ch.bbw.lm.automat.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class ticketController {

    @Autowired
    Ticket myTicket;

    //
    //First Screen
    //

    @GetMapping("/")
    public String showStartScreen(){
        System.out.println("Startscreen is shown");
        return "startScreen";
    }

    //
    // Choose Ticket Type
    //

    @GetMapping("/ticketType")
    public String showTicketTypeScreen(Model model){
        System.out.println("showTicketType is shown");
        model.addAttribute("ticket", myTicket);
        model.addAttribute("ticketValues", myTicket.getTicketTypeValues()); //List with Form-Radiobutton types
        return "ticketType";
    }

    @PostMapping("/ticketType")
    public String postTicketType(@Valid @ModelAttribute Ticket ticket, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "ticketType";
        }
        myTicket = ticket;
        System.out.println(myTicket);
        if(Objects.equals(myTicket.getTicketType(), "Tageskarte")){
            return "redirect:/ticketType-dayCard";
        }
        else if(Objects.equals(myTicket.getTicketType(), "Einzelfahrt")){
            return "redirect:/ticketType-normalTicket";
        }
        else if(Objects.equals(myTicket.getTicketType(), "Hin und Retour")){
            return "redirect:/ticketType-normalTicket";
        }
        else {
            return "redirect:/ticketType-errorPage";
        }
    }

    //
    //DayCard Option
    //

    @GetMapping("/ticketType-dayCard")
    public String showDayCardScreen(Model model){
        System.out.println("DayCard Screen");
        model.addAttribute("ticket", myTicket);
        model.addAttribute("transportValues", myTicket.getTicketTransportValues());
        return "dayCard";
    }

    @PostMapping("/dayCard")
    public String postDayCard(@ModelAttribute Ticket ticket){
        ticket.setTicketType(myTicket.getTicketType());
        myTicket = ticket;
        System.out.println(myTicket);
        return "redirect:/generalInfos";
    }

    //
    //SingleTicket Option
    //

    @GetMapping("/ticketType-normalTicket")
    public String showNormalTicketScreen(Model model){
        System.out.println("NormalTicket Screen");
        model.addAttribute("ticket", myTicket);
        return "normalTicket";
    }

    @PostMapping("/normalTicket")
    public String postNormalTicket(@ModelAttribute Ticket ticket){
        ticket.setTicketType(myTicket.getTicketType());
        myTicket = ticket;
        System.out.println(myTicket);
        return "redirect:/generalInfos";
    }

    //
    //GeneralInfos
    //

    @GetMapping("/generalInfos")
    public String showGeneralInfosScreen(Model model){
        System.out.println("GeneralInfos Screen");
        model.addAttribute("ticket", myTicket);
        return "generalInfos";
    }

    @PostMapping("/generalInfos")
    public String postGeneralInfos(@ModelAttribute Ticket ticket){
        ticket.setTicketType(myTicket.getTicketType());
        if(Objects.equals(ticket.getTicketType(), "Tageskarte")){
        ticket.setTransport(myTicket.getTransport());
        ticket.setPlz(myTicket.getPlz());
        ticket.setStart("Beliebig");
        ticket.setGoal("Beliebig");
        } else {
        ticket.setStart(myTicket.getStart());
        ticket.setGoal(myTicket.getGoal());
        ticket.setTransport("Zug, Tram, Bus und Schiff");
        ticket.setPlz(8166);
        }
        myTicket = ticket;
        System.out.println(myTicket);
        return "ticketOverview";
    }



}