package br.com.ottonsam.planer.link;

import br.com.ottonsam.planer.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    public LinkResponse registerLink(LinkRequestPayload payload, Trip trip) {
        Link link = new Link(payload, trip);
        this.linkRepository.save(link);

        return new LinkResponse(link.getId());
    }

    public List<LinkData> getAllLinks(UUID tripId) {
        return this.linkRepository.findByTripId(tripId).stream().map(link -> new LinkData(link.getId(), link.getTitle(), link.getUrl())).toList();
    }
}
