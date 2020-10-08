package cn.jdd.reactwebworkflowvertx.Entity;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jdd")
public class Travel {

    private String zone;

    private String time;

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "zone='" + zone + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
