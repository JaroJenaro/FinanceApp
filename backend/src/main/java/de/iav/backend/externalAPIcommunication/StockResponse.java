package de.iav.backend.externalAPIcommunication;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.iav.backend.externalAPICommunication.TimeSeries;

public class StockResponse {
    @JsonProperty("Meta Data")
    private de.iav.backend.externalAPIcommunication.MetaData metaData;
    @JsonProperty("Time Series (5min)")
    private TimeSeries timeSeries;

    public StockResponse() {
    }

    public de.iav.backend.externalAPIcommunication.MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(de.iav.backend.externalAPIcommunication.MetaData metaData) {
        this.metaData = metaData;
    }

    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }
}
