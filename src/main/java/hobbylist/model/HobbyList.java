package hobbylist.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import hobbylist.model.activity.Activity;
import hobbylist.model.activity.UniqueActivityList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at HobbyList level
 * Duplicates are not allowed (by .isSameActivity comparison)
 */
public class HobbyList implements ReadOnlyHobbyList {

    private final UniqueActivityList activities;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        activities = new UniqueActivityList();
    }

    public HobbyList() {}

    /**
     * Creates a HobbyList using the Activities in the {@code toBeCopied}
     */
    public HobbyList(ReadOnlyHobbyList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the activities list with {@code activities}.
     * {@code activities} must not contain duplicate activities.
     */
    public void setActivities(List<Activity> activities) {
        this.activities.setActivities(activities);
    }

    /**
     * Resets the existing data of this {@code HobbyList} with {@code newData}.
     */
    public void resetData(ReadOnlyHobbyList newData) {
        requireNonNull(newData);

        setActivities(newData.getActivityList());
    }

    //// activity-level operations

    /**
     * Returns true if an activity with the same identity as {@code activity} exists in the HobbyList.
     */
    public boolean hasActivity(Activity activity) {
        requireNonNull(activity);
        return activities.contains(activity);
    }

    /**
     * Adds an activity to the HobbyList.
     * The activity must not already exist in the HobbyList.
     */
    public void addActivity(Activity p) {
        activities.add(p);
    }

    /**
     * Replaces the given activity {@code target} in the list with {@code editedActivity}.
     * {@code target} must exist in the HobbyList.
     * The activity identity of {@code editedActivity} must not be the same as another existing activity in the
     * HobbyList
     */
    public void setActivity(Activity target, Activity editedActivity) {
        requireNonNull(editedActivity);

        activities.setActivity(target, editedActivity);
    }

    /**
     * Removes {@code key} from this {@code HobbyList}.
     * {@code key} must exist in the HobbyList.
     */
    public void removeActivity(Activity key) {
        activities.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return activities.asUnmodifiableObservableList().size() + " activities";
        // TODO: refine later
    }

    @Override
    public ObservableList<Activity> getActivityList() {
        return activities.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HobbyList // instanceof handles nulls
                && activities.equals(((HobbyList) other).activities));
    }

    @Override
    public int hashCode() {
        return activities.hashCode();
    }
}