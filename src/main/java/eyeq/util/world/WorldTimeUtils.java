package eyeq.util.world;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldTimeUtils {
    public static final int REAL_SECONDS_IN_MINUTE = 60;
    public static final int REAL_MINUTES_IN_HOUR = 60;
    public static final int REAL_HOURS_IN_DAY = 24;
    public static final int REAL_DAYS_IN_WEEK = 7;
    public static final int REAL_DAYS_IN_MONTH = 30;
    public static final int REAL_MONTHS_IN_YEAR = 4;

    public static final float SECONDS_IN_MINUTE;
    public static final int SECONDS_IN_HOUR;
    public static final int SECONDS_IN_DAY = 24000;
    public static final int SECONDS_IN_WEEK;
    public static final int SECONDS_IN_MONTH;
    public static final int SECONDS_IN_YEAR;

    static {
        SECONDS_IN_WEEK = SECONDS_IN_DAY * REAL_DAYS_IN_WEEK;
        SECONDS_IN_MONTH = SECONDS_IN_DAY * REAL_DAYS_IN_MONTH;
        SECONDS_IN_YEAR = SECONDS_IN_MONTH * REAL_MONTHS_IN_YEAR;

        SECONDS_IN_HOUR = SECONDS_IN_DAY / REAL_HOURS_IN_DAY;
        SECONDS_IN_MINUTE = SECONDS_IN_HOUR / REAL_MINUTES_IN_HOUR;
    }

    public static long getWorldTime(World world) {
        return world.getWorldTime();
    }

    // return 0 - 23999
    public static int getTimeInDay(World world) {
        return (int) (getWorldTime(world) % SECONDS_IN_DAY);
    }

    // return 0 - 59
    public static int getSecond(World world) {
        return (int) (getTimeInDay(world) % SECONDS_IN_MINUTE);
    }

    // return 0 - 59
    public static int getMinute(World world) {
        return (int) (getTimeInDay(world) / SECONDS_IN_MINUTE) % REAL_MINUTES_IN_HOUR;
    }

    // return 0 - 23
    public static int getHour(World world) {
        return getTimeInDay(world) / SECONDS_IN_HOUR % REAL_HOURS_IN_DAY;
    }

    public static boolean isAm(World world) {
        return getHour(world) < 12;
    }

    public static long getTotalDay(World world) {
        return getWorldTime(world) / SECONDS_IN_DAY + 1;
    }

    // return 1 - 30
    public static int getDay(World world) {
        return (int) (getTotalDay(world) % REAL_DAYS_IN_MONTH);
    }

    public static Day getDayOfWeek(World world) {
        return Day.getDayFromOrder(getDay(world));
    }

    // return 1 - 5
    public static int getWeek(World world) {
        return (int) (getWorldTime(world) % SECONDS_IN_MONTH) / SECONDS_IN_WEEK + 1;
    }

    // return 1 - 4
    public static int getMonth(World world) {
        return (int) (getWorldTime(world) / SECONDS_IN_MONTH % REAL_MONTHS_IN_YEAR) + 1;
    }

    public static Season getSeason(World world) {
        return Season.getSeasonFromMonth(getMonth(world));
    }

    public static int getYear(World world) {
        return (int) (getWorldTime(world) / SECONDS_IN_YEAR) + 1;
    }

    public enum Day {
        SUN("sunday"),
        MAN("manday"),
        TUE("tuesday"),
        WED("wednesday"),
        THU("thursday"),
        FRI("friday"),
        SAT("saturday");

        private final String name;

        Day(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @SideOnly(Side.CLIENT)
        public String getTranslatedName() {
            return "day." + this.getName();
        }

        public Day prevDay() {
            return getDayFromOrder(getOrderFromDay(this) - 1);
        }

        public Day nextDay() {
            return getDayFromOrder(getOrderFromDay(this) + 1);
        }

        public static int getOrderFromDay(Day day) {
            return day.ordinal() + 1;
        }

        public static Day getDayFromOrder(int order) {
            --order;
            order %= REAL_DAYS_IN_WEEK;
            if(order < 0) {
                order += REAL_DAYS_IN_WEEK;
            }
            for(Day day : Day.values()) {
                if(day.ordinal() == order) {
                    return day;
                }
            }
            return null;
        }
    }

    public enum Season {
        SPRING("spring"),
        SUMMER("summer"),
        AUTUMN("autumn"),
        WINTER("winter")
        ;

        private final String name;

        Season(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @SideOnly(Side.CLIENT)
        public String getTranslatedName() {
            return "season." + this.getName();
        }

        public Season prevSeason() {
            return getSeasonFromMonth(getMonthFromSeason(this) - 1);
        }

        public Season nextSeason() {
            return getSeasonFromMonth(getMonthFromSeason(this) + 1);
        }

        public static int getMonthFromSeason(Season season) {
            return season.ordinal() + 1;
        }

        public static Season getSeasonFromMonth(int month) {
            --month;
            month %= REAL_MONTHS_IN_YEAR;
            if(month < 0) {
                month += REAL_MONTHS_IN_YEAR;
            }
            for(Season season : Season.values()) {
                if(season.ordinal() == month) {
                    return season;
                }
            }
            return null;
        }
    }
}
