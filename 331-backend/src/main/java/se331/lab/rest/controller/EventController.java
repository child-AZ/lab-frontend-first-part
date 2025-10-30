package se331.lab.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se331.lab.rest.entity.Event;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {
    List<Event> eventList;

    @PostConstruct
    public void init() {
        eventList = new ArrayList<>();
        eventList.add(Event.builder()
                .id(123L)
                .category("animal welfare")
                .title("Cat Adoption Day")
                .description("Find your new feline friend at this event.")
                .location("Meow Town")
                .date("January 28, 2022")
                .time("12:00")
                .petAllowed(true)
                .organizer("Kat Laydee")
                .build());

        eventList.add(Event.builder()
                .id(456L)
                .category("food")
                .title("Community Gardening")
                .description("Join us as we tend to the community edible plants.")
                .location("Flora City")
                .date("March 14, 2022")
                .time("10:00")
                .petAllowed(true)
                .organizer("Fern Pollin")
                .build());

        eventList.add(Event.builder()
                .id(789L)
                .category("animal welfare")
                .title("Dog Rescue Fundraiser")
                .description("Support local shelters with donations.")
                .location("Bark Village")
                .date("February 20, 2022")
                .time("13:30")
                .petAllowed(true)
                .organizer("Rex Ruff")
                .build());

        eventList.add(Event.builder()
                .id(101L)
                .category("education")
                .title("Pet Care Workshop")
                .description("Learn best practices for caring for pets.")
                .location("Paws Academy")
                .date("April 05, 2022")
                .time("09:00")
                .petAllowed(false)
                .organizer("Dr. Whiskers")
                .build());

        eventList.add(Event.builder()
                .id(202L)
                .category("community")
                .title("Neighborhood Cleanup")
                .description("Help clean public spaces in our neighborhood.")
                .location("Green Park")
                .date("May 01, 2022")
                .time("08:00")
                .petAllowed(false)
                .organizer("Civic Group")
                .build());

        eventList.add(Event.builder()
                .id(303L)
                .category("health")
                .title("Charity Run")
                .description("Join a 5K to raise funds.")
                .location("River Trail")
                .date("June 11, 2022")
                .time("07:00")
                .petAllowed(false)
                .organizer("Fitness Friends")
                .build());
    }

    @GetMapping("/events")
    public ResponseEntity<?> getEventLists(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page) {
        perPage = perPage == null ? eventList.size() : perPage;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * perPage;
        List<Event> output = new ArrayList<>();
        for (int i = firstIndex; i < Math.min(firstIndex + perPage, eventList.size()); i++) {
            output.add(eventList.get(i));
        }
        return ResponseEntity.ok(output);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") Long id) {
        for (Event e : eventList) {
            if (e.getId().equals(id)) {
                return ResponseEntity.ok(e);
            }
        }
        return ResponseEntity.notFound().build();
    }
}