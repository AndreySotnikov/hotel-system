package project.dto;

import project.entity.TimeTable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by andrey on 15.04.15.
 */
public class TimetableDto {
    private int id;
    private String date;
    private String state;

    public TimetableDto() {
    }

    public TimetableDto(TimeTable timeTable){
        this.id = timeTable.getTimeTableId();
        this.state = timeTable.getRoomState().getName();
        Date df = new Date(timeTable.getFrom());
        Date dt = new Date(timeTable.getTo());
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        this.date = dateFormat.format(df)+" - "+dateFormat.format(dt);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimetableDto that = (TimetableDto) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TimetableDto{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
