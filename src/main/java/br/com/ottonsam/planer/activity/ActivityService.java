package br.com.ottonsam.planer.activity;

import br.com.ottonsam.planer.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public ActivityResponse registerActivity(ActivityRequestPayload payload, Trip trip) {
        Activity activity = new Activity(payload, trip);
        activityRepository.save(activity);

        return new ActivityResponse(activity.getId());
    }

    public List<ActivityData> getAllActivities(UUID tripId) {
        return this.activityRepository.findByTripId(tripId).stream().map(activity -> new ActivityData(activity.getId(), activity.getTitle(), activity.getOccursAt())).toList();
    }

}
