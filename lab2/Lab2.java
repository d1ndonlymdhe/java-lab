class InvalidValueException extends Exception {
    public InvalidValueException(String s) {
        super(s);
    }
}

class Time {
    static private int count = 0;
    private int hours;

    private int minutes;

    private int seconds;

    public int getHours() {
        return hours;
    }

    public void setHours(int hour) throws InvalidValueException {
        if (hour >= 0) {
            this.hours = hour;
        } else {
            throw new InvalidValueException("Hour should be > 0");
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minute) throws InvalidValueException {
        if (minute >= 0) {
            this.minutes = minute;
        } else {
            throw new InvalidValueException("Minute should be > 0");
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) throws InvalidValueException {
        if (seconds >= 0) {
            this.seconds = seconds;
        } else {
            throw new InvalidValueException("Second should be > 0");
        }
    }

    public Time(Time t) {
        try {
            setHours(t.hours);
            setMinutes(t.minutes);
            setSeconds(t.seconds);
            count++;
        } catch (InvalidValueException e) {
            System.out.println(e.getMessage());
        }
    }

    public Time(int hour, int minute, int second) {
        try {
            setHours(hour);
            setMinutes(minute);
            setSeconds(second);
            count++;
        } catch (InvalidValueException e) {
            System.out.println(e.getMessage());
        }
    }

    public Time() {
        hours = 0;
        minutes = 0;
        seconds = 0;
        count++;
    }

    public void hoursToTime(float hours) throws InvalidValueException {
        secondsToTime((int) (hours * 3600));
    }

    public void minutesToTime(float mins) throws InvalidValueException {
        secondsToTime((int) (mins * 60));
    }

    public void secondsToTime(int seconds) throws InvalidValueException {
        float h = (seconds / 3600f);
        setHours((int) h);
        float m = (h - hours) * 60;
        setMinutes((int) m);
        float s = (m - minutes) * 60;
        setSeconds((int) s);
    }

    int toSeconds() {
        return hours * 3600 + minutes * 60 + seconds;
    }

    public void addSecondsToTime(int seconds) throws InvalidValueException {
        secondsToTime(toSeconds() + seconds);
    }

    public static int getCount() {
        return count;
    }

    public String toString() {
        String retString = "";
        retString = retString + (hours < 10 ? ("0" + hours) : hours) + ":";
        retString = retString + (minutes < 10 ? ("0" + minutes) : minutes) + ":";
        retString = retString + (seconds < 10 ? ("0" + seconds) : seconds);
        return retString;
    }

    public void display() {
        System.out.println(this);
    }
}

public class Lab2 {
    public static void main(String[] args) throws Exception {
        Time t1 = new Time();
        t1.setHours(15);
        t1.setMinutes(5);
        t1.setSeconds(7);
        t1.display();
        // demonstrating parameterized constructor
        Time t2 = new Time(15, 5, 7);
        t2.display();
        // demonstrating copy constructor
        Time t3 = new Time(t1);
        t3.addSecondsToTime(53);
        t3.display();
        // generating exception
        try {
            t3.setSeconds(-5);
        } catch (InvalidValueException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Finally called");
        }
        Time t4 = new Time();
        t4.hoursToTime(5.87f);
        t4.display();
        Time t5 = new Time();
        t5.minutesToTime(100.5f);
        t5.display();
        Time t6 = new Time();
        t6.secondsToTime(500);
        t6.display();
        System.out.println(Time.getCount());
    }
}
