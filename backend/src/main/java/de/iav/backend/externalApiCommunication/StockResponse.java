package de.iav.backend.externalApiCommunication;

import com.fasterxml.jackson.annotation.JsonProperty;
public class StockResponse {
    @JsonProperty("Meta Data")
    private MetaData metaData;
    @JsonProperty("Time Series (5min)")
    private TimeSeries timeSeries;

    public StockResponse() {
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }
}
