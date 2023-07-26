package de.iav.backend.externalAPICommunication;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class TimeSeries {
    private Map<String, TimeSeriesEntry> data = new HashMap<>();

    public TimeSeries() {
    }

    public Map<String, TimeSeriesEntry> getData() {
        return data;
    }

    public void setData(Map<String, TimeSeriesEntry> data) {
        this.data = data;
    }
    // Constructors, getters, and setters

    // Use @JsonAnySetter to handle dynamic date-time keys during deserialization
    @JsonAnySetter
    public void addEntry(String dateTime, TimeSeriesEntry entry) {
        data.put(dateTime, entry);
    }

    // Helper methods to access the data map
    public String getLastDateTime() {
        if (!data.isEmpty()) {
            return data.keySet().iterator().next();
        }
        return null;
    }

    public TimeSeriesEntry getEntry(String dateTime) {
        return data.get(dateTime);
    }
}
