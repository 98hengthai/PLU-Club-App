package entities;

public class HomePageEvent {
    private String eventName;
    private String eventTime;

    public HomePageEvent(){}

    public HomePageEvent(String eventName, String eventTime){
        this.eventName = eventName;
        this.eventTime = eventTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
